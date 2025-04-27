/*package com.example.agreementservice.controller;

public class AgreementController {
    
}
*/
package com.example.agreementservice.controller;

import com.example.agreementservice.model.*;
import com.example.agreementservice.service.AgreementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/agreements")
public class AgreementController {

    private final AgreementService service;

    public AgreementController(AgreementService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
    @PostMapping("/draft")
    public ResponseEntity<Agreement> createDraft(@RequestBody Agreement agreement) {
        return ResponseEntity.ok(service.createDraft(agreement));
    }

    @PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAgreement(@PathVariable Long id, @RequestBody Agreement agreement) {
        return service.updateAgreement(id, agreement)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('EMPLOYER', 'JOBSEEKER')")
    @PutMapping("/{id}/state")
    public ResponseEntity<?> transitionState(@PathVariable Long id, @RequestBody AgreementState state) {
        return service.transitionState(id, state)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('RECRUITER', 'ADMIN')")
    @PostMapping("/{id}/upload")
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            return service.uploadFile(id, file)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

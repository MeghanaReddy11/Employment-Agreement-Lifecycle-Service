/*package com.example.agreementservice.service;

public class AgreementService {
    
}*/
package com.example.agreementservice.service;

import com.example.agreementservice.model.*;
import com.example.agreementservice.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AgreementService {
    private final AgreementRepository repository;
    private final AuditLogRepository auditLogRepository;

    public AgreementService(AgreementRepository repository, AuditLogRepository auditLogRepository) {
        this.repository = repository;
        this.auditLogRepository = auditLogRepository;
    }

    public Agreement createDraft(Agreement agreement) {
        agreement.setState(AgreementState.DRAFT);
        agreement.setLastUpdated(LocalDateTime.now());
        return repository.save(agreement);
    }

    public Optional<Agreement> updateAgreement(Long id, Agreement updated) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(updated.getTitle());
            existing.setContent(updated.getContent());
            existing.setLastUpdated(LocalDateTime.now());
            return repository.save(existing);
        });
    }

    public Optional<Agreement> transitionState(Long id, AgreementState newState) {
        return repository.findById(id).map(agreement -> {
            AgreementState oldState = agreement.getState();
            agreement.setState(newState);
            agreement.setLastUpdated(LocalDateTime.now());

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            AuditLog log = new AuditLog();
            log.setAgreementId(id);
            log.setUsername(username);
            log.setFromState(oldState);
            log.setToState(newState);
            log.setTimestamp(LocalDateTime.now());
            auditLogRepository.save(log);

            return repository.save(agreement);
        });
    }

    public Optional<Agreement> uploadFile(Long agreementId, MultipartFile file) throws IOException {
        if (!file.getOriginalFilename().endsWith(".pdf") && !file.getOriginalFilename().endsWith(".jpg")) {
            throw new IOException("Invalid file type. Only PDF and JPG are supported.");
        }

        return repository.findById(agreementId).map(agreement -> {
            try {
                File targetFile = new File("uploads/" + file.getOriginalFilename());
                targetFile.getParentFile().mkdirs();
                try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                    fos.write(file.getBytes());
                }
                agreement.setFilePath(targetFile.getAbsolutePath());
                agreement.setLastUpdated(LocalDateTime.now());
                return repository.save(agreement);
            } catch (IOException e) {
                throw new RuntimeException("File upload failed", e);
            }
        });
    }
}


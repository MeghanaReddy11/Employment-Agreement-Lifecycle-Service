/*package com.example.agreementservice.model;

public class AuditLog {
    
}*/
package com.example.agreementservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agreementId;
    private String username;

    @Enumerated(EnumType.STRING)
    private AgreementState fromState;

    @Enumerated(EnumType.STRING)
    private AgreementState toState;

    private LocalDateTime timestamp;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAgreementId() { return agreementId; }
    public void setAgreementId(Long agreementId) { this.agreementId = agreementId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public AgreementState getFromState() { return fromState; }
    public void setFromState(AgreementState fromState) { this.fromState = fromState; }

    public AgreementState getToState() { return toState; }
    public void setToState(AgreementState toState) { this.toState = toState; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}


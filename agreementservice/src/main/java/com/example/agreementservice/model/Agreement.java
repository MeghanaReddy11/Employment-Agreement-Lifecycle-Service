/*package com.example.agreementservice.model;

public class Agreement {
    
}*/
package com.example.agreementservice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String filePath;

    @Enumerated(EnumType.STRING)
    private AgreementState state;

    private LocalDateTime lastUpdated;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public AgreementState getState() { return state; }
    public void setState(AgreementState state) { this.state = state; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}


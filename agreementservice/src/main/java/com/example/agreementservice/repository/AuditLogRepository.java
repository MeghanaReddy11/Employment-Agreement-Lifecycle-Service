/*package com.example.agreementservice.repository;

public class AuditLogRepository {
    
}*/
package com.example.agreementservice.repository;

import com.example.agreementservice.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {}


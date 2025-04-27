/*package com.example.agreementservice.repository;

public class AgreementRepository {
    
}*/
package com.example.agreementservice.repository;

import com.example.agreementservice.model.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {}


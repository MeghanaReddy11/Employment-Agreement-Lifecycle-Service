# Employment-Agreement-Lifecycle-Service
Create a system to handle the lifecycle of employment agreements.
🛠️ Requirements:
RESTful service using Spring Boot for:


Creating agreement draft


Updating agreement details


Submitting & signing agreement


Store agreements and states in a database


Use enums/state machine pattern for: DRAFT → REVIEW → SIGNED_BY_EMPLOYER → SIGNED_BY_CANDIDATE → COMPLETED


Add file upload (PDF/image of signature) support with storage in local directory or S3


🔍 Evaluation Criteria:
State management logic


File upload implementation


Entity relationships and version control


Use of service layer and repository abstraction



🔐 Security Additions:
Role-based restrictions:


ROLE_RECRUITER and ROLE_ADMIN – create/edit agreements.


ROLE_EMPLOYER – review and sign.


ROLE_JOBSEEKER – view and sign.


Secure file uploads:


Validate file types (PDF, JPG)


Store files with access controls (use S3 presigned URLs if cloud-based)


Add audit logs for all state transitions with user info.

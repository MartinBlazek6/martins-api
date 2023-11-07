-- db.migration/V1__create_resume_table.sql
CREATE TABLE resume (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        profession VARCHAR(255),
                        GDPR VARCHAR(255),
                        source_code_of_this_project VARCHAR(255),
                        martins_cv BOOLEAN
);

CREATE TABLE soft_skills (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             skill VARCHAR(255),
                             resume_id INT,
                             FOREIGN KEY (resume_id) REFERENCES resume(id)
);

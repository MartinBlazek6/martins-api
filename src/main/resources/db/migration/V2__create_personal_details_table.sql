-- db.migration/V2__create_personal_details_table.sql
CREATE TABLE personal_details (
                                  id INT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(255),
                                  email VARCHAR(255),
                                  phoneNumber VARCHAR(15),
                                  location VARCHAR(255),
                                  resume_id INT,
                                  FOREIGN KEY (resume_id) REFERENCES resume(id)
);

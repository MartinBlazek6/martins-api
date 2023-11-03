ALTER TABLE `user_role`
DROP FOREIGN KEY `user_role_ibfk_2`;
ALTER TABLE `user_role`
    CHANGE COLUMN `role_name` `role_id` VARCHAR(255) NOT NULL ;
ALTER TABLE `user_role`
    ADD CONSTRAINT `user_role_ibfk_2`
        FOREIGN KEY (`role_id`)
            REFERENCES `role` (`role_name`);

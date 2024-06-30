package com.martin.entity.records;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ChangeUserPasswordRecord(
        @NotBlank(message = "Username cannot be blank")
        String userName,

        @NotBlank(message = "New password cannot be blank")
        @Pattern(
                regexp = "(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
                message = "Password must be at least eight characters long, include one letter, one number, and one special character."
        )
        String newPassword,

        @NotBlank(message = "Repeated password cannot be blank")
        String repeatedNewPassword
) {
}
package com.martin.entity.records;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record ChangeUserPasswordRecord (
        String userName,
        String newPassword,
        String repeatedNewPassword
) {
}
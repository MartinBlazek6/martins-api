package com.martin.entity.records;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.martin.entity.Role;

import java.util.Set;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserRecord(
        String userName,
        String userFirstName,
        String userLastName,
        Set<Role> role
) {
}
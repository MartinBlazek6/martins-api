package com.martin.controller;

import com.martin.entity.User;
import com.martin.entity.records.ChangeUserPasswordRecord;
import com.martin.entity.records.UserRecord;
import com.martin.exceptions.UserAlreadyExistException;
import com.martin.exceptions.UserNotFoundException;
import com.martin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final String API_KEY = System.getenv("API_KEY");

    @PostMapping("/registerNewUser")
    public ResponseEntity<UserRecord> registerNewUser(
            @RequestBody User user,
            @RequestHeader(required = false) String apiKey) {
        user = userService.registerNewUser(user, API_KEY.equals(apiKey));
        return new ResponseEntity<>(new UserRecord(user.getUserName(), user.getUserFirstName(), user.getUserLastName(), user.getRole()), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/updateUserPassword")
    public ResponseEntity<String> changeUserPassword(@RequestBody ChangeUserPasswordRecord userPasswordRecord) {
        if (userPasswordRecord.newPassword().equals(userPasswordRecord.repeatedNewPassword())) {
            userService.changeUserPassword(userPasswordRecord.userName(), userPasswordRecord.newPassword());
            return new ResponseEntity<>("Passwords updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Passwords should be same", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

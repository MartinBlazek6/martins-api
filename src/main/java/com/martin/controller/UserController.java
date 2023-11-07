package com.martin.controller;

import com.martin.entity.User;
import com.martin.entity.records.UserRecord;
import com.martin.exceptions.UserAlreadyExistException;
import com.martin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping({"/registerNewUser"})
    public ResponseEntity<UserRecord> registerNewUser(@RequestBody User user) {
        try {
            user = userService.registerNewUser(user);
            return new ResponseEntity<>(new UserRecord(user.getUserName(), user.getUserFirstName(), user.getUserLastName(), user.getRole()), HttpStatus.ACCEPTED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}

package com.martin.controller;

import com.martin.entity.User;
import com.martin.entity.records.ChangeUserPasswordRecord;
import com.martin.entity.records.UserRecord;
import com.martin.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags = "API for managing users")
public class UserController {

    private final UserService userService;
    private final String API_KEY = System.getenv("API_KEY");

    @ApiOperation(value = "Register a new user", notes = "Endpoint to register a new user", response = UserRecord.class)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "User registered successfully"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping("/registerNewUser")
    public ResponseEntity<UserRecord> registerNewUser(
            @ApiParam(value = "User object containing user details", required = true)
            @Valid @RequestBody User user,
            @ApiParam(value = "API key for authentication")
            @RequestHeader(required = false) String apiKey) {
        user = userService.registerNewUser(user, API_KEY.equals(apiKey));
        return new ResponseEntity<>(new UserRecord(user.getUserName(), user.getUserFirstName(), user.getUserLastName(), user.getRole()), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Change user password", notes = "Endpoint to change user password", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Passwords updated"),
            @ApiResponse(code = 406, message = "Passwords should be same"),
            @ApiResponse(code = 403, message = "Access denied")
    })
    @PreAuthorize("hasRole('Admin')")
    @PatchMapping("/updateUserPassword")
    public ResponseEntity<String> changeUserPassword(
            @ApiParam(value = "Record containing the username and new passwords", required = true)
            @RequestBody @Valid ChangeUserPasswordRecord userPasswordRecord) {
        if (userPasswordRecord.newPassword().equals(userPasswordRecord.repeatedNewPassword())) {
            userService.changeUserPassword(userPasswordRecord.userName(), userPasswordRecord.newPassword());
            return new ResponseEntity<>("Passwords updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Passwords should be same", HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

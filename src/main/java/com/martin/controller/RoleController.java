package com.martin.controller;

import com.martin.entity.Role;
import com.martin.service.RoleService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(tags ="API for managing roles")
public class RoleController {

    private final RoleService roleService;

    @ApiOperation(value = "Create a new role", notes = "Endpoint to create a new role", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Role created successfully"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    @PostMapping("/createNewRole")
    public ResponseEntity<Role> createNewRole(
            @ApiParam(value = "Role object containing role details", required = true)
            @RequestBody Role role) {
        Role createdRole = roleService.createNewRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }
}

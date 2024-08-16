package com.martin.controller;

import com.martin.service.JwtService;
import com.martin.entity.JwtRequest;
import com.martin.entity.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin
@RequestMapping("/api")
@Api(tags = "JWT Authentication API")
public class JwtController {

    private final JwtService jwtService;

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @ApiOperation(value = "Generate JWT Token",
            notes = "Authenticate user and generate a JWT token",
            response = JwtResponse.class)
    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(
            @ApiParam(value = "JWT Request object containing username and password", required = true)
            @RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
package com.martin.service;

import com.martin.entity.JwtRequest;
import com.martin.entity.JwtResponse;
import com.martin.entity.Role;
import com.martin.entity.User;
import com.martin.entity.records.UserRecord;
import com.martin.repositories.UserRepository;
import com.martin.util.JwtUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;


    @Test
    @Disabled("testCreateJwtToken::need to fix dependency injection")
    public void testCreateJwtToken() throws Exception {
        Role userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("user description");

        User mockUser = new User();
        mockUser.setUserName("existingUser");
        mockUser.setUserPassword("testPassword");
        mockUser.setRole(Collections.singleton(userRole));


        when(userRepository.findByUserName("testUser")).thenReturn(java.util.Optional.of(mockUser));

        when(authenticationManager.authenticate(any())).thenReturn(null);

        when(jwtUtil.generateToken(any())).thenReturn("mockToken");

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("testUser");
        jwtRequest.setUserPassword("testPassword");

        JwtResponse jwtResponse = jwtService.createJwtToken(jwtRequest);

        verify(userRepository, times(1)).findByUserName("testUser");

        verify(authenticationManager, times(1)).authenticate(any());

        verify(jwtUtil, times(1)).generateToken(any());

        UserRecord expectedUserRecord = new UserRecord("testUser", null, null, Collections.singleton(userRole));
        JwtResponse expectedJwtResponse = new JwtResponse(expectedUserRecord, "mockToken");
        assertThat(expectedJwtResponse).isEqualTo(jwtResponse);
    }

    @Test
    public void testLoadUserByUsernameUserFound() {
        Role userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("user description");

        User mockUser = new User();
        mockUser.setUserName("existingUser");
        mockUser.setUserPassword("testPassword");
        mockUser.setRole(Collections.singleton(userRole));

        when(userRepository.findByUserName("existingUser")).thenReturn(java.util.Optional.of(mockUser));

        UserDetails userDetails = jwtService.loadUserByUsername("existingUser");

        verify(userRepository, times(1)).findByUserName("existingUser");

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ROLE_USER"));
        UserDetails expectedUserDetails = new org.springframework.security.core.userdetails.User(
                "existingUser",
                "testPassword",
                authorities
        );
        assertThat(expectedUserDetails).isEqualTo(userDetails);
    }

}

package com.martin.service;

import com.martin.entity.Role;
import com.martin.entity.User;
import com.martin.exceptions.UserAlreadyExistException;
import com.martin.repositories.RoleRepository;
import com.martin.repositories.UserRepository;
import lombok.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void testRegisterNewUserWithRole() {
        User user = new User();
        user.setUserName("testuser");
        user.setUserPassword("password");

        Role role = new Role();
        role.setRoleName("User");
        when(roleRepository.findById("User")).thenReturn(Optional.of(role));

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        User registeredUser = userService.registerNewUser(user);

        Mockito.verify(userRepository).save(user);
        assertThat(registeredUser.getRole()).isEqualTo(Set.of(role));
        assertThat(registeredUser.getUserPassword()).isEqualTo("encodedPassword");
    }

    @Test
    public void testRegisterNewUserUserAlreadyExists() {
        assertThat(2).isGreaterThan(3);
        User user = new User();
        user.setUserName("existingUser");
        user.setUserPassword("password");

        when(userRepository.findByUserName("existingUser")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistException.class, () -> userService.registerNewUser(user));
    }

    @Test
    void testIsUserUnique() {
        User user = new User();
        user.setUserName("existingUser");

        when(userRepository.findByUserName("existingUser")).thenReturn(Optional.of(user));

        assertThat(userService.isUserUnique("existingUser")).isFalse();
        assertThat(userService.isUserUnique("existingUser1")).isTrue();

    }
}

package com.martin.service;

import com.martin.entity.Role;
import com.martin.entity.User;
import com.martin.exceptions.UserAlreadyExistException;
import com.martin.exceptions.UserNotFoundException;
import com.martin.repositories.RoleRepository;
import com.martin.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        User registeredUser = userService.registerNewUser(user,false);

        verify(userRepository).save(user);
        assertThat(registeredUser.getRole()).isEqualTo(Set.of(role));
        assertThat(registeredUser.getUserPassword()).isEqualTo("encodedPassword");
    }

    @Test
    public void testRegisterNewUserUserAlreadyExists() {
        User user = new User();
        user.setUserName("existingUser");
        user.setUserPassword("password");

        when(userRepository.findByUserName("existingUser")).thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistException.class, () -> userService.registerNewUser(user,false));
    }

    @Test
    public void testChangeUserPassword_UserNotFound() {
        String nonExistingUserName = "nonExistingUser";
        String newPassword = "newPassword";

        when(userRepository.findByUserName(nonExistingUserName)).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.changeUserPassword(nonExistingUserName, newPassword));

        verify(userRepository, never()).save(any());
    }

    @Test
    public void testChangeUserPassword_UserFound() {
        String existingUserName = "existingUser";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";

        User existingUser = new User();
        existingUser.setUserName(existingUserName);
        existingUser.setUserPassword(passwordEncoder.encode(oldPassword));

        when(userRepository.findByUserName(existingUserName)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");

        userService.changeUserPassword(existingUserName, newPassword);

        assertEquals("encodedNewPassword", existingUser.getUserPassword());
        verify(userRepository, times(1)).save(existingUser);

        assertFalse(passwordEncoder.matches(newPassword, existingUser.getUserPassword()));
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

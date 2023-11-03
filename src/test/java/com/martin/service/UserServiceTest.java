package com.martin.service;

import com.martin.entity.User;
import com.martin.exceptions.UserAlreadyExistException;
import com.martin.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testRegisterNewUserUserAlreadyExists() {
        User user = new User();
        user.setUserName("existingUser");
        user.setUserPassword("password");

        when(userRepository.findByUserName("existingUser")).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistException.class, () -> userService.registerNewUser(user));
    }
}

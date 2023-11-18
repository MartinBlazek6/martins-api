package com.martin.service;

import com.martin.exceptions.UserAlreadyExistException;
import com.martin.repositories.RoleRepository;
import com.martin.repositories.UserRepository;
import com.martin.entity.Role;
import com.martin.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    protected boolean isUserUnique(String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isEmpty();
    }

    /**
     * Register a new user in the system.
     *
     * @param user     The user to be registered.
     * @param isAdmin  A boolean indicating whether the user should have admin privileges.
     * @return The registered user.
     * @throws UserAlreadyExistException If a user with the same username already exists.
     */
    public User registerNewUser(User user,boolean isAdmin) {
        if (isUserUnique(user.getUserName())){
            Role role = isAdmin ? roleRepository.findById("Admin").orElseThrow(()-> new RuntimeException("role Admin not found"))
                                : roleRepository.findById("User").orElseThrow(()-> new RuntimeException("role User not found"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));

            return userRepository.save(user);
        }
        throw new UserAlreadyExistException(user.getUserName() + ": already exist");
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

package com.martin.service;

import com.martin.entity.Role;
import com.martin.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testCreateNewRole() {
        Role mockRole = new Role();
        mockRole.setRoleName("USER");

        when(roleRepository.save(any())).thenReturn(mockRole);

        Role createdRole = roleService.createNewRole(mockRole);

        assertThat(createdRole).isEqualTo(mockRole);

        verify(roleRepository, times(1)).save(mockRole);
    }
}

package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.model.user.UserAccount;
import com.techreturners.weatheripe.repository.UserAccountRepository;
import com.techreturners.weatheripe.security.dto.SignupRequestDTO;
import com.techreturners.weatheripe.exception.userauthentication.EmailAlreadyExistsException;
import com.techreturners.weatheripe.exception.userauthentication.UsernameAlreadyExistsException;
import com.techreturners.weatheripe.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class UserServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private UserAccount expectedUser;

    SignupRequestDTO newUserReq;
    @BeforeEach
    public void init() {
        expectedUser = UserAccount.builder().userName("john")
                .email("john@isemail.com")
                .password("fakerpassword")
                .build();

        newUserReq = SignupRequestDTO.builder().username(expectedUser.getUserName())
                .email(expectedUser.getEmail())
                .password(expectedUser.getPassword())
                .build();
    }


    @Test
    void testCreateUser() {

        when(userAccountRepository.existsByUserName(expectedUser.getUserName())).thenReturn(false);
        when(userAccountRepository.existsByEmail(expectedUser.getEmail())).thenReturn(false);
        when(userAccountRepository.save(expectedUser)).thenReturn(expectedUser);
        when(passwordEncoder.encode(expectedUser.getPassword())).thenReturn(expectedUser.getPassword());

        UserAccount user = userService.create(newUserReq);
        verify(passwordEncoder, times(1)).encode(newUserReq.getPassword());
        verify(userAccountRepository, times(1)).save(any(UserAccount.class));
        assertEquals(expectedUser.getUserName(), user.getUserName());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getPassword(), user.getPassword());
    }

    @Test
    public void testEmailAlreadyExists() {

        when(userAccountRepository.existsByEmail(expectedUser.getEmail())).thenReturn(true);

        EmailAlreadyExistsException thrown = Assertions.assertThrows(EmailAlreadyExistsException.class, () ->
            userService.create(newUserReq)
        );

        assertEquals(String.format("email %s already exists", expectedUser.getEmail()),thrown.getMessage());
    }


    @Test
    public void testUsernameAlreadyExists() {
        when(userAccountRepository.existsByUserName(expectedUser.getUserName())).thenReturn(true);
        UsernameAlreadyExistsException thrown = Assertions.assertThrows(UsernameAlreadyExistsException.class, () ->
            userService.create(newUserReq)
        );
        assertEquals(String.format("Username %s already exists.", expectedUser.getUserName()),thrown.getMessage());
    }

}

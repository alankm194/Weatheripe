package com.techreturners.weatheripe.service;

import com.techreturners.weatheripe.model.ERole;
import com.techreturners.weatheripe.request.LoginRequest;
import com.techreturners.weatheripe.response.SuccessfulLoginResponse;
import com.techreturners.weatheripe.security.JwtUtils;
import com.techreturners.weatheripe.security.UserDetailsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import static org.mockito.Mockito.*;


@DataJpaTest
public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Test
    public void testSuccessfulLogin() {
        LoginRequest request = LoginRequest.builder().username("Alan").password("Password").build();
        UserDetailsImpl userDetails = new UserDetailsImpl(1L,
                request.getUsername(),
                "alan@mail.com",
                request.getPassword(),
                new SimpleGrantedAuthority(ERole.ROLE_USER.name()));
        SuccessfulLoginResponse expectedResult = SuccessfulLoginResponse.builder()
                .token("FAKEJWT!@#!123")
                .username(userDetails.getUsername())
                .build();

        Authentication mockAuthentication = mock(Authentication.class);
        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())))
                .thenReturn(mockAuthentication);
        when(jwtUtils.generateJwtToken(mockAuthentication)).thenReturn("FAKEJWT!@#!123");

        when(mockAuthentication.getPrincipal()).thenReturn(userDetails);

        SuccessfulLoginResponse successfulLoginResponse = authService.login(request);

        verify(jwtUtils, times(1)).generateJwtToken(mockAuthentication);
        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        verify(mockAuthentication, times(1)).getPrincipal();
        Assertions.assertEquals(expectedResult, successfulLoginResponse);
    }
}

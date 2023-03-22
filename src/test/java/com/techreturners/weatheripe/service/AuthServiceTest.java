package com.techreturners.weatheripe.service;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;


@DataJpaTest
public class AuthServiceTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private AuthenticationManager authenticationManager;

//    @Mock
//    private JwtUtils jwtUtils;

//    @Test
//    public void testSuccessfulLogin() {
//        LoginRequest request = LoginRequest.builder().username("Alan").password("Password").build();
//        UserDetailsImpl userDetails = new UserDetailsImpl(1L,
//                request.getUsername(),
//                "alan@mail.com",
//                request.getPassword(),
//                new SimpleGrantedAuthority(ERole.ROLE_USER.name()));
//        SuccessfulLoginResponse expectedResult = SuccessfulLoginResponse.builder()
//                .token("FAKEJWT!@#!123")
//                .username(userDetails.getUsername())
//                .build();
//
//        Authentication mockAuthentication = mock(Authentication.class);
//        when(authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())))
//                .thenReturn(mockAuthentication);
//        when(jwtUtils.generateJwtToken(mockAuthentication)).thenReturn("FAKEJWT!@#!123");
//
//        when(mockAuthentication.getPrincipal()).thenReturn(userDetails);
//
//        SuccessfulLoginResponse successfulLoginResponse = authService.login(request);
//
//        verify(jwtUtils, times(1)).generateJwtToken(mockAuthentication);
//        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        verify(mockAuthentication, times(1)).getPrincipal();
//        Assertions.assertEquals(expectedResult, successfulLoginResponse);
//    }
}

package com.techreturners.weatheripe.security.service;

import com.techreturners.weatheripe.security.dto.LoginRequestDTO;
import com.techreturners.weatheripe.security.dto.SuccessfulLoginDTO;
import com.techreturners.weatheripe.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.util.stream.Collectors.joining;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public SuccessfulLoginDTO login(LoginRequestDTO loginRequestDTO) throws BadCredentialsException{

            var authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

            var user = (UserDetailsImpl) authentication.getPrincipal();

            var now = Instant.now();
            var expiry = 36000L;

            var scope =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(joining(" "));

            var claims =
                    JwtClaimsSet.builder()
                            .issuer("com.techreturners")
                            .issuedAt(now)
                            .expiresAt(now.plusSeconds(expiry))
                            .subject(user.getUsername())
                            .claim("roles", scope)
                            .build();

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
            return SuccessfulLoginDTO.builder().username(user.getUsername()).token(token).build();
    }

    public String extractUsernameFromToken(String token){
        return jwtDecoder.decode(token.substring("Bearer ".length())).getClaimAsString("sub");
    }
}

package com.jokerP.store.auth;


import com.jokerP.store.commons.NotFoundException;
import com.jokerP.store.users.UserDto;
import com.jokerP.store.users.UserMapper;
import com.jokerP.store.users.UserRepository;
import com.jokerP.store.util.Constants;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtService jwtService;
    private final JwtConfig jwtConfig;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginUserRequest request,
            HttpServletResponse response
    ) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var cookie = authService.createCookie(
                "refreshToken",
                jwtService.generateRefreshToken(user).toString(),
                "/auth/refresh",
                jwtConfig.getRefreshTokenExpiration()
        );
        response.addCookie(cookie);

        return  ResponseEntity.ok(new JwtResponse(jwtService.generateAccessToken(user).toString()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(
            @CookieValue("refreshToken") String refreshToken
    ) {
        var jwt = jwtService.parseToken(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        var userId = jwt.getUserId();
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(Constants.Message.USER_NOT_FOUND)
        );

        var accessToken = jwtService.generateAccessToken(user);
        return   ResponseEntity.ok(new JwtResponse(accessToken.toString()));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        var user = authService.getCurrentUser();
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}

package com.jokerP.store.auth;

import com.jokerP.store.commons.NotFoundException;
import com.jokerP.store.users.User;
import com.jokerP.store.users.UserRepository;
import com.jokerP.store.util.Constants;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();

        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(Constants.Message.USER_NOT_FOUND)
        );
    }

    public Cookie createCookie(String key, String value, String path, int maxAge) {
        var cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(true);

        return cookie;
    }
}

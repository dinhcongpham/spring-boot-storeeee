package com.jokerP.store.users;

import com.jokerP.store.commons.BadRequestException;
import com.jokerP.store.commons.NotFoundException;
import com.jokerP.store.auth.ChangePasswordRequest;
import com.jokerP.store.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Iterable<UserDto> getAllUsers(String sortBy) {
        if (!Set.of("name", "email").contains(sortBy)) {
            sortBy = "name";
        }

        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getUserById(Long id) {
        var user = handleGetUser(id);
        return userMapper.toDto(user);
    }

    public UserDto registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException(Constants.Message.EMAIL_ALREADY_EXISTS);
        }
        var user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    public UserDto updateUser(Long id, UpdateUserRequest request) {
        var user = handleGetUser(id);

        userMapper.update(request, user);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Long id) {
        var user = handleGetUser(id);
        userRepository.delete(user);
    }

    public void changePassword(Long id, ChangePasswordRequest request) {
        var user = handleGetUser(id);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadRequestException(Constants.Message.PASSWORD_DOES_NOT_MATCH);
        }

        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    private User handleGetUser(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(Constants.Message.USER_NOT_FOUND)
        );
    }
}

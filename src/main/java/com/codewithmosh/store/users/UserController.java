package com.codewithmosh.store.users;

import com.codewithmosh.store.users.dto.request.UserChangePassordRequest;
import com.codewithmosh.store.users.dto.request.UserCreateRequest;
import com.codewithmosh.store.users.dto.request.UserUpdateRequest;
import com.codewithmosh.store.users.dto.response.UserDto;
import com.codewithmosh.store.users.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * UserController
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(
        @Valid @RequestBody UserCreateRequest dto,
        UriComponentsBuilder uriBuilder
    ) {
        var user = userMapper.toEntity(dto);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriBuilder
            .path("/users/{id}")
            .buildAndExpand(userDto.id())
            .toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @GetMapping
    public Iterable<UserDto> getAllUsers() {
        return userRepository
            .findAll()
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        System.out.println(user.getId());
        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
        @PathVariable Long id,
        @Valid @RequestBody UserChangePassordRequest request
    ) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        var oldPassword = request.oldPassword();
        if (
            !user.getPassword().equals(oldPassword)
        ) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setPassword(request.newPassword());
        userRepository.save(user);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUserDto(
        @PathVariable(name = "id") Long id,
        @Valid @RequestBody UserUpdateRequest request
    ) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        // var email = request.getName();
        // var password = request.getPassword();
        // if (email != null) user.setEmail(email);
        // if (password != null) user.setPassword(password);
        userMapper.update(request, user);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

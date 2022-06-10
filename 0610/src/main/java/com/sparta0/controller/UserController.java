package com.sparta0.controller;

import com.sparta0.dto.UserDto;
import com.sparta0.model.User;
import com.sparta0.repository.UserRepository;
import com.sparta0.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/get")
    public List<User> list() {
        return userRepository.findAll();
    }

    @PostMapping("/create")
    public Long create(@RequestBody UserDto requestDto) {
        User user = userRepository.save(new User(requestDto));
        return user.getId();
    }

    @GetMapping("/read/{id}")
    public User findOne(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserDto requestDto) {
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return id;
    }

    /**
     *나이와 성별이 같은 경우 추천
     */
    @GetMapping("/recommend/{id}")
    public List<User> recommend(@PathVariable Long id) {
        return userService.recommend(id);
    }
}

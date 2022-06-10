package com.sparta0.service;

import com.sparta0.dto.UserDto;
import com.sparta0.model.User;
import com.sparta0.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long update(Long id, UserDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        user.update(requestDto);
        return user.getId();
    }

    public List<User> recommend(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return userRepository.findByGenderAndAge(user.getGender(), user.getAge());
    }
}

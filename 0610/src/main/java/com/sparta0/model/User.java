package com.sparta0.model;

import com.sparta0.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "USERS")
@NoArgsConstructor
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private int age;

    private int gender;

    public User(UserDto requestDto) {
        this.name = requestDto.getName();
        this.email = requestDto.getEmail();
        this.age = requestDto.getAge();
        this.gender = requestDto.getGender();
    }

    public void update(UserDto requestDto) {
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.gender = requestDto.getGender();
    }
}

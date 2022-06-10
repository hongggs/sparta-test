package com.sparta0.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;

    private String email;

    private int age;

    private int gender;

    public UserDto(String name, int age, int gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

package com.sparta7.entity;

import com.sparta7.dto.MemberRequestDto;
import com.sparta7.dto.MemberResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private int age;

    private int gender;

    public Member(String email, String name, int age, int gender) {
        this.email = email;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Member(MemberRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.gender = requestDto.getGender();
    }

    public Member(MemberResponseDto responseDto) {
        this.email = responseDto.getEmail();
        this.name = responseDto.getName();
        this.age = responseDto.getAge();
        this.gender = responseDto.getGender();
    }

    public void update(MemberRequestDto requestDto) {
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.gender = requestDto.getGender();
    }
}

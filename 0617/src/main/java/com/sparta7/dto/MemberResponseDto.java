package com.sparta7.dto;

import com.sparta7.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private String email;

    private String name;

    private int age;

    private int gender;


//    private String password;
//
//
//    public static MemberResponseDto of(Member member) {
//        return new MemberResponseDto(member.getEmail(), member.getPassword());
//    }
}

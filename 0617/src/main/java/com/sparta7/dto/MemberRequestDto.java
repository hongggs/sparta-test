package com.sparta7.dto;

import com.sparta7.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    private String email;

    private String name;

    private int age;

    private int gender;
}

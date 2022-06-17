package com.sparta7.service;

import com.sparta7.dto.LoginRequestDto;
import com.sparta7.dto.MemberRequestDto;
import com.sparta7.entity.Member;
import com.sparta7.jwt.TokenProvider;
import com.sparta7.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Member create(MemberRequestDto requestDto) {
        return memberRepository.save(new Member(requestDto));
    }

    @Transactional
    public String login(LoginRequestDto requestDto) {
        boolean result = memberRepository.existsByEmail(requestDto.getEmail());
        if (result) {
            String token = tokenProvider.createToken(requestDto.getEmail());
            System.out.println("token = " + token);
            return token;
        } else {
            return "정보가 없습니다.";
        }

    }

    public Long update(Long id, MemberRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        member.update(requestDto);
        return member.getId();
    }

    public List<Member> recommend(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return memberRepository.findByGenderAndAge(member.getGender(), member.getAge());
    }
}
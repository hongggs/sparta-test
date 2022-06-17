package com.sparta7.controller;

import com.sparta7.dto.LoginRequestDto;
import com.sparta7.dto.MemberRequestDto;
import com.sparta7.entity.Member;
import com.sparta7.repository.MemberRepository;
import com.sparta7.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/create")
    public Long create(@RequestBody MemberRequestDto requestDto) {
        Member member = memberService.create(requestDto);
        return member.getId();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto requestDto) {
        return memberService.login(requestDto);
    }

//    @GetMapping("/read/{id}")
//    public Member findOne(@PathVariable Long id) {
//        return memberRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//    }
//
//    @PutMapping("/update/{id}")
//    public Long update(@PathVariable Long id, @RequestBody MemberRequestDto requestDto) {
//        return memberService.update(id, requestDto);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public Long delete(@PathVariable Long id) {
//        memberRepository.deleteById(id);
//        return id;
//    }
//
//    /**
//     *나이와 성별이 같은 경우 추천
//     */
//    @GetMapping("/recommend/{id}")
//    public List<Member> recommend(@PathVariable Long id) {
//        return memberService.recommend(id);
//    }


}

//package com.study.boot09.controller;
//
//import com.study.boot09.domain.Member;
//import com.study.boot09.persistence.MemberRepositroy;
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Log
//@Controller
//@RequestMapping("/member/")
//public class MemberController {
//    @Autowired
//    PasswordEncoder pwEncoder;
//
//    @Autowired
//    MemberRepositroy repo;
//
//    @GetMapping("/join")
//    public void join(){
//
//    }
//    @Transactional
//    @PostMapping("/join")
//    public String joinPost(@ModelAttribute("member") Member member){
//        log.info("Member: "+member);
//        String encrytPw= pwEncoder.encode(member.getUpw());
//        log.info("en: "+encrytPw);
//        member.setUpw(encrytPw);
//        repo.save(member);
//        return "/member/joinResult";
//    }
//}

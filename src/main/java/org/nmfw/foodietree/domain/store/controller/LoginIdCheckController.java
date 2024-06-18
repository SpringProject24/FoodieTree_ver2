package org.nmfw.foodietree.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nmfw.foodietree.domain.store.service.LoginIdCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class LoginIdCheckController {

    private LoginIdCheckService loginIdCheckService;

    // 아이디(이메일) 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword) {
        boolean flag = loginIdCheckService.checkIdentifier(type, keyword);
        log.debug("{}중복ㅊ크 결과? {}",type,flag);
        return ResponseEntity
                .ok()
                .body(flag);
    }


}
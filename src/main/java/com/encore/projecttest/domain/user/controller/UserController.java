package com.encore.projecttest.domain.user.controller;

import com.encore.projecttest.domain.user.dto.UserInfoDto;
import com.encore.projecttest.domain.user.dto.UserSignUpDto;
import com.encore.projecttest.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Map;


// 추후 예외처리 확실하게 해줄것.
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, Object>> signup(
            // @Valid 어노테이션을 이용해 RequestBody 검증
            @Valid @RequestBody UserSignUpDto userSignUpDto) throws Exception{
        userService.signUp(userSignUpDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/userinfo/{email}")
    public ResponseEntity<Map<String, Object>> findUserByEmail(@RequestParam(value = "email")String email){
        UserInfoDto userInfoDto = null;
        try{
            userInfoDto = userService.findByEmail(email);
            log.info(userInfoDto.toString());
            return ResponseEntityController.responseMessage(userInfoDto, HttpStatus.OK);
        } catch(EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntityController.errResponse(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}

package com.freestrokes.auth.controller;

import com.freestrokes.aop.LogExecutionTime;
import com.freestrokes.constants.PathConstants;
import com.freestrokes.auth.dto.AuthDto;
import com.freestrokes.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = PathConstants.LOGIN, produces = "application/json")
    @Operation(
        summary = "로그인",
        description = "아이디와 패스워드를 이용하여 로그인을 한다."
    )
    // NOTE: AOP 확인을 위해 추가 (@LogExecutionTime)
    @LogExecutionTime
    public ResponseEntity<?> login(
        @RequestBody AuthDto.RequestDto loginRequestDto,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse
    ) throws Exception {
        return authService.login(loginRequestDto, httpServletRequest, httpServletResponse);
    }

}

package com.freestrokes.auth.service;

import com.freestrokes.auth.dto.AuthDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 인증 요청을 위한 서비스 인터페이스
 */
public interface AuthRequestService {

    /**
     * 로그인
     * @param loginRequestDto 로그인 정보
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 로그인 인증 정보
     */
    ResponseEntity<?> login(
        AuthDto.RequestDto loginRequestDto,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse
    );

}

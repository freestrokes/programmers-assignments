package com.freestrokes.controller;

import com.freestrokes.constants.PathConstants;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {

    @GetMapping(path = PathConstants.HEALTH_CHECK, produces = "application/json")
    @Operation(
        summary = "헬스 체크",
        description = "헬스 체크를 한다."
    )
    public ResponseEntity<?> healthCheck() throws Exception {
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}

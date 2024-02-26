package com.fvelasquez.evaluacion.controller;

import com.fvelasquez.evaluacion.dto.request.UserRequest;
import com.fvelasquez.evaluacion.dto.response.UserResponse;
import com.fvelasquez.evaluacion.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@Validated
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        UserResponse response= userService.create(request);
        return ResponseEntity.created(URI.create("/api/user/"+response.getId().toString())).body(response);
    }

}

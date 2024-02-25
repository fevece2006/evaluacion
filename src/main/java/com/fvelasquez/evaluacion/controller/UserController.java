package com.fvelasquez.evaluacion.controller;


import com.fvelasquez.evaluacion.dto.request.UserRequest;
import com.fvelasquez.evaluacion.dto.response.UserResponse;
import com.fvelasquez.evaluacion.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
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

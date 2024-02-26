package com.fvelasquez.evaluacion.service;

import com.fvelasquez.evaluacion.dto.request.UserRequest;
import com.fvelasquez.evaluacion.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse create(UserRequest request);




}

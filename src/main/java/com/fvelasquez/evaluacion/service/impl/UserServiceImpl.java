package com.fvelasquez.evaluacion.service.impl;

import com.fvelasquez.evaluacion.dto.request.UserRequest;
import com.fvelasquez.evaluacion.dto.response.UserResponse;
import com.fvelasquez.evaluacion.entity.PhoneEntity;
import com.fvelasquez.evaluacion.entity.UserEntity;
import com.fvelasquez.evaluacion.repository.UserRepository;
import com.fvelasquez.evaluacion.service.UserService;
import com.fvelasquez.evaluacion.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Transactional
    @Override
    public UserResponse create(UserRequest request) {

        List<PhoneEntity> phones = request.getPhones().stream().map(phoneRequest -> {
            PhoneEntity phoneEntity = PhoneEntity.builder()
                    .number(phoneRequest.getNumber())
                    .cityCode(phoneRequest.getCitycode())
                    .contryCode(phoneRequest.getContrycode())
                    .build();
            return phoneEntity;
        }).toList();
        Set<PhoneEntity> phoneEntities = new HashSet<>(phones);


        UserEntity instance = UserEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword()).isActive(true).build();
        instance.setPhones(phoneEntities);


       UserEntity response = userRepository.save(instance);

        JwtUtil jwtToken = buildTokenJWT(response);
        response.setLastLogin(response.getCreatedDate());
        response.setToken(jwtToken.toString());
        return this.toResponse(response);

    }



    private UserResponse toResponse(UserEntity entity) {
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(entity, userResponse);
        return userResponse;
    }

    private static JwtUtil buildTokenJWT(UserEntity response) {
        LocalDateTime ldt = LocalDateTime.now().plusDays(90);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        long date = zonedDateTime.toInstant().toEpochMilli();
        JwtUtil jwtToken = new JwtUtil(response.getName(), response.getEmail(),date);
        return jwtToken;
    }

}

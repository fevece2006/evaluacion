package com.fvelasquez.evaluacion.service.impl;

import com.fvelasquez.evaluacion.dto.request.UserRequest;
import com.fvelasquez.evaluacion.dto.response.UserResponse;
import com.fvelasquez.evaluacion.entity.PhoneEntity;
import com.fvelasquez.evaluacion.entity.UserEntity;
import com.fvelasquez.evaluacion.repository.PhoneRepository;
import com.fvelasquez.evaluacion.repository.UserRepository;
import com.fvelasquez.evaluacion.service.UserService;
import com.fvelasquez.evaluacion.utils.ConstantesExcepcion;
import com.fvelasquez.evaluacion.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PhoneRepository phoneRepository;


    @Transactional
    @Override
    public UserResponse create(UserRequest request) {

        if(userRepository.findByEmail(request.getEmail().trim()) != null ){
            throw new RuntimeException(ConstantesExcepcion.EXCEPTION_CORREO_REGISTRADO);
        }

        LocalDateTime localDateTime = LocalDateTime.now();

        UserEntity userEntity = new UserEntity();
        userEntity.setName(request.getName());
        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(request.getPassword());
        userEntity.setIsActive(true);

        userEntity.setLastLogin(localDateTime);
        userEntity.setCreatedDate(localDateTime);
        userEntity.setModifiedDate(localDateTime);

        UserEntity response = userRepository.save(userEntity);


        if (request.getPhones() != null) {
            request.getPhones().stream()
                    .map(
                            phoneRequest -> {
                                PhoneEntity phoneEntity = new PhoneEntity();
                                phoneEntity.setNumber(phoneRequest.getNumber());
                                phoneEntity.setCityCode(phoneRequest.getCitycode());
                                phoneEntity.setContryCode(phoneRequest.getContrycode());
                                phoneEntity.setUser(userEntity);
                                return phoneEntity;
                            }
                    ).map(
                            phoneRepository::save
                    )
                    .collect(Collectors.toList());
        }

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

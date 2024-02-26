package com.fvelasquez.evaluacion.repository;

import com.fvelasquez.evaluacion.entity.UserEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Override
    <S extends UserEntity> S save(S entity);

    @Query(value = "SELECT * FROM users WHERE email LIKE ?1%", nativeQuery = true)
    UserEntity findByEmail(String email);

}

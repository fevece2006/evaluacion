package com.fvelasquez.evaluacion.repository;

import com.fvelasquez.evaluacion.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

}

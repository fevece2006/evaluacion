package com.fvelasquez.evaluacion.repository;


import com.fvelasquez.evaluacion.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {
}

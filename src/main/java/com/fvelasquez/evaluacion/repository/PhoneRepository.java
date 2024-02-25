package com.fvelasquez.evaluacion.repository;


import com.fvelasquez.evaluacion.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface PhoneRepository extends JpaRepository<PhoneEntity, UUID> {
}

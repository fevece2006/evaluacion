package com.fvelasquez.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="phones")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_generator")
    private Long id;

    private String number;
    @Column(name = "city_code", length = 5)
    private String cityCode;
    @Column(name = "country_code", length = 5)
    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

}

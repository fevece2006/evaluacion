package com.fvelasquez.evaluacion.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="phones")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    private UUID id;

    private String number;

    @Column(name = "city_code", length = 5)
    private String cityCode;

    @Column(name = "country_code", length = 5)
    private String contryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity user;

}

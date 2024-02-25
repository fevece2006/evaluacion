package com.fvelasquez.evaluacion.entity;

import com.fvelasquez.evaluacion.bean.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends AuditableEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "email", length = 300, unique = true)
    private String email;

    @Column(name = "password", length = 350)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<PhoneEntity> phones;

    @Column(name = "token", length = 500)
    private String token;
    @Column(name = "last_login", columnDefinition = "timestamp")
    private LocalDateTime lastLogin;
    @Column(name = "is_active", columnDefinition = "boolean default TRUE")
    private Boolean isActive;

}

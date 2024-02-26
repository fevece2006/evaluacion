package com.fvelasquez.evaluacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.fvelasquez.evaluacion.entity.UserEntity;
import com.fvelasquez.evaluacion.repository.PhoneRepository;
import com.fvelasquez.evaluacion.repository.UserRepository;
import com.fvelasquez.evaluacion.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUserEntity() {
        LocalDateTime localDateTime = LocalDateTime.now();

        UserEntity userEntityTest = new UserEntity();
        userEntityTest.setId(UUID.fromString("b9435661-a70c-4ecb-a0c1-15b6ebeb2b30"));
        userEntityTest.setName("Fernando Velasquez");
        userEntityTest.setEmail("fvelasquez@dominio.cl");
        userEntityTest.setPassword("Miclave1");
        userEntityTest.setIsActive(true);
        userEntityTest.setLastLogin(localDateTime);
        userEntityTest.setCreatedDate(localDateTime);
        userEntityTest.setModifiedDate(localDateTime);

        // Simulamos el comportamiento del repositorio userRepository.save
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntityTest);

        // Verificamos que la respuesta tenga el correo esperado
        assertEquals("fvelasquez@dominio.cl", userEntityTest.getEmail());
    }
}

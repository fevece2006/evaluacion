package com.fvelasquez.evaluacion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonPropertyOrder({"id","created","modified", "last_login","token","isactive"})
public class UserResponse {
    private UUID id;
    @JsonProperty("created")
    private LocalDateTime createdDate;
    @JsonProperty("modified")
    private LocalDateTime modifiedDate;
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;
    private String token;
    @JsonProperty("isactive")
    private Boolean isActive;

}

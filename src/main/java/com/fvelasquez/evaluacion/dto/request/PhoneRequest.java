package com.fvelasquez.evaluacion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneRequest{
    private String number;
    private String citycode;
    private String contrycode;
}

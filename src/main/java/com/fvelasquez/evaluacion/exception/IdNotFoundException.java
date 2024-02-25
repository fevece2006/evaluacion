package com.fvelasquez.evaluacion.exception;

public class IdNotFoundException extends  RuntimeException{

    private static final String ERROR_MESSAGE= "Identificador del usuario no existe";

    public IdNotFoundException(){
        super(ERROR_MESSAGE);
    }

}

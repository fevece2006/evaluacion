package com.fvelasquez.evaluacion.service;

public interface CrudService<RQ, RS, ID>{

    RS create(RQ request);
    RS read(ID id);
}

package com.pragma.loansanddeposits.mapper;

public interface GenericDataMapper <E, D>{

    E toEntity(D data);

    D toData(E entity);

}

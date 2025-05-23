package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.helper.mapper;

public interface GenericDataMapper <E, D>{

    E toEntity(D data);

    D toData(E entity);

}

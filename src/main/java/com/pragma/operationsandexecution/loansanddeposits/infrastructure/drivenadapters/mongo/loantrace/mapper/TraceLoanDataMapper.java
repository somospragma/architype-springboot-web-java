package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace.mapper;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.LoanTrace;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace.LoanTraceData;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.helper.mapper.GenericDataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceLoanDataMapper extends GenericDataMapper<LoanTrace, LoanTraceData> {
}


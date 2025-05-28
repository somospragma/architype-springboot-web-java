package com.pragma.loansanddeposits.mapper;

import com.pragma.loansanddeposits.loantrace.LoanTraceData;
import com.pragma.loansanddeposits.model.LoanTrace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceLoanDataMapper extends GenericDataMapper<LoanTrace, LoanTraceData> {
}


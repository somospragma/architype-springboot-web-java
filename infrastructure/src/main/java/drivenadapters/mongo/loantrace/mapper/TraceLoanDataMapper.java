package drivenadapters.mongo.loantrace.mapper;

import drivenadapters.mongo.loantrace.LoanTraceData;
import drivenadapters.helper.mapper.GenericDataMapper;
import com.pragma.loansanddeposits.model.LoanTrace;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TraceLoanDataMapper extends GenericDataMapper<LoanTrace, LoanTraceData> {
}


package entrypoint.rest.mappers;

import entrypoint.rest.dto.LoanDto;
import com.pragma.loansanddeposits.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoanDtoMapper {

    Loan loanDtoToLoan(LoanDto loanDTO);

}

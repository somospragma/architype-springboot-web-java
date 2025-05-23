package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.LocalDate;

import static com.pragma.operationsandexecution.loansanddeposits.application.constants.ApplicationContants.*;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_END_DATE_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_LOAN_DTO;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_LOAN_ID_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_LOAN_ID_EXAMPLE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_AMOUNT_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_AMOUNT_EXAMPLE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_INTEREST_RATE_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_INTEREST_RATE_EXAMPLE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_START_DATE_DESCRIPTION;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_START_DATE_EXAMPLE;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_SCHEMA_END_DATE_EXAMPLE;

/**
 * DTO para transferir datos de préstamos.
 */
@Builder
@Schema(description = SWAGGER_SCHEMA_LOAN_DTO)
public class LoanDto {

    @NotEmpty(message = VALIDATE_DTO_LOAN_ID_NOT_EMPTY)
    @Schema(description = SWAGGER_SCHEMA_LOAN_ID_DESCRIPTION, example = SWAGGER_SCHEMA_LOAN_ID_EXAMPLE)
    private String loanId;

    @NotNull(message = VALIDATE_DTO_AMOUNT_NOT_NULL)
    @Positive(message = VALIDATE_DTO_AMOUNT_POSITIVE)
    @Schema(description = SWAGGER_SCHEMA_AMOUNT_DESCRIPTION, example = SWAGGER_SCHEMA_AMOUNT_EXAMPLE)
    private Double amount;

    @NotNull(message = VALIDATE_DTO_INTEREST_RATE_NOT_NULL)
    @Positive(message = VALIDATE_DTO_INTEREST_RATE_POSITIVE)
    @Schema(description = SWAGGER_SCHEMA_INTEREST_RATE_DESCRIPTION, example = SWAGGER_SCHEMA_INTEREST_RATE_EXAMPLE)
    private Double interestRate;

    @NotNull(message = VALIDATE_DTO_START_DATE_NOT_NULL)
    @Schema(description = SWAGGER_SCHEMA_START_DATE_DESCRIPTION, example = SWAGGER_SCHEMA_START_DATE_EXAMPLE)
    private LocalDate startDate;

    @NotNull(message = VALIDATE_DTO_END_DATE_NOT_NULL)
    @Schema(description = SWAGGER_SCHEMA_END_DATE_DESCRIPTION, example = SWAGGER_SCHEMA_END_DATE_EXAMPLE)
    private LocalDate endDate;

    public @NotEmpty(message = VALIDATE_DTO_LOAN_ID_NOT_EMPTY) String getLoanId() {
        return loanId;
    }

    public void setLoanId(@NotEmpty(message = VALIDATE_DTO_LOAN_ID_NOT_EMPTY) String loanId) {
        this.loanId = loanId;
    }

    public @NotNull(message = VALIDATE_DTO_AMOUNT_NOT_NULL) @Positive(message = VALIDATE_DTO_AMOUNT_POSITIVE) Double getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = VALIDATE_DTO_AMOUNT_NOT_NULL) @Positive(message = VALIDATE_DTO_AMOUNT_POSITIVE) Double amount) {
        this.amount = amount;
    }

    public @NotNull(message = VALIDATE_DTO_INTEREST_RATE_NOT_NULL) @Positive(message = VALIDATE_DTO_INTEREST_RATE_POSITIVE) Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(@NotNull(message = VALIDATE_DTO_INTEREST_RATE_NOT_NULL) @Positive(message = VALIDATE_DTO_INTEREST_RATE_POSITIVE) Double interestRate) {
        this.interestRate = interestRate;
    }

    public @NotNull(message = VALIDATE_DTO_START_DATE_NOT_NULL) LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotNull(message = VALIDATE_DTO_START_DATE_NOT_NULL) LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotNull(message = VALIDATE_DTO_END_DATE_NOT_NULL) LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotNull(message = VALIDATE_DTO_END_DATE_NOT_NULL) LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

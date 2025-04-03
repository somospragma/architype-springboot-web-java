package com.pragma.operationsandexecution.loansanddeposits.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

import static com.pragma.operationsandexecution.crosscutting.constants.application.ApplicationContants.*;
import static com.pragma.operationsandexecution.crosscutting.constants.common.SwaggerConstants.*;

/**
 * DTO para transferir datos de préstamos.
 */
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

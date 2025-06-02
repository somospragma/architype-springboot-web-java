package com.pragma.loansanddeposits.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_AMOUNT_NOT_NULL;
import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_AMOUNT_POSITIVE;
import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_END_DATE_NOT_NULL;
import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_INTEREST_RATE_NOT_NULL;
import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_INTEREST_RATE_POSITIVE;
import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_LOAN_ID_NOT_EMPTY;
import static com.pragma.loansanddeposits.constant.ApplicationContants.VALIDATE_DTO_START_DATE_NOT_NULL;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_AMOUNT_DESCRIPTION;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_AMOUNT_EXAMPLE;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_END_DATE_DESCRIPTION;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_END_DATE_EXAMPLE;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_INTEREST_RATE_DESCRIPTION;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_INTEREST_RATE_EXAMPLE;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_LOAN_DTO;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_LOAN_ID_DESCRIPTION;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_LOAN_ID_EXAMPLE;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_START_DATE_DESCRIPTION;
import static com.pragma.loansanddeposits.constant.SwaggerConstants.SWAGGER_SCHEMA_START_DATE_EXAMPLE;

/**
 * DTO para transferir datos de préstamos.
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = SWAGGER_SCHEMA_LOAN_DTO)
public class LoanDto {

    @NotEmpty(message = VALIDATE_DTO_LOAN_ID_NOT_EMPTY)
    @Pattern(regexp = "\\d+", message = "El id del préstamo debe contener solo dígitos.")
    @Schema(description = SWAGGER_SCHEMA_LOAN_ID_DESCRIPTION, example = SWAGGER_SCHEMA_LOAN_ID_EXAMPLE)
    private String id;

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

    public @NotEmpty(message = VALIDATE_DTO_LOAN_ID_NOT_EMPTY) String getId() {
        return id;
    }

    public void setId(@NotEmpty(message = VALIDATE_DTO_LOAN_ID_NOT_EMPTY) String id) {
        this.id = id;
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
                "loanId='" + id + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

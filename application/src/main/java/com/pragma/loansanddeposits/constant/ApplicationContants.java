package com.pragma.loansanddeposits.constant;

public class ApplicationContants {

    /*  Constantes de errores */
    public static final String VALIDATE_DTO_LOAN_ID_NOT_EMPTY = "El ID del prestamo no puede ser vacio";
    public static final String VALIDATE_DTO_AMOUNT_NOT_NULL = "La cantidad del préstamo no puede ser nula.";
    public static final String VALIDATE_DTO_AMOUNT_POSITIVE = "La cantidad del préstamo debe ser positiva.";
    public static final String VALIDATE_DTO_INTEREST_RATE_NOT_NULL = "La tasa de interés no puede ser nula.";
    public static final String VALIDATE_DTO_INTEREST_RATE_POSITIVE = "La tasa de interés debe ser positiva.";
    public static final String VALIDATE_DTO_START_DATE_NOT_NULL = "La fecha de inicio no puede ser nula.";
    public static final String VALIDATE_DTO_END_DATE_NOT_NULL = "La fecha de finalización no puede ser nula.";

    private ApplicationContants() {
    }
}

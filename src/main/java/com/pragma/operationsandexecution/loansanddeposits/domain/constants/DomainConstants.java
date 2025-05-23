package com.pragma.operationsandexecution.loansanddeposits.domain.constants;

public class DomainConstants {

    /* Constantes de información de flujo */
    public static final String VALUE_MESSAGE_USER_TOKEN_GET_ERROR = "El usuario no tiene un token autorizado, debe de autorizar de nuevo";
    public static final String MESSAGE_LOG_USER_TOKEN_GET_ERROR = "Token no autorizado. Reautorizar";
    public static final String VALUE_MESSAGE_LOAN_EXIST_ERROR = "El prestamo no se puede crear, ya existe";
    public static final String MESSAGE_LOG_LOAN_EXIST_ERROR = "El loanId ya existe en la base de datos";
    public static final String VALUE_MESSAGE_LOAN_ID_FORMAT_INCORRECT = "El campo loanId debe contener únicamente dígitos";
    public static final String MESSAGE_LOG_FORMAT_INCORRECT = "El loanId contiene caracteres alfanuméricos";
    public static final String VALUE_MESSAGE_LOAN_ID_NOT_FOUND = "El préstamo con el ID especificado no fue encontrado";
    public static final String MESSAGE_LOG_LOAN_ID_NOT_FOUND = "No se encontró el préstamo en BD con loanId";

    /* Constantes Extras de utilidad */
    public static final String UTILITY_KEY_MESSAGE = "message";
    public static final String UTILITY_EXAMPLE_USER_NAME_VALUE = "miUserName";
    public static final String UTILITY_EXAMPLE_USER_NAME_2_VALUE = "miNombreDeUsuario";
    public static final String UTILITY_EXAMPLE_NAME_VALUE = "miNombre";
    public static final String UTILITY_EXAMPLE_LAST_NAME_VALUE = "miApellido";

    /* Constantes Extras de messageKey */
    public static final String MESSAGE_KEY_LOAN_NOT_FOUND_ERROR = "loan.not.found.error";
    public static final String MESSAGE_KEY_LOAN_FOUND_ERROR = "loan.found.error";
    public static final String MESSAGE_KEY_CREATION_ERROR = "loan.creation.error";
    public static final String MESSAGE_KEY_VALIDATE_TOKEN_USER_ERROR = "user.token.get.error";

}


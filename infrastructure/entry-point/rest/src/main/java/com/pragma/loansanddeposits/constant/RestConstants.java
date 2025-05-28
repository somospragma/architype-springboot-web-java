package com.pragma.loansanddeposits.constant;

public class RestConstants {

    private RestConstants() {}

    /* Constantes de información de flujo */
    public static final String REST_EXCHANGE_REQUEST = "Se envio una petición al servicio Rest";

    /* Constantes de layers */
    public static final String LAYER_CROSSCUTTING_GENERIC_REST_CLIENT_EXCHANGE_MESSAGE = "crosscutting.rest.GenericRestClient.exchange";

    public static final String PATH_LOAN_CONTROLLER = "/v1/loan";
    public static final String LAYER_INFRASTRUCTURE_CONTROLLER_CREATE_LOAN = "infrastructure.controller.LoanController.createLoan";
    public static final String MESSAGE_KEY_CREATION_SUCCESS = "loan.creation.success";
    public static final String CREATE_LOAN_PROCESS_STARTED = "Se inició la creación de un prestamo";
    public static final String GET_LOAN_BY_ID_PROCESS_STARTED = "Se inició la obtención de un prestamo";
    public static final String LAYER_INFRASTRUCTURE_CONTROLLER_GET_LOAN_BY_ID = "infrastructure.controller.LoanController.getLoanById";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_SAVE = "infrastructure.dataproviders.database.implementation.Sql.save";
    public static final String MESSAGE_KEY_GET_SUCCESS = "loan.get.success";

    public static final String CODE_STATUS_200 = "200";
    public static final String CODE_STATUS_204 = "204";
    public static final String CODE_STATUS_400 = "400";
    public static final String CODE_STATUS_401 = "401";
    public static final String CODE_STATUS_403 = "403";
    public static final String CODE_STATUS_404 = "404";
    public static final String CODE_STATUS_408 = "408";
    public static final String CODE_STATUS_409 = "409";
    public static final String CODE_STATUS_500 = "500";
    public static final String CODE_STATUS_501 = "501";
}

package com.pragma.loansanddeposits.constant;

public class SecretConstants {

    private SecretConstants() {}

    /* Constantes Extras de utilidad */
    public static final String UTILITY_SECRET_CLIENT_ID_YML = "${azure.keyvault.secret.client-id}";
    public static final String UTILITY_SECRET_CLIENT_SECRET_YML = "${azure.keyvault.secret.client-secret}";
    public static final String UTILITY_SECRET_TENANT_ID_YML = "${azure.keyvault.secret.tenant-id}";
    public static final String UTILITY_SECRET_URI = "${azure.keyvault.secret.uri}";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_GET_SECRET = "infrastructure.dataproviders.secrets.implementation.EntrustSecret.retrieveAndParseSecret";
    public static final String SECRET_PARSER_ERROR_CATCH = "Ocurrio un error parseando el JSON del secreto.";
    public static final String MESSAGE_KEY_SECRET_PARSER_ERROR = "exception.getSecret.error.parser";

}

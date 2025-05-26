package constants;

public class SecretConstants {

    private SecretConstants() {}

    /* Constantes Extras de utilidad */
    public static final String UTILITY_SECRET_CLIENT_ID_YML = "${azure.keyvault.secret.client-id}";
    public static final String UTILITY_SECRET_CLIENT_SECRET_YML = "${azure.keyvault.secret.client-secret}";
    public static final String UTILITY_SECRET_TENANT_ID_YML = "${azure.keyvault.secret.tenant-id}";
    public static final String UTILITY_SECRET_URI = "${azure.keyvault.secret.uri}";

}

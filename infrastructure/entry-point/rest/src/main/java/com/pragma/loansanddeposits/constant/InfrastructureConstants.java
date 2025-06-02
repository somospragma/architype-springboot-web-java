package com.pragma.loansanddeposits.constant;

public class InfrastructureConstants {

    private InfrastructureConstants() {}

    /*  Constantes de errores */
    public static final String MONGO_CREATE_ERROR = "Error al crear MongoClient";
    public static final String MONGO_CREATE_CLIENT = "Fallo al crear MongoClient";
    public static final String COSMOS_CREATE_ERROR = "Error al crear CosmosAsyncClient";
    public static final String COSMOS_CREATE_CLIENT = "Fallo al crear CosmosAsyncClient";
    public static final String STORAGE_UPLOAD_FILE_ERROR = "Error al subir el archivo: ";
    public static final String STORAGE_DOWNLOAD_FILE_ERROR = "Error al descargar el archivo: ";
    public static final String STORAGE_DELETE_FILE_ERROR = "Error al eliminar el archivo: ";
    public static final String STORAGE_VERIFY_FILE_ERROR = "Error al verificar la existencia del blob: ";
    public static final String STORAGE_NOT_FOUND = "El blob no existe: ";
    public static final String NO_SQL_SAVE_ERROR = "Error al guardar la entidad";
    public static final String SQL_SAVE_ERROR = "Error al guardar la entidad";
    public static final String SQL_ENTITY_DELETED_ERROR = "Error al eliminar entidad";
    public static final String SQL_FIND_BY_ID_ERROR = "Error al buscar la entidad por ID";
    public static final String SQL_FIND_ALL_ERROR = "Error al buscar todas la entidades";
    public static final String SQL_DELETE_BY_ID_ERROR = "Error al eliminar la entidad por ID";
    public static final String TYPE_NOT_SUPPORTED = "Tipo de base de datos no soportado: ";
    public static final String NO_SQL_ENTITY_DELETED_ERROR = "Error al eliminar entidad";
    public static final String NO_SQL_FIND_BY_ID_ERROR = "Error al buscar la entidad por ID";
    public static final String NO_SQL_FIND_ALL_ERROR = "Error al buscar todas la entidades";
    public static final String NO_SQL_DELETE_BY_ID_ERROR = "Error al eliminar la entidad por ID";
    public static final String UPDATE_FILE_ERROR = "Error al subir el archivo";
    public static final String DOWNLOAD_FILE_NOT_FOUND_ERROR = "El archivo no fue encontrado";
    public static final String DOWNLOAD_FILE_ERROR = "Error al descargar el archivo";
    public static final String DELETE_FILE_NOT_FOUND_ERROR = "El archivo no fue encontrado para eliminación";
    public static final String DELETE_FILE_ERROR = "Error al eliminar el archivo";
    public static final String FILE_EXISTS_ERROR = "Error al verificar la existencia del archivo";
    public static final String TRANSACTION_MANAGER_ERROR = "EntityManagerFactory devolvió un valor nulo. No se puede crear TransactionManager.";
    public static final String TRANSACTION_MANAGER_ERROR_CATCH = "EntityManagerFactory devuelve nulo.";

    /* Constantes de información de flujo */

    public static final String ENTITY_PERSISTED_SUCCESSFULLY = "La entidad persistió con éxito";
    public static final String ENTITY_MERGED_SUCCESSFULLY = "Entidad fusionada exitosamente";
    public static final String ENTITY_DELETED_SUCCESSFULLY_SQL = "Entidad eliminada exitosamente";
    public static final String ENTITY_DELETED_SUCCESSFULLY_NO_SQL = "Entidad eliminada exitosamente";
    public static final String ENTITY_FIND_BY_ID_SQL = "Entidad encontrada por ID";
    public static final String ENTITY_DELETE_BY_ID_SQL = "Entidad eliminada por el ID";
    public static final String ENTITY_SAVED_NO_SQL = "Entidad guardada";
    public static final String ALL_ENTITY_RETRIEVED = "Todas las entidades recuperadas";
    public static final String ENTITY_FIND_BY_ID_NO_SQL = "Entidad encontrada por el ID";
    public static final String ENTITY_FIND_ALL_NO_SQL = "Se encontraron todas las entitades";
    public static final String ENTITY_DELETE_BY_ID_NO_SQL = "Entidad eliminada por el ID";
    public static final String UPDATE_FILE_SUCCESSFULLY = "Archivo subido exitosamente";
    public static final String DOWNLOAD_FILE_SUCCESSFULLY = "Archivo descargado exitosamente";
    public static final String DELETE_FILE_SUCCESSFULLY = "Archivo eliminado exitosamente";
    public static final String FILE_EXISTS_TRUE = "Archivo {} existencia";
    public static final String REST_RESPONSE_SUCCESS = "El servicio de Rest respondio sastifactoriamente";

    /* Constantes de Beans y Configuraciones */
    public static final String BEAN_AZURE_SQL_DATA_SOURCE = "azureSqlDataSource";
    public static final String BEAN_SUPPRESS_WARNINGS = "unchecked";
    public static final String BEAN_ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    public static final String BEAN_TRANSACTION_MANAGER = "transactionManager";
    public static final String CONFIGURATION_PROPERTIES_SPRING_DATA_SOURCE = "spring.datasource";

    /* Constantes Extras de utilidad */
    public static final String UTILITY_AZURE_SQL = "azureSql";
    public static final String UTILITY_STRING_FORMAT = "%s: %s";
    public static final String UTILITY_CRITERIA_WHERE_MONGO = "_id";
    public static final String UTILITY_SQL_QUERY_FIND_ALL_SELECT = "SELECT e FROM ";
    public static final String UTILITY_SQL_QUERY_FIND_ALL_VALUE = " e";
    public static final String UTILITY_TYPE_REPOSITORY_SQL = "sql";
    public static final String UTILITY_TYPE_REPOSITORY_NO_SQL = "nosql";
    public static final String UTILITY_QUALIFIER_ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    public static final String UTILITY_QUALIFIER_AZURE_SQL_DATA_SOURCE = "azureSqlDataSource";
    public static final String UTILITY_REST_ENTRUST_URL_YML = "${rest.entrust.url}";
    public static final String UTILITY_REST_HEADER_ACCEPT = "Accept";
    public static final String UTILITY_REST_HEADER_CONTENT_TYPE = "Content-Type";
    public static final String UTILITY_REST_HEADER_APPLICATION_ID = "applicationId";
    public static final String UTILITY_SECRET_NAME_ENTRUST = "entrust-credentials";

    /* Constantes path y Utilidades*/
    public static final String PATH_LOAN_CONTROLLER_CREATE_LOAN = "/initiate";
    public static final String PATH_LOAN_CONTROLLER_GET_LOAN_BY_ID = "/{id}/get-loan-by-id/retrieve";
    public static final String PATH_VARIABLE_ID = "id";

    /* Constantes de layers */
     public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_DELETE = "infrastructure.dataproviders.database.implementation.Sql.delete";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_FIND_BY_ID = "infrastructure.dataproviders.database.implementation.Sql.findById";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_FIND_ALL = "infrastructure.dataproviders.database.implementation.Sql.findAll";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_SQL_DELETE_BY_ID = "infrastructure.dataproviders.database.implementation.Sql.deleteById";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_SAVE = "infrastructure.dataproviders.database.implementation.NoSql.save";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_DELETE = "infrastructure.dataproviders.database.implementation.NoSql.delete";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_FIND_BY_ID = "infrastructure.dataproviders.database.implementation.NoSql.findById";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_FIND_ALL = "infrastructure.dataproviders.database.implementation.NoSql.findAll";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_NO_SQL_DELETE_BY_ID = "infrastructure.dataproviders.database.implementation.NoSql.deleteById";
    public static final String LAYER_INFRASTRUCTURE_CONFIGURATION_MONGO_CLIENT = "infrastructure.configuratio.DataSourceConfiguration.mongoClient";
    public static final String LAYER_INFRASTRUCTURE_CONFIGURATION_AZURE_SQL_DATA_SOURCE = "infrastructure.configuratio.DataSourceConfiguration.azureSqlDataSource";
    public static final String LAYER_INFRASTRUCTURE_CONFIGURATION_ENTITY_MANAGER_FACTORY = "infrastructure.configuratio.DataSourceConfiguration.entityManagerFactory";
    public static final String LAYER_INFRASTRUCTURE_CONFIGURATION_TRANSACTION_MANAGER = "infrastructure.configuratio.DataSourceConfiguration.transactionManager";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_UPLOAD_FILE = "infrastructure.dataproviders.StorageDataProvider.uploadFile";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DOWNLOAD_FILE = "infrastructure.dataproviders.blob.StorageDataProvider.downloadFile";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DELETE_FILE = "infrastructure.dataproviders.blob.StorageDataProvider.deleteFile";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_EXISTS = "infrastructure.dataproviders.blob.StorageDataProvider.exists";
    public static final String LAYER_INFRASTRUCTURE_DATA_PROVIDER_REST_GET_TOKEN = "infrastructure.dataproviders.rest.implementation.EntrustRestClient.getTokenAuthenticationEntrust";

    /* Constantes Extras de messageKey */
    public static final String MESSAGE_KEY_DATABASE_SQL_SAVE_ERROR = "database.sql.save.error";
    public static final String MESSAGE_KEY_DATABASE_SQL_FIND_BY_ID_ERROR = "database.sql.findById.error";
    public static final String MESSAGE_KEY_DATABASE_SQL_DELETE_ERROR = "database.sql.delete.error";
    public static final String MESSAGE_KEY_DATABASE_SQL_DELETE_BY_ID_ERROR = "database.sql.deleteById.error";
    public static final String MESSAGE_KEY_DATABASE_SQL_FIND_ALL_ERROR = "database.sql.findAll.error";
    public static final String MESSAGE_KEY_DATABASE_NO_SQL_FIND_ALL_ERROR = "database.nosql.findAll.error";
    public static final String MESSAGE_KEY_DATABASE_NO_SQL_DELETE_BY_ID_ERROR = "database.nosql.deleteById.error";
    public static final String MESSAGE_KEY_DATABASE_NO_SQL_FIND_BY_ID_ERROR = "database.nosql.findById.error";
    public static final String MESSAGE_KEY_DATABASE_NO_SQL_DELETE_ERROR = "database.nosql.delete.error";
    public static final String MESSAGE_KEY_DATABASE_NO_SQL_SAVE_ERROR = "database.nosql.save.error";

    /* Constantes Extras de Paths */
    public static final String PATH_ENTRUST_CREATE = "createWithList";

}

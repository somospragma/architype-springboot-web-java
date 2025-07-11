package com.pragma.loansanddeposits;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.pragma.loansanddeposits.exceptions.StorageException;
import com.pragma.loansanddeposits.port.out.ILoggerPort;
import com.pragma.loansanddeposits.port.out.IStoragePort;
import com.pragma.loansanddeposits.util.RequestFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

import static com.pragma.loansanddeposits.constant.BlobConstants.DELETE_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.DELETE_FILE_NOT_FOUND_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.DELETE_FILE_SUCCESSFULLY;
import static com.pragma.loansanddeposits.constant.BlobConstants.DOWNLOAD_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.DOWNLOAD_FILE_NOT_FOUND_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.DOWNLOAD_FILE_SUCCESSFULLY;
import static com.pragma.loansanddeposits.constant.BlobConstants.FILE_EXISTS_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.FILE_EXISTS_TRUE;
import static com.pragma.loansanddeposits.constant.BlobConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DELETE_FILE;
import static com.pragma.loansanddeposits.constant.BlobConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DOWNLOAD_FILE;
import static com.pragma.loansanddeposits.constant.BlobConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_EXISTS;
import static com.pragma.loansanddeposits.constant.BlobConstants.LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_UPLOAD_FILE;
import static com.pragma.loansanddeposits.constant.BlobConstants.STORAGE_DELETE_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.STORAGE_DOWNLOAD_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.STORAGE_NOT_FOUND;
import static com.pragma.loansanddeposits.constant.BlobConstants.STORAGE_UPLOAD_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.STORAGE_VERIFY_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.UPDATE_FILE_ERROR;
import static com.pragma.loansanddeposits.constant.BlobConstants.UPDATE_FILE_SUCCESSFULLY;


/**
 * Implementación de {@link IStoragePort} utilizando Azure Blob Storage.
 * <p>
 * Este servicio proporciona métodos para interactuar con Azure Blob Storage, incluyendo
 * la subida, descarga, eliminación y verificación de blobs.
 * </p>
 */
@Service
public class BlobStorageDataProvider implements IStoragePort {

    private final BlobContainerClient blobContainerClient;
    private final ILoggerPort loggerService;

    /**
     * Constructor para inyectar la configuración de Azure Blob Storage.
     *
     * @param connectionString la cadena de conexión a Azure Blob Storage
     * @param containerName    el nombre del contenedor de blobs
     */
    public BlobStorageDataProvider(
            @Value("${azure.storage.blob.connection-string}") String connectionString,
            @Value("${azure.storage.blob.container-name}") String containerName,
            ILoggerPort iLoggerPort) {

        this.blobContainerClient = new BlobContainerClientBuilder()
                .connectionString(connectionString)
                .containerName(containerName)
                .buildClient();
        this.loggerService = iLoggerPort;

        createContainerIfNotExists();
    }

    /**
     * Crea el contenedor si no existe.
     */
    private void createContainerIfNotExists() {
        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uploadFile(String blobName, RequestFile file) throws StorageException {
        try (InputStream inputStream = file.getInputStream()) {
            BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
            blobClient.upload(inputStream, file.getSize(), true);
            loggerService.logInfo(blobName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_UPLOAD_FILE,
                    UPDATE_FILE_SUCCESSFULLY, blobClient);
        } catch (Exception exception) {
            loggerService.logInfo(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_UPLOAD_FILE,
                    UPDATE_FILE_ERROR, exception.getMessage());
            throw new StorageException(STORAGE_UPLOAD_FILE_ERROR + blobName, exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream downloadFile(String blobName) throws StorageException {
        try {
            BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
            if (!blobClient.exists()) {
                loggerService.logInfo(blobName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DOWNLOAD_FILE,
                        DOWNLOAD_FILE_NOT_FOUND_ERROR, "");
                throw new StorageException(STORAGE_NOT_FOUND + blobName);
            }
            loggerService.logInfo(blobName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DOWNLOAD_FILE,
                    DOWNLOAD_FILE_SUCCESSFULLY, blobClient);
            return blobClient.openInputStream();
        } catch (Exception exception) {
            loggerService.logInfo(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DOWNLOAD_FILE,
                    DOWNLOAD_FILE_ERROR, exception.getMessage());
            throw new StorageException(STORAGE_DOWNLOAD_FILE_ERROR + blobName, exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteFile(String blobName) throws StorageException {
        try {
            BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
            if (blobClient.exists()) {
                blobClient.delete();
                loggerService.logInfo(blobName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DELETE_FILE,
                        DELETE_FILE_SUCCESSFULLY, blobClient);
            } else {
                loggerService.logInfo(blobName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DELETE_FILE,

                        DELETE_FILE_NOT_FOUND_ERROR, "");
                throw new StorageException(STORAGE_NOT_FOUND + blobName);
            }
        } catch (Exception exception) {
            loggerService.logInfo(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_DELETE_FILE,
                    DELETE_FILE_ERROR, exception.getMessage());
            throw new StorageException(STORAGE_DELETE_FILE_ERROR + blobName, exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(String blobName) throws StorageException {
        try {
            BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
            boolean exists = blobClient.exists();
            loggerService.logInfo(blobName, LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_EXISTS,
                    FILE_EXISTS_TRUE, blobClient);
            return exists;
        } catch (Exception exception) {
            loggerService.logInfo(exception.getLocalizedMessage(), LAYER_INFRASTRUCTURE_DATA_PROVIDER_BLOB_EXISTS,
                    FILE_EXISTS_ERROR, exception.getMessage());
            throw new StorageException(STORAGE_VERIFY_FILE_ERROR + blobName, exception);
        }
    }
}

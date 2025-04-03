package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import org.springframework.web.multipart.MultipartFile;

import com.pragma.operationsandexecution.crosscutting.exceptions.StorageException;

import java.io.InputStream;

/**
 * Interfaz para el servicio de almacenamiento.
 * <p>
 * Define métodos para interactuar con el almacenamiento de blobs.
 * </p>
 */
public interface IStoragePort {

    /**
     * Sube un archivo al almacenamiento de blobs.
     *
     * @param blobName el nombre del blob en el contenedor
     * @param file     el archivo a subir
     * @throws StorageException si ocurre un error durante la subida
     */
    void uploadFile(String blobName, MultipartFile file) throws StorageException;

    /**
     * Descarga un archivo desde el almacenamiento de blobs.
     *
     * @param blobName el nombre del blob en el contenedor
     * @return un {@link InputStream} con el contenido del blob
     * @throws StorageException si ocurre un error durante la descarga
     */
    InputStream downloadFile(String blobName) throws StorageException;

    /**
     * Elimina un archivo del almacenamiento de blobs.
     *
     * @param blobName el nombre del blob en el contenedor
     * @throws StorageException si ocurre un error durante la eliminación
     */
    void deleteFile(String blobName) throws StorageException;

    /**
     * Verifica si un blob existe en el contenedor.
     *
     * @param blobName el nombre del blob en el contenedor
     * @return {@code true} si el blob existe, {@code false} en caso contrario
     * @throws StorageException si ocurre un error durante la verificación
     */
    boolean exists(String blobName) throws StorageException;
}
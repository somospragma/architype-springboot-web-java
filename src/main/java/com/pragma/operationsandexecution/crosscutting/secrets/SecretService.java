package com.pragma.operationsandexecution.crosscutting.secrets;

import com.pragma.operationsandexecution.crosscutting.configuration.SecretConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementación de {@link ISecretService} utilizando Azure Key Vault.
 * <p>
 * Este servicio accede a los secretos almacenados en Azure Key Vault.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class SecretService implements ISecretService {

    private final SecretConfiguration secretConfiguration;

    /**
     * Obtiene un secreto por su nombre desde Azure Key Vault.
     *
     * @param secretName el nombre del secreto a obtener
     * @return un {@link Optional} que contiene el valor del secreto si existe
     */
    @Override
    public String getSecret(String secretName) {
        return secretConfiguration.getSecretClient(secretName).getValue();
    }
}

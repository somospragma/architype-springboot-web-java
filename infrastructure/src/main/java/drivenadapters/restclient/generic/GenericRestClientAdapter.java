package drivenadapters.restclient.generic;

import drivenadapters.restclient.model.HttpRequestConfig;
import com.pragma.loansanddeposits.exceptions.StandardRestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Cliente REST genérico utilizando RestTemplate para realizar llamadas HTTP.
 *
 */
@Component
@RequiredArgsConstructor
public class GenericRestClientAdapter implements IGenericRestClientPort {

    private final RestTemplate restTemplate;

    /**
     * Realiza una solicitud HTTP genérica.
     *
     * @param requestConfig     Clase que contiene la configuración de la solicitud.
     * @param responseType      Estructura de respuesta exitosa de servicio externo.
     * @param errorResponseType Estructura de respuestas de error de servicios externos.
     * @param transactionId    Identificador de la transacción.
     * @return Respuesta HTTP completa con el cuerpo de la respuesta y los encabezados.
     *
     * @throws StandardRestException Excepción que encapsula errores de servicios externos, esta excepción
     * debe ser gestionada en los adaptadores que utilicen el método, no se debe propagar a capas superiores.
     */
    public <T, R, E> ResponseEntity<T> sendRequestAndReceiveResponse(HttpRequestConfig<E> requestConfig,
                                                                     Class<T> responseType,
                                                                     Class<R> errorResponseType,
                                                                     String transactionId) throws StandardRestException {
        HttpHeaders httpHeaders = new HttpHeaders();
        String url = requestConfig.getUrl();
        HttpMethod method = requestConfig.getMethod();
        Map<String, String> headers = requestConfig.getHeaders();
        E requestBody = requestConfig.getRequestBody();
        if (headers != null) {
            headers.forEach(httpHeaders::set);
        }
        try {
            HttpEntity<E> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
            return restTemplate.exchange(url, method, requestEntity, responseType);
        } catch (HttpClientErrorException exception) {
            R errorResponse = exception.getResponseBodyAs(errorResponseType);
            throw new StandardRestException(exception.getStatusCode(), errorResponse);

        }
    }

}

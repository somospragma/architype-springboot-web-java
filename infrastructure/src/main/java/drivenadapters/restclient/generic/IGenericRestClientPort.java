package drivenadapters.restclient.generic;

import drivenadapters.restclient.model.HttpRequestConfig;
import com.pragma.loansanddeposits.exceptions.StandardRestException;
import org.springframework.http.ResponseEntity;


public interface IGenericRestClientPort {

    <T, R, E> ResponseEntity<T> sendRequestAndReceiveResponse(HttpRequestConfig<E> requestConfig, Class<T> responseType,
                                                              Class<R> errorResponseType, String transactionId)
            throws StandardRestException;
}

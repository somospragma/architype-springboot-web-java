package com.pragma.operationsandexecution.crosscutting.documentation;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiErrorResponse;
import com.pragma.operationsandexecution.crosscutting.exceptions.ApiResponseDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.*;

import static com.pragma.operationsandexecution.crosscutting.constants.common.CommonConstants.*;
import static com.pragma.operationsandexecution.crosscutting.constants.common.SwaggerConstants.*;

/**
 * Anotación personalizada que agrupa las respuestas de API comunes,
 * evitando repetirlas en cada método del controlador.
 * <p>
 * Incluye:
 * - 200 OK
 * - 204 No Content
 * - 400 Bad Request
 * - 401 Unauthorized
 * - 403 Forbidden
 * - 404 Not Found
 * - 408 Request Timeout
 * - 409 Conflict
 * - 500 Internal Server Error
 * - 501 Not Implemented
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ApiResponses(value = {
        @ApiResponse(
                responseCode = CODE_STATUS_200,
                description = SWAGGER_API_RESPONSE_COMMON_API_200,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiResponseDto.class),
                        examples = @ExampleObject(ApiResponsesExamples.SUCCESS_200_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_204,
                description = SWAGGER_API_RESPONSE_COMMON_API_204,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiResponseDto.class),
                        examples = @ExampleObject(ApiResponsesExamples.SUCCESS_204_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_400,
                description = SWAGGER_API_RESPONSE_COMMON_API_400,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_400_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_401,
                description = SWAGGER_API_RESPONSE_COMMON_API_401,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_401_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_403,
                description = SWAGGER_API_RESPONSE_COMMON_API_403,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_403_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_404,
                description = SWAGGER_API_RESPONSE_COMMON_API_404,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_404_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_408,
                description = SWAGGER_API_RESPONSE_COMMON_API_408,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_408_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_409,
                description = SWAGGER_API_RESPONSE_COMMON_API_409,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_409_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_500,
                description = SWAGGER_API_RESPONSE_COMMON_API_500,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_500_EXAMPLE)
                )
        ),
        @ApiResponse(
                responseCode = CODE_STATUS_501,
                description = SWAGGER_API_RESPONSE_COMMON_API_501,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorResponse.class),
                        examples = @ExampleObject(ApiResponsesExamples.ERROR_501_EXAMPLE)
                )
        )
})
public @interface CommonApiResponses {
}
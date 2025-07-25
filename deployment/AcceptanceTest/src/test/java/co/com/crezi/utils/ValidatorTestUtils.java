package co.com.crezi.utils;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.model.Request;
import com.atlassian.oai.validator.model.Response;
import com.atlassian.oai.validator.model.SimpleResponse;
import com.atlassian.oai.validator.report.ValidationReport;

public class ValidatorTestUtils {
  public static String contentType = "application/json";
  public static final String POST = "POST";
  public static final String GET = "GET";
  public static final String PUT = "PUT";
  public static final String DELETE = "DELETE";
  public static final String OPTIONS = "OPTIONS";
  public static final String HEAD = "HEAD";

  public static void setContentType(String _contentType) {
    contentType = _contentType;
  }

  public static ValidationReport validateResponseSchema(String oasUrl, String responseJson,
      String path, String method, int status) {

    OpenApiInteractionValidator validator = OpenApiInteractionValidator
        .createForSpecificationUrl(oasUrl).build();

    Response response = SimpleResponse.Builder
        .status(status)
        .withContentType(contentType)
        .withBody(responseJson)
        .build();

    return validator.validateResponse(path, Request.Method.valueOf(method), response);
  }

}

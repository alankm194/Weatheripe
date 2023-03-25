package com.techreturners.weatheripe.configuration;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class OperationResponseCustomizer implements OperationCustomizer {
    public static final ApiResponse NOT_FOUND_API_RESPONSE;


    static {
        MediaType mediaType = new MediaType();
        mediaType.setSchema(new StringSchema());

        Content content = new Content();
        content.addMediaType("*/*", mediaType);

        NOT_FOUND_API_RESPONSE = new ApiResponse()
                .description("Not Found")
                .content(content);
    }

    /**
     * Customize operation.
     *
     * @param operation     input operation
     * @param handlerMethod original handler method
     * @return customized operation
     */
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        List<Class<?>> exceptions = Arrays.asList(method.getExceptionTypes());

        if(exceptions.contains(RuntimeException.class)){
            ApiResponses apiResponses = operation.getResponses();
            apiResponses.addApiResponse("404", NOT_FOUND_API_RESPONSE);
        }

        return operation;
    }
}
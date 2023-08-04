package com.example.demo.exception;

import com.example.demo.exception.model.ApiError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Test
    void should_returnApiError_when_exceptionThrown() {
        //given
        String expectedMessage = "test";
        RuntimeException runtimeException = new RuntimeException(expectedMessage);

        //when
        ApiError apiError = restExceptionHandler.defaultHandler(runtimeException);

        //then
        assertThat(apiError.getMessage()).isEqualTo(expectedMessage);

    }
}
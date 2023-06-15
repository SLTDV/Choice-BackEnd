package com.select.choice.infrastructure.feign.error;

import com.select.choice.global.error.type.ErrorCode;
import com.select.choice.infrastructure.feign.error.exception.FeignBadRequestException;
import com.select.choice.infrastructure.feign.error.exception.FeignForbiddenException;
import com.select.choice.infrastructure.feign.error.exception.FeignUnAuthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400) {
            switch (response.status()) {
                case 400:
                    throw new FeignBadRequestException(ErrorCode.FEIGN_BAD_REQUEST);
                case 401:
                    throw new FeignUnAuthorizedException(ErrorCode.FEIGN_UNAUTHRIZED);
                case 403:
                    throw new FeignForbiddenException(ErrorCode.FEIGN_FORBIDDEN);
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
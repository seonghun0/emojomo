package uyongseong.emojomo.domain;

import lombok.Getter;

@Getter
public class ResponseDto {

    private Boolean success;

    private int code;

    private String message;

    private Object data;

    public ResponseDto createRespData(Boolean success, int code, String message, Object data)
    {
        ResponseDto responseDto = new ResponseDto();

        responseDto.code = code;
        responseDto.success = success;
        responseDto.message = message;
        responseDto.data = data;

        return  responseDto;
    }
}

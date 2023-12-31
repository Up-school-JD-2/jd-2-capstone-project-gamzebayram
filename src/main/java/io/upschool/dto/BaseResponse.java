package io.upschool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private int status;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    @Builder.Default
    private String error = "no message available.";

    private T data;


}




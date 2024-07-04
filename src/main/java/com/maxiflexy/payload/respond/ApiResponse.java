package com.maxiflexy.payload.respond;

import com.maxiflexy.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String message;

    private T data;

    private String responseTime;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
        this.responseTime = DateUtils.dateToString(LocalDateTime.now());
    }

    public ApiResponse(String message){
        this.message = message;
        this.responseTime = DateUtils.dateToString(LocalDateTime.now());
    }
}

package org.example.eticaretapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    Boolean success;
    String message;
    Integer code;
    T data;
    
    public static<T> BaseResponse<T> getSuccess(T t){
        return getSuccess(t, "basarili islem");
    }
    
    public static<T> BaseResponse<T> getSuccess(T t, String message){
        return BaseResponse.<T>builder().success(true).code(200).data(t).message(message).build();
    }
}
package com.qp.grocery.utils;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData<T> {
    T entity;
    boolean success;
    String successMsg;
    String failedMsg;
}

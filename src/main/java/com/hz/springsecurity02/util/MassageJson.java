package com.hz.springsecurity02.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MassageJson<T> {
    private Integer code;
    private String msg;
    private T data;
}

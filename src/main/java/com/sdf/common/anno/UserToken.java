package com.sdf.common.anno;

import com.sdf.common.enu.TokenType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface UserToken {
    TokenType value() default TokenType.Validate;

}

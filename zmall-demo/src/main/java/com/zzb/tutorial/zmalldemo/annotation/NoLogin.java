package com.zzb.tutorial.zmalldemo.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NoLogin {
}

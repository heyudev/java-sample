package com.heyudev.spi.annotation;

import java.lang.annotation.*;

/**
 * @author heyudev
 * @date 2021/06/04
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface SPI {
}

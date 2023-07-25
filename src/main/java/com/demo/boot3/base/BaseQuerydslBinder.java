package com.demo.boot3.base;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.util.CastUtils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BaseQuerydslBinder {
    public BaseQuerydslBinder() {
    }

//    public static void customize(QuerydslBindings bindings, Path<?> root) {
//        MultiValueBinding<Path<? extends Date>, Date> dateMultiValueBinding = DateMultiValueBinding.get();
//        bindings.bind(Date.class).all((MultiValueBinding) CastUtils.cast(dateMultiValueBinding));
//        bindings.bind(Timestamp.class).all((MultiValueBinding)CastUtils.cast(dateMultiValueBinding));
//        MultiValueBinding<Path<? extends LocalDate>, LocalDate> localDateMultiValueBinding = LocalDateMultiValueBinding.get();
//        bindings.bind(LocalDate.class).all((MultiValueBinding)CastUtils.cast(localDateMultiValueBinding));
//        MultiValueBinding<Path<? extends LocalDateTime>, LocalDateTime> localDateTimeMultiValueBinding = LocalDateTimeMultiValueBinding.get();
//        bindings.bind(LocalDateTime.class).all((MultiValueBinding)CastUtils.cast(localDateTimeMultiValueBinding));
//    }
}

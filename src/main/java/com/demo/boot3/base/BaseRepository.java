package com.demo.boot3.base;

import com.demo.boot3.base.BaseQuerydslBinder;
import com.querydsl.core.types.EntityPath;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository <T,S extends EntityPath<?>> extends JpaRepository<T,String>, QuerydslBinderCustomizer<S> {
//    default void customize(@Nonnull QuerydslBindings bindings, @Nonnull S entityPath) {
//        BaseQuerydslBinder.customize(bindings, entityPath);
//    }
}

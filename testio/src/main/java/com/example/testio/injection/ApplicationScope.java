package com.example.testio.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by mantas on 5/1/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}

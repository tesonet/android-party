package com.tesonet.testio.extension

import java.lang.reflect.ParameterizedType

fun <T> Any.genericTypeClass(): Class<T> {
    val className = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].typeName
    val clazz = Class.forName(className)
    return clazz as Class<T>
}
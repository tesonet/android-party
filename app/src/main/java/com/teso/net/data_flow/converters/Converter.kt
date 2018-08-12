package com.teso.net.data_flow.converters

/**
 * Created by a.belichenko@gmail.com on 2/19/18.
 */
interface Converter<T, R> {
    fun convert(t: T): R
}
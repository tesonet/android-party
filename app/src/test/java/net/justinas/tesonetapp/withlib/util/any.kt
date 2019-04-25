@file:Suppress("UNCHECKED_CAST")

package net.justinas.tesonetapp.withlib.util

import org.mockito.Mockito


fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}
private fun <T> uninitialized(): T = null as T
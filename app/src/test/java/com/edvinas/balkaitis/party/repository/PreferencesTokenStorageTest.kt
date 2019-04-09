package com.edvinas.balkaitis.party.repository

import android.content.SharedPreferences
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PreferencesTokenStorageTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var tokenStorage: PreferencesTokenStorage
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var tokenEditor: SharedPreferences.Editor

    @Before
    fun setUp() {
        tokenStorage = PreferencesTokenStorage(sharedPreferences)

        editor = mock(SharedPreferences.Editor::class.java)
        tokenEditor = mock(SharedPreferences.Editor::class.java)
        given(editor.putString(KEY_TOKEN, TOKEN)).willReturn(tokenEditor)
        given(editor.remove(KEY_TOKEN)).willReturn(tokenEditor)
        given(sharedPreferences.edit()).willReturn(editor)
    }

    @Test
    fun removeToken_removesToken() {
        tokenStorage.removeToken()

        verify(editor).remove(KEY_TOKEN)
        verify(tokenEditor).apply()
    }

    @Test
    fun saveToken_savesToken() {
        tokenStorage.saveToken(TOKEN)

        verify(editor).putString(KEY_TOKEN, TOKEN)
        verify(tokenEditor).apply()
    }

    @Test
    fun getToken_returnsToken() {
        given(sharedPreferences.getString(KEY_TOKEN, EMPTY_TOKEN)).willReturn(TOKEN)

        val actualToken = tokenStorage.getToken()

        assertEquals(TOKEN, actualToken)
    }

    companion object {
        private const val TOKEN = "token"
        private const val EMPTY_TOKEN = ""
        private const val KEY_TOKEN = "key.token"
    }
}

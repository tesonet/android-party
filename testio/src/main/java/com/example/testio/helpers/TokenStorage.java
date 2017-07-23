package com.example.testio.helpers;

import android.content.SharedPreferences;
import org.jetbrains.annotations.Nullable;

/**
 * Created by mantas on 7/23/17.
 * In real app Account manager should be used
 */

public class TokenStorage {

  private final String TOKEN = "token";
  private SharedPreferences sharedPreferences;

  public TokenStorage(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
  }

  @Nullable
  public String getToken() {
    return sharedPreferences.getString(TOKEN, null);
  }

  public void storeToken(String token) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(TOKEN, token);
    editor.commit();
  }
}

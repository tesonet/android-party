package com.example.testio.helpers;

import android.util.Base64;

/**
 * Created by mantas on 7/22/17.
 */

public class E64 {

  public static String encrypt(String input) {
    return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
  }

  public static String decrypt(String input) {
    return new String(Base64.decode(input, Base64.DEFAULT));
  }

}

package lajevski.radoslav.tesonetparty.utils;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

/**
 * Created by Radoslav on 1/16/2018.
 */

public class AppString {

    /**
     * Check if a given String is actually empty, uninitialized or unusable.
     * The method performs conditional checks for NPE, empty initialized String and String containing the value "null".
     *
     * @param text - the String to check
     * @return true, if the provided String can be considered empty. False, if not
     */
    public static boolean isEmpty(String text) {
        // Three conditions: NPE, empty initialized String and String containing the value "null"
        return ((text == null) || (text != null && text.trim().isEmpty()) || (text != null && text.equals("null")));
    }

    public static SpannableString makeItalic(String text, int start, int end){

        SpannableString spannableString = new SpannableString(text);

        spannableString.setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

}

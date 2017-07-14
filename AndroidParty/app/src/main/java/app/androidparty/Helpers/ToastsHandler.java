package app.androidparty.Helpers;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Thoughtful on 2017-03-26.
 */

public final class ToastsHandler {

    public static void showMessage(Context context, CharSequence msg){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

}

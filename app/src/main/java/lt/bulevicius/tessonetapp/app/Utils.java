package lt.bulevicius.tessonetapp.app;

import android.annotation.SuppressLint;

import static lt.bulevicius.tessonetapp.app.Constants.BEARER;
import static lt.bulevicius.tessonetapp.app.Constants.KM_FORMAT;

public class Utils {


    public static String addBearer(String token) {
        return String.format(BEARER, token);
    }

    @SuppressLint("DefaultLocale")
    public static String addKilometers(long distance) {
        return String.format(KM_FORMAT, distance);
    }
}

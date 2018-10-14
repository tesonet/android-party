package lt.bulevicius.tessonetapp.app;

import static lt.bulevicius.tessonetapp.app.Constants.BEARER;

public class Utils {

    public static String addBearrer(String token) {
        return String.format(BEARER, token);
    }
}

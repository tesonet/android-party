package lt.liutkevicius.tesonetandroidparty.network.error;

import android.content.Context;
import lt.liutkevicius.tesonetandroidparty.R;
import retrofit2.HttpException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;
import java.net.UnknownHostException;

public class ErrorHandler {

    private final Context context;

    @Inject
    public ErrorHandler(Context context) {
        this.context = context;
    }

    public String getErrorMessage(Throwable throwable) {
        String message = context.getString(R.string.unknown_error);
        if (throwable instanceof HttpException) {
            switch (((HttpException) throwable).code()) {
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    return context.getString(R.string.unauthorized);
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    return context.getString(R.string.forbidden);
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    return context.getString(R.string.server_error);
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    return context.getString(R.string.bad_request);
                default:
                    return throwable.getLocalizedMessage();

            }
        } else if (throwable instanceof UnknownHostException) {
            message = context.getString(R.string.no_internet);
        }
        return message;
    }
}

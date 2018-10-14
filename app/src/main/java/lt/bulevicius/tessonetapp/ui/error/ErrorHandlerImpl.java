package lt.bulevicius.tessonetapp.ui.error;

import android.content.Context;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.net.ssl.SSLHandshakeException;

import lt.bulevicius.tessonetapp.R;
import retrofit2.HttpException;

/**
 * The type Error handler.
 */
public final class ErrorHandlerImpl {

    private Context context;

    /**
     * Instantiates a new Error handler.
     *
     * @param context the context
     */
    @Inject
    public ErrorHandlerImpl(Context context) {
        this.context = context;
    }

    /**
     * Handles error, returns one of the custom errors.
     *
     * @param error some error.
     * @return custom error.
     */
    public Exception handleError(Throwable error) {
        if (error instanceof HttpException) {
            if (((HttpException) error).code() >= 400 && ((HttpException) error).code() < 500) {
                return getAuthException();
            } else if (((HttpException) error).code() >= 500) {
                return getServerError();
            } else
                return getGenericException();
        }

        if (error instanceof UnknownHostException) {
            return getNetworkError();
        }

        if (error instanceof SSLHandshakeException) {
            return getNetworkError();
        }

        if (error instanceof ConnectException) {
            return getNetworkError();
        }

        if (error instanceof SocketTimeoutException) {
            return getNetworkError();
        }

        return getGenericException();

    }

    private Exception getNetworkError() {
        return new NetworkError(context.getString(R.string.network_error_generic));
    }

    private Exception getServerError() {
        return new ServerError(context.getString(R.string.server_error));
    }

    private Exception getGenericException() {
        return new GenericException(context.getString(R.string.generic_error));
    }

    private Exception getAuthException() {
        return new AuthException(context.getString(R.string.unauthorized_error));
    }
}

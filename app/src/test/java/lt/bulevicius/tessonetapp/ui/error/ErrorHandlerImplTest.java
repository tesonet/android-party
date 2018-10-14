package lt.bulevicius.tessonetapp.ui.error;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

public class ErrorHandlerImplTest {

    private ErrorHandlerImpl errorHandler;
    @Mock
    Context context;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        errorHandler = new ErrorHandlerImpl(context);
    }

    @Test
    public void testServerError() {
        HttpException exception = Mockito.mock(HttpException.class);
        doReturn(501).when(exception).code();
        String expectedValue = "ups";
        doReturn(expectedValue).when(context).getString(anyInt());
        Exception actual = errorHandler.handleError(exception);
        assertTrue(actual instanceof ServerError);
        assertEquals(expectedValue, actual.getMessage());
    }

    @Test
    public void testAuthError() {
        HttpException exception = Mockito.mock(HttpException.class);
        doReturn(403).when(exception).code();
        String expectedValue = "auth";
        doReturn(expectedValue).when(context).getString(anyInt());
        Exception actual = errorHandler.handleError(exception);
        assertTrue(actual instanceof AuthException);
        assertEquals(expectedValue, actual.getMessage());
    }

    @Test
    public void testNetworkError() {
        SSLHandshakeException exception = Mockito.mock(SSLHandshakeException.class);
        String expectedValue = "no internet";
        doReturn(expectedValue).when(context).getString(anyInt());
        Exception actual = errorHandler.handleError(exception);
        assertTrue(actual instanceof NetworkError);
        assertEquals(expectedValue, actual.getMessage());
    }

    @Test
    public void testGenericException() {
        Exception exception = new Exception("any");
        String expectedValue = "hmmm";
        doReturn(expectedValue).when(context).getString(anyInt());
        Exception actual = errorHandler.handleError(exception);
        assertTrue(actual instanceof GenericException);
        assertEquals(expectedValue, actual.getMessage());
    }
}
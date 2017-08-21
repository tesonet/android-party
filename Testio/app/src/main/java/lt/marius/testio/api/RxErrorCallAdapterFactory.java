package lt.marius.testio.api;


import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class RxErrorCallAdapterFactory extends CallAdapter.Factory {
	private CallAdapter.Factory mDelegate;

	public RxErrorCallAdapterFactory(final CallAdapter.Factory delegate) {
		mDelegate = delegate;
	}


	@Override
	public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

		Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
		Class<?> rawObservableType = getRawType(observableType);
		boolean isResponse = false;
		if (rawObservableType == Response.class) {
			isResponse = true;
		}


		return new RxErrorCallAdapter(mDelegate.get(returnType, annotations, retrofit),
				isResponse);
	}
}

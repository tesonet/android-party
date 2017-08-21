package lt.marius.testio.api

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import lt.marius.testio.GlobalErrorEvent
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Response
import java.lang.reflect.Type

class RxErrorCallAdapter<R>(private val mDelegate: CallAdapter<R, Any>,
                            private val isResponse: Boolean) : CallAdapter<R, Any> {

	override fun responseType(): Type = mDelegate.responseType()

	override fun adapt(call: Call<R>): Any {

		if (isResponse) {
			val observable = mDelegate.adapt(call) as Observable<Response<Any>>
			return observable
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.doOnNext {
						if (!it.isSuccessful) {
							sendErrorEvent(it.errorBody().string())
						}
					}
					.doOnError {
					}
					.doOnComplete {
					}

		} else {
			val observable = mDelegate.adapt(call) as Observable<Any>
			return observable
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.doOnNext {
					}
					.doOnError { e ->
						if (e is HttpException) {
							sendErrorEvent(e.response().errorBody().string())
						}
					}
					.doOnComplete {
					}
		}
	}


	private fun sendErrorEvent(body: String) {
		val errorResponse = Gson().fromJson(body, ErrorResponse::class.java)
		EventBus.getDefault().post(GlobalErrorEvent(errorResponse.message))
	}
}


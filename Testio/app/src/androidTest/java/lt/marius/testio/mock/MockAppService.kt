package lt.marius.testio.mock

import io.reactivex.Observable
import lt.marius.testio.GlobalErrorEvent
import lt.marius.testio.api.AppService
import lt.marius.testio.model.LoginRequestBody
import lt.marius.testio.model.LoginResponse
import lt.marius.testio.model.Server
import org.greenrobot.eventbus.EventBus

/**
 * Created by marius on 17.8.21.
 */
class MockAppService : AppService, BaseIdlingResource() {
	companion object {
		val CORRECT_USERNAME = "tesonet"
		val CORRECT_PASSWORD = "partyanimal"
		val CORRECT_TOKEN = "mockToken"
	}

	override fun getName() = "MockAppService"

	override fun login(body: LoginRequestBody): Observable<LoginResponse> {
		val loginObservable = Observable.create<LoginResponse> { emitter ->
			if (body.username == CORRECT_USERNAME && body.password == CORRECT_PASSWORD) {
				emitter.onNext(LoginResponse(token = CORRECT_TOKEN))
				emitter.onComplete()
			} else {
				EventBus.getDefault().post(GlobalErrorEvent("Unauthorized"))
				emitter.onError(Throwable("Unauthorized"))
			}
		}
		return wrapObservable(loginObservable)
	}

	override fun getServers(): Observable<List<Server>> {
		val serversObservable = Observable.create<List<Server>> { emitter ->
			//hmm.. no idea how to test if token passed here is correct...
			emitter.onNext(listOf(Server("Lithuania #2", 900)))
			emitter.onComplete()
		}
		return wrapObservable(serversObservable)
	}


}
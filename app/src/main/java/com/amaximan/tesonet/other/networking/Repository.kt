package com.amaximan.tesonet.other.networking

import com.amaximan.tesonet.App
import com.amaximan.tesonet.model.database.TesonetDB
import com.amaximan.tesonet.model.event.ServersLoadedEvent
import com.amaximan.tesonet.model.networking.TokenRequest
import com.amaximan.tesonet.model.sp.SPManager
import com.amaximan.tesonet.other.StateManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    val database = TesonetDB.instance

    private object Holder {
        val INSTANCE = Repository()
    }

    companion object {
        val instance: Repository by lazy { Holder.INSTANCE }
    }

    private val api: API
    private val compositeDisposable = CompositeDisposable()

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        api = Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build()
                .create(API::class.java)
    }

    fun getToken(tokenRequest: TokenRequest) {
        compositeDisposable.add(api.getToken(tokenRequest)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { StateManager.instance.getTokenIsInProgress.set(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->
                            val token = response.body()?.token

                            if (response.isSuccessful && token != null) {
                                SPManager.instance.setToken(token)
                                getServers(token)
                            } else {
                                run {
                                    App.instance.showToast(response.message() ?: "Unexpected error")
                                }
                            }

                            run { StateManager.instance.getTokenIsInProgress.set(false) }
                        },
                        { error ->
                            run { StateManager.instance.getTokenIsInProgress.set(false) }
                            run { App.instance.showToast(error.message ?: "Unexpected error") }
                        }))
    }

    fun getServers(token: String) {
        compositeDisposable.add(api.getServers("Bearer $token")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { StateManager.instance.getServersIsInProgress.set(true) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response ->

                            if (response.isSuccessful) {
                                EventBus.getDefault().postSticky(ServersLoadedEvent())

                                response.body()?.let {
                                    compositeDisposable
                                            .add(Single.fromCallable {
                                                database.serverDao().insertAll(it)
                                            }
                                                    .observeOn(AndroidSchedulers.mainThread())
                                                    .subscribeOn(Schedulers.io())
                                                    .subscribe()
                                            )
                                }
                            } else {
                                run {
                                    App.instance.showToast(response.message() ?: "Unexpected error")
                                }
                            }

                            run { StateManager.instance.getServersIsInProgress.set(false) }
                        },
                        { error ->
                            run { StateManager.instance.getServersIsInProgress.set(false) }
                            run { App.instance.showToast(error.message ?: "Unexpected error") }
                        }))
    }

    fun getServersLiveData() = database.serverDao().getAll()
}
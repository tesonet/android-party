package android.example.myapplication.repository

import android.example.myapplication.util.*
import android.example.myapplication.util.Constants.Companion.NETWORK_TIMEOUT
import android.example.myapplication.util.ErrorHandling.Companion.UNABLE_TO_RESOLVE_HOST
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

abstract class NetworkBoundResource<ResponseObject, ViewStateType>(

) {
    private val result=MediatorLiveData<DataState<ViewStateType>>()
    protected lateinit var job: CompletableJob
    protected lateinit var coroutineScope: CoroutineScope

    init {
        setJob(initNewJob())
        setValue(DataState.loading(isLoading=true))
        coroutineScope.launch {
            //delay(TESTING_NETWORK_DELAY)
            withContext(Main) {
                // make network call
                val apiResponse=createCall()
                result.addSource(apiResponse) { response ->
                    result.removeSource(apiResponse)
                    coroutineScope.launch {
                        handleNetworkCall(response)
                    }
                }
            }
        }
        GlobalScope.launch(IO) {
            delay(NETWORK_TIMEOUT)
            if (!job.isCompleted) {
                println(" DEBUG: NetworkBoundResource: JOB NETWORK TIMEOUT.")
                job.cancel(CancellationException(UNABLE_TO_RESOLVE_HOST))
            }
        }

    }


    fun onCompleteJob(dataState: DataState<ViewStateType>){
        GlobalScope.launch(Main) {
            job.complete()
            setValue(dataState)
        }
    }

    private fun setValue(dataState: DataState<ViewStateType>) {
        result.value=dataState
    }

    @OptIn(InternalCoroutinesApi::class)
    private fun initNewJob(): Job {
        println("DEBUG: init new job")
        result.value=DataState.loading(true)
        job=Job()
        job.invokeOnCompletion(
            onCancelling=true,
            invokeImmediately=true,
            handler=object : CompletionHandler {
                override fun invoke(cause: Throwable?) {
                    if (job.isCancelled) {
                        println("DEBUG: NetworkBoundResource: Job has been cancelled.")
                    } else if (job.isCompleted) {
                        println("DEBUG: NetworkBoundResource: Job has been completed...")
                    }
                }

            })
        coroutineScope=CoroutineScope(IO + job)
        return job
    }

    private fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {

        when (response) {
            is ApiSuccessResponse -> {
                handleApiSuccessResponse(response)
            }
            is ApiErrorResponse -> {
                println("DEBUG: NetworkBoundResource: ${response.errorMessage}")
                onReturnError(response.errorMessage)
            }
            is ApiEmptyResponse -> {
                println("DEBUG: NetworkBoundResource: Request returned NOTHING (HTTP 204)")
                onReturnError("HTTP 204. Returned NOTHING.")
            }
        }
    }

    private fun onReturnError(message: String) {
        result.value=DataState.error(message)
    }

    abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

    abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

    fun asLiveData()=result as LiveData<DataState<ViewStateType>>

    abstract fun setJob(job: Job)

}
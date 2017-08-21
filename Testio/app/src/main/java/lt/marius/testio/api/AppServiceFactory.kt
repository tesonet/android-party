package lt.marius.testio.api

import android.content.Context

/**
 * Created by marius on 17.8.21.
 */
interface AppServiceFactory {
	fun createAppService(context: Context): AppService

	companion object {
		var mockAppServiceFactory: AppServiceFactory? = null
		fun create(): AppServiceFactory {
			mockAppServiceFactory?.let {
				return it
			}
			return ApiAppServiceFactory()
		}
	}
}
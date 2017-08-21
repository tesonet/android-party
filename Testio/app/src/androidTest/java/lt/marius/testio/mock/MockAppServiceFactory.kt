package lt.marius.testio.mock

import android.content.Context
import lt.marius.testio.api.AppService
import lt.marius.testio.api.AppServiceFactory

/**
 * Created by marius on 17.8.21.
 */
class MockAppServiceFactory : AppServiceFactory {
	override fun createAppService(context: Context): AppService {
		return MockAppService()
	}
}
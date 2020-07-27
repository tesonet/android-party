package lt.havefun.tesonetfunparty.servers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import lt.havefun.tesonetfunparty.MockedFailedRequestsApi
import lt.havefun.tesonetfunparty.RxSchedulerRule
import lt.havefun.tesonetfunparty.data.MainRepository
import lt.havefun.tesonetfunparty.data.Server
import lt.havefun.tesonetfunparty.data.db.CachedServersListRepository
import lt.havefun.tesonetfunparty.ui.main.ServersViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ServersNotReceivedViewModelUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var errorObserver: Observer<String>

    @Mock
    lateinit var dataObserver: Observer<List<Server>>

    private lateinit var serversViewModel: ServersViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val testDao = TestServerDao()
        val cachedServersRepo = CachedServersListRepository(testDao)
        val mockedApi = MockedFailedRequestsApi()
        serversViewModel = ServersViewModel(MainRepository(mockedApi), cachedServersRepo)
    }

    @Test
    fun dataReceived() {
        serversViewModel.data.observeForever(dataObserver)
        serversViewModel.getServersList()

        verify(dataObserver, never()).onChanged(listOf(Server("test",123)))
    }

    @Test
    fun errorReceived() {
        serversViewModel.error.observeForever(errorObserver)
        serversViewModel.getServersList()

        verify(errorObserver).onChanged("error")
    }
}
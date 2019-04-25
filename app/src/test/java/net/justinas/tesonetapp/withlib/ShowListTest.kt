package net.justinas.tesonetapp.withlib

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import net.justinas.minilist.domain.item.GetListItemsInteractor
import net.justinas.minilist.domain.item.IdEntity
import net.justinas.minilist.util.LoadResult
import net.justinas.minilist.view.list.ListViewModel
import net.justinas.tesonetapp.withlib.util.any
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ShowListTest {

    @Mock
    private lateinit var listInteractor: GetListItemsInteractor
    private lateinit var viewModel: ListViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    @Throws(Exception::class)
    fun `Give init block Should getList`() {
        // Given
        val orderItems = listOf(IdEntity(10, "yes", "tezt"), IdEntity(12, "no", "tezt"))
        `when`(listInteractor.execute(any())).thenReturn(Single.just(orderItems))

        //When
        viewModel = ListViewModel(listInteractor)

        // Should
        viewModel.result.observeForever {
            val result = (it as? LoadResult.Success)?.data
            MatcherAssert.assertThat(result, `is`(orderItems))
        }
        Mockito.verify(listInteractor).execute(any())
    }
}
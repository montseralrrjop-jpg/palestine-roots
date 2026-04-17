package com.palestine.roots.viewmodel

import android.app.Application
import com.palestine.roots.PalestineRootsApplication
import com.palestine.roots.domain.model.Site
import com.palestine.roots.domain.repository.SiteRepository
import com.palestine.roots.ui.states.SiteUiState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: SiteRepository
    private lateinit var application: PalestineRootsApplication
    private lateinit var viewModel: HomeViewModel

    private val sampleSites = listOf(
        Site(id = "1", name = "المسجد الأقصى", city = "القدس", category = "ديني", description = "وصف", history = "تاريخ", imageUrl = "", latitude = 31.7767, longitude = 35.2354, foundationYear = "705 م", isFavorite = false),
        Site(id = "2", name = "كنيسة المهد", city = "بيت لحم", category = "ديني", description = "وصف", history = "تاريخ", imageUrl = "", latitude = 31.7048, longitude = 35.2077, foundationYear = "339 م", isFavorite = false)
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
        application = mockk(relaxed = true)
        
        // Mock application.repository
        every { application.repository } returns repository
        
        // Mock default flows
        every { repository.getAllSites() } returns flowOf(sampleSites)
        every { repository.getFavoriteSites() } returns flowOf(emptyList())
        
        // PreferencesManager might need mocking if it's used in init
        // For simplicity, we'll assume it works or mock it if needed
        
        viewModel = HomeViewModel(application)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadAllSites should update uiState to Success when repository returns data`() = runTest {
        // Given
        every { repository.getAllSites() } returns flowOf(sampleSites)

        // When
        viewModel.loadAllSites()
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assert(state is SiteUiState.Success)
        assertEquals(sampleSites, (state as SiteUiState.Success).sites)
    }

    @Test
    fun `onSearchQueryChanged should update uiState with filtered results`() = runTest {
        // Given
        val query = "القدس"
        val filteredSites = listOf(sampleSites[0])
        every { repository.searchSites(query) } returns flowOf(filteredSites)

        // When
        viewModel.onSearchQueryChanged(query)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assert(state is SiteUiState.Success)
        assertEquals(filteredSites, (state as SiteUiState.Success).sites)
        assertEquals(query, viewModel.searchQuery.value)
    }

    @Test
    fun `onSearchQueryChanged with empty query should load all sites`() = runTest {
        // Given
        val query = ""
        every { repository.getAllSites() } returns flowOf(sampleSites)

        // When
        viewModel.onSearchQueryChanged(query)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assert(state is SiteUiState.Success)
        assertEquals(sampleSites, (state as SiteUiState.Success).sites)
    }

    @Test
    fun `filterByCity should update uiState with city results`() = runTest {
        // Given
        val city = "الخليل"
        val citySites = emptyList<Site>()
        every { repository.getSitesByCity(city) } returns flowOf(citySites)

        // When
        viewModel.filterByCity(city)
        advanceUntilIdle()

        // Then
        val state = viewModel.uiState.value
        assert(state is SiteUiState.Empty)
    }
}

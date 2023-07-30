package com.wiensmit.rmapp.presentation.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.wiensmit.rmapp.domain.museum.usecase.GetMuseumArtOverviewFeed
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.ui.overview.OverviewPager
import com.wiensmit.rmapp.presentation.ui.overview.OverviewViewModel
import com.wiensmit.rmapp.presentation.ui.overview.preview.OverviewArtPreviewProvider
import com.wiensmit.rmapp.presentation.ui.overview.ui.OverviewScreen
import io.mockk.coEvery
import io.mockk.mockk

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalFoundationApi
    @Test
    fun testOverviewClickEvents() {
        // given
        var clickedId = ""
        val onclick: ((String) -> Unit) = {
            clickedId = it
        }
        val getMuseumFeed = mockk<GetMuseumArtOverviewFeed>()
        val overviewVM = OverviewViewModel(OverviewPager(getMuseumFeed))
        val overviewItem = OverviewArtPreviewProvider.provideOverviewArtItem()
        coEvery { getMuseumFeed.invoke(any(), any()) } returns Result.success(
            listOf(overviewItem.copy(objectNumber = "123", artTitle = "test for click")) +
                (1..50).map {
                    overviewItem.copy(objectNumber = it.toString())
                }
        )

        composeTestRule.setContent {
            AppTheme() {
                OverviewScreen(
                    viewModel = overviewVM,
                    onItemClicked = onclick
                )
            }
        }
        //when
        composeTestRule.onNodeWithText("test for click").performClick()

        //then
        assertEquals("123", clickedId)
    }

    @ExperimentalFoundationApi
    @Test
    fun testOverviewErrorState() {
        // given
        val getMuseumFeed = mockk<GetMuseumArtOverviewFeed>()
        val overviewVM = OverviewViewModel(OverviewPager(getMuseumFeed))
        coEvery { getMuseumFeed.invoke(any(), any()) } returns Result.failure(Exception())

        composeTestRule.setContent {
            AppTheme() {
                OverviewScreen(
                    viewModel = overviewVM,
                    onItemClicked = { }
                )
            }
        }
        //when
        val overviewItem = OverviewArtPreviewProvider.provideOverviewArtItem()
        coEvery { getMuseumFeed.invoke(any(), any()) } returns Result.success(
            listOf(overviewItem.copy(objectNumber = "123", artTitle = "test for click")) +
                (1..50).map {
                    overviewItem.copy(objectNumber = it.toString())
                }
        )
        //then
        composeTestRule.onNodeWithTag("error_message").assertIsDisplayed()
        composeTestRule.onNodeWithTag("error_button").performClick()
        composeTestRule.onNodeWithText("test for click").assertIsDisplayed()
    }
}
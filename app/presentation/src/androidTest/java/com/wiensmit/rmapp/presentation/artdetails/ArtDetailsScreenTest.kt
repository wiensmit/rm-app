package com.wiensmit.rmapp.presentation.artdetails

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.wiensmit.rmapp.domain.museum.usecase.GetMuseumArtDetails
import com.wiensmit.rmapp.presentation.components.compose.theme.AppTheme
import com.wiensmit.rmapp.presentation.ui.artdetails.ArtDetailsViewModel
import com.wiensmit.rmapp.presentation.ui.artdetails.model.ArtDetailsSkeletonProvider
import com.wiensmit.rmapp.presentation.ui.artdetails.preview.ArtDetailsPreviewProvider
import com.wiensmit.rmapp.presentation.ui.artdetails.ui.ArtDetailsScreen
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ArtDetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testArtDetailsBackButton() {
        // given
        var clicked = false
        val onclick: (() -> Unit) = {
            clicked = true
        }
        val getMuseumArtDetails = mockk<GetMuseumArtDetails>()
        val overviewVM = ArtDetailsViewModel(getMuseumArtDetails, ArtDetailsSkeletonProvider())
        val detailsItem = ArtDetailsPreviewProvider.artDetailsPreview()
        coEvery { getMuseumArtDetails.invoke(any()) } returns Result.success(detailsItem.details)

        composeTestRule.setContent {
            AppTheme() {
                ArtDetailsScreen(
                    viewModel = overviewVM,
                    onCloseClicked = onclick
                )
            }
        }
        //when
        composeTestRule.onNodeWithTag("back_button").performClick()

        //then
        assertEquals(true, clicked)
    }

    @Test
    fun testArtDetailsErrorRetry() {
        // given
        val getMuseumArtDetails = mockk<GetMuseumArtDetails>()
        val overviewVM = spyk(ArtDetailsViewModel(getMuseumArtDetails, ArtDetailsSkeletonProvider()))
        coEvery { getMuseumArtDetails.invoke(any()) } returns Result.failure(Exception())

        composeTestRule.setContent {
            AppTheme() {
                ArtDetailsScreen(
                    viewModel = overviewVM,
                    onCloseClicked = {}
                )
            }
        }

        //when
        overviewVM.initVm("123")
        composeTestRule.waitUntil(1000) {
            composeTestRule
                .onAllNodesWithTag("error_message")
                .fetchSemanticsNodes().size == 1
        }

        composeTestRule.onNodeWithTag("error_button").performClick()

        //then
        verify { overviewVM.retry() }
    }

    @Test
    fun testArtDetailsSuccessData() {
        // given
        val getMuseumArtDetails = mockk<GetMuseumArtDetails>()
        val overviewVM = spyk(ArtDetailsViewModel(getMuseumArtDetails, ArtDetailsSkeletonProvider()))
        val detailsItem = ArtDetailsPreviewProvider.artDetailsPreview()
        coEvery { getMuseumArtDetails.invoke(any()) } returns Result.success(detailsItem.details)

        composeTestRule.setContent {
            AppTheme() {
                ArtDetailsScreen(
                    viewModel = overviewVM,
                    onCloseClicked = {}
                )
            }
        }

        //when
        overviewVM.initVm("123")

        //then
        composeTestRule.onNodeWithText(detailsItem.details.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(detailsItem.details.description!!).assertIsDisplayed()
        composeTestRule.onNodeWithText(detailsItem.details.presentingDate!!).assertIsDisplayed()
    }
}
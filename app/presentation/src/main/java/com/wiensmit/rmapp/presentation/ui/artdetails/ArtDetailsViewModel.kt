package com.wiensmit.rmapp.presentation.ui.artdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import com.wiensmit.rmapp.domain.museum.usecase.GetMuseumArtDetails
import com.wiensmit.rmapp.presentation.ui.artdetails.model.ArtDetailsDisplay
import com.wiensmit.rmapp.presentation.ui.artdetails.model.ArtDetailsSkeletonProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Art details view model, supplied ui with art details
 *
 * version 1.0
 */
@HiltViewModel
class ArtDetailsViewModel @Inject constructor(
    private val getMuseumArtDetails: GetMuseumArtDetails,
    artDetailsSkeletonProvider: ArtDetailsSkeletonProvider,
) : ViewModel() {

    private val _display = MutableStateFlow<ArtDetailsDisplay>(artDetailsSkeletonProvider.provideSkeleton())
    val display: StateFlow<ArtDetailsDisplay> = _display

    private var objectNumber: String? = null

    fun initVm(objectNumber: String) {
        this.objectNumber = objectNumber
        viewModelScope.launch {
            getMuseumArtDetails(objectNumber)
                .onSuccess {
                    _display.emit(ArtDetailsDisplay.Data(it))
                }
                .onFailure {
                    Timber.e(it)
                    _display.emit(ArtDetailsDisplay.Error)
                }
        }
    }

    fun retry() {
        objectNumber?.let { initVm(it) }
    }
}
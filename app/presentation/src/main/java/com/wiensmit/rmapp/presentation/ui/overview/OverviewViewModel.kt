package com.wiensmit.rmapp.presentation.ui.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Art overview view model, used as a cache for overviews
 *
 * version 1.0
 */
@HiltViewModel
class OverviewViewModel @Inject constructor(
    paging: OverviewPager,
) : ViewModel() {

    val overviews: Flow<PagingData<MuseumArtOverview>> = paging.getOverviews().cachedIn(viewModelScope)

}
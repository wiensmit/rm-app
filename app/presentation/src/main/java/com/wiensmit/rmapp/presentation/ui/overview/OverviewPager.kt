package com.wiensmit.rmapp.presentation.ui.overview

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wiensmit.rmapp.domain.museum.model.MuseumArtOverview
import com.wiensmit.rmapp.domain.museum.usecase.GetMuseumArtOverviewFeed
import com.wiensmit.rmapp.presentation.ui.overview.model.OverviewConstants
import timber.log.Timber
import javax.inject.Inject

/**
 * pager for paginating data for the art overview
 *
 * version 1.0
 */
class OverviewPager @Inject constructor(
    private val getMuseumOverviewFeed: GetMuseumArtOverviewFeed,
) {

    fun getOverviews() = Pager(
        config = PagingConfig(
            pageSize = OverviewConstants.PAGE_SIZE,
        ),
        pagingSourceFactory = {
            InternalPagingSource(getMuseumOverviewFeed)
        }
    ).flow

    internal class InternalPagingSource(
        private val getMuseumOverviewFeed: GetMuseumArtOverviewFeed,
    ) : PagingSource<Int, MuseumArtOverview>() {
        override fun getRefreshKey(state: PagingState<Int, MuseumArtOverview>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
            }
        }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MuseumArtOverview> {
            return try {
                val page = params.key ?: 1
                val response = getMuseumOverviewFeed(page = page, pageSize = OverviewConstants.PAGE_SIZE).getOrThrow()

                LoadResult.Page(
                    data = response,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.isEmpty()) null else page.plus(1),
                )
            } catch (e: Exception) {
                Timber.e(e)
                LoadResult.Error(e)
            }
        }
    }
}


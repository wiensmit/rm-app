package com.wiensmit.rmapp.domain.localization.usecase

import javax.inject.Inject

/**
 * supplies the current active locale, for now always en
 *
 * version 1.0
 */
class GetLocale @Inject constructor() {

    suspend operator fun invoke(): String {
        return "en"
    }
}

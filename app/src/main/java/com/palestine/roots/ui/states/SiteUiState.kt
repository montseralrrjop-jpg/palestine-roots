package com.palestine.roots.ui.states

import com.palestine.roots.domain.model.Site

/**
 * يمثل الحالات المختلفة لواجهة المستخدم.
 */
sealed class SiteUiState {
    object Loading : SiteUiState()
    data class Success(val sites: List<Site>) : SiteUiState()
    data class Error(val message: String) : SiteUiState()
    object Empty : SiteUiState()
}

package com.codelabs.foodapp.core.util

import androidx.annotation.StringRes
import com.codelabs.foodapp.R

sealed class UiText {
    data class DynamicString(val value:String):UiText()
    data class StringResource(@StringRes val id:Int):UiText()

    companion object{
        fun unknwonError():UiText{
            return UiText.StringResource(R.string.error_unknown)
        }
    }
}
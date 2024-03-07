package com.codelabs.foodapp.feature_prod.util

sealed class ResponseError : Error() {
    data object BadResponse:ResponseError()
}
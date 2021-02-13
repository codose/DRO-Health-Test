package com.drohealth.pharmacy.utils

/*
    Created By : Osemwingie Oshodin
    Github : https://github.com/codose
 */

sealed class ApiResponse<out T> {
    class Loading<out T> : ApiResponse<T>()
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Failure<out T>(val message: String, val code : Int = 0) : ApiResponse<T>()
}

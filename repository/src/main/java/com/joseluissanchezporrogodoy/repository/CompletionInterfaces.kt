package com.joseluissanchezporrogodoy.repository

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

internal interface SuccessCompletion<T> {
    fun successCompletion(e: T)
}

internal interface ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}

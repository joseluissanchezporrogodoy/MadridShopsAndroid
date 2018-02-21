package com.joseluissanchezporrogodoy.domain.interactor

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

interface SuccessCompletion<T> {
    fun successCompletion(e: T)
}

interface ErrorCompletion {
    fun errorCompletion(errorMessage: String)
}

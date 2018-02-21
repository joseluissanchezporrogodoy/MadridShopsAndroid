package com.joseluissanchez_porrogodoy.repository.network

import com.joseluissanchez_porrogodoy.repository.ErrorCompletion
import com.joseluissanchez_porrogodoy.repository.SuccessCompletion

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

internal interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}

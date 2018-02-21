package com.joseluissanchezporrogodoy.repository.network

import com.joseluissanchezporrogodoy.repository.ErrorCompletion
import com.joseluissanchezporrogodoy.repository.SuccessCompletion

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

internal interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}

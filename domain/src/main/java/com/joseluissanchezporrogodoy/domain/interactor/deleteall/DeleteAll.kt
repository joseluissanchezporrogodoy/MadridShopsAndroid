package com.joseluissanchezporrogodoy.domain.interactor.deleteall

import com.joseluissanchezporrogodoy.domain.interactor.CodeClosure
import com.joseluissanchezporrogodoy.domain.interactor.ErrorClosure

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface DeleteAll {
    fun execute(success: CodeClosure, error: ErrorClosure)
}

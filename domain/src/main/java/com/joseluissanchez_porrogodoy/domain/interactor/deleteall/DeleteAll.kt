package com.joseluissanchez_porrogodoy.domain.interactor.deleteall

import com.joseluissanchez_porrogodoy.domain.interactor.CodeClosure
import com.joseluissanchez_porrogodoy.domain.interactor.ErrorClosure

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface DeleteAll {
    fun execute(success: CodeClosure, error: ErrorClosure)
}

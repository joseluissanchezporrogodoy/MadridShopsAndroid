package com.joseluissanchez_porrogodoy.domain.interactor.internetstatus

import android.content.Context
import com.joseluissanchez_porrogodoy.domain.interactor.CodeClosure
import com.joseluissanchez_porrogodoy.domain.interactor.ErrorClosure

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface InternetStatusInteractor {
    fun execute(context: Context, success: CodeClosure, error: ErrorClosure)
}

package com.joseluissanchezporrogodoy.domain.interactor.internetstatus

import android.content.Context
import com.joseluissanchezporrogodoy.domain.interactor.CodeClosure
import com.joseluissanchezporrogodoy.domain.interactor.ErrorClosure

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface InternetStatusInteractor {
    fun execute(context: Context, success: CodeClosure, error: ErrorClosure)
}

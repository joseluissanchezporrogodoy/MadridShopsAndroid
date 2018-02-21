package com.joseluissanchez_porrogodoy.domain.interactor.deleteall

import android.content.Context
import com.joseluissanchez_porrogodoy.domain.interactor.CodeClosure
import com.joseluissanchez_porrogodoy.domain.interactor.ErrorClosure
import com.joseluissanchez_porrogodoy.repository.RepositoryImpl
import java.lang.ref.WeakReference

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
class DeleteAllImpl (context: Context) : DeleteAll {
    val weakContext = WeakReference<Context>(context)

    override fun execute(success: CodeClosure, error: ErrorClosure) {
        val repository = RepositoryImpl(weakContext.get() !!)

        repository.deleteAll(success, error)
    }
}

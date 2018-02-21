package com.joseluissanchez_porrogodoy.domain.interactor.getallactivities

import android.content.Context
import com.joseluissanchez_porrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchez_porrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchez_porrogodoy.domain.mappers.EntitiyRepositoryIntoEntityModelMapper
import com.joseluissanchez_porrogodoy.domain.model.EntitiesModel
import com.joseluissanchez_porrogodoy.repository.Repository
import com.joseluissanchez_porrogodoy.repository.RepositoryImpl
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImpl(context: Context) : GetAllActivitiesInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get() !!)
    override fun execute(success: SuccessCompletion<EntitiesModel>, error: ErrorCompletion) {
        repository.getAllActivities(success = {
            val shops: EntitiesModel = EntitiyRepositoryIntoEntityModelMapper.map(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }
}

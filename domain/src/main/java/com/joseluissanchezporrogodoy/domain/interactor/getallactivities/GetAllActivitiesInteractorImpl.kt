package com.joseluissanchezporrogodoy.domain.interactor.getallactivities

import android.content.Context
import com.joseluissanchezporrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchezporrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchezporrogodoy.domain.mappers.EntitiyRepositoryIntoEntityModelMapper
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel
import com.joseluissanchezporrogodoy.repository.Repository
import com.joseluissanchezporrogodoy.repository.RepositoryImpl
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

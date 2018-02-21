package com.joseluissanchez_porrogodoy.domain.interactor.getallactivities

import com.joseluissanchez_porrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchez_porrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchez_porrogodoy.domain.model.EntitiesModel

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<EntitiesModel>, error: ErrorCompletion)
}

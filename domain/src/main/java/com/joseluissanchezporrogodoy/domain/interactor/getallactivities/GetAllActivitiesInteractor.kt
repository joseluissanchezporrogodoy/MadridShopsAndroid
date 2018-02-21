package com.joseluissanchezporrogodoy.domain.interactor.getallactivities

import com.joseluissanchezporrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchezporrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface GetAllActivitiesInteractor {
    fun execute(success: SuccessCompletion<EntitiesModel>, error: ErrorCompletion)
}

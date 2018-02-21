package com.joseluissanchez_porrogodoy.domain.interactor

import com.joseluissanchez_porrogodoy.domain.model.EntitiesModel

        /**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (entities: EntitiesModel) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any

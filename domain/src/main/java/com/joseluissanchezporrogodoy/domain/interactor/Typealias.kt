package com.joseluissanchezporrogodoy.domain.interactor

import com.joseluissanchezporrogodoy.domain.model.EntitiesModel

        /**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (entities: EntitiesModel) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any

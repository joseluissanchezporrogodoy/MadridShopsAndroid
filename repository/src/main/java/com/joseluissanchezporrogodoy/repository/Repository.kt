package com.joseluissanchezporrogodoy.repository

import com.joseluissanchezporrogodoy.repository.model.Entity

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
interface Repository {
    fun getAllShops(success: (shops: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAll(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllActivities(success: (shops: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit)

}

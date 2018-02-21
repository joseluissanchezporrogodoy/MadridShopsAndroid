package com.joseluissanchez_porrogodoy.repository.cache

import com.joseluissanchez_porrogodoy.repository.model.Entity


/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
internal interface Cache {
    fun getAllShops(success: (shops: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShops(shops: List<Entity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllActivities(success: (activities: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllActivities(activities: List<Entity>, success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun deleteAll(success: () -> Unit, error: (errorMessage: String) -> Unit)
}

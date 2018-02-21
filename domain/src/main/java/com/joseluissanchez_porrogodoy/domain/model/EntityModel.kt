package com.joseluissanchez_porrogodoy.domain.model

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

import java.io.Serializable
import java.util.*

/**
 * Entity: represents one Entity
 *
 */
data class EntityModel
(
                val type: Int,
                val id: Long,
                val name: String,
                val address: String,
                val imageUrl: String,
                val logoUrl: String,
                val description: String,
                val openingHours: String,
                val latitude: Float,
                val longitude: Float): Serializable {
    init {
        EntitiesModel(ArrayList<EntityModel>())
    }
}

class EntitiesModel(val entities: MutableList<EntityModel>): Aggregate<EntityModel> {
    override fun count(): Int = entities.size

    override fun all(): List<EntityModel> = entities

    override fun get(position: Int): EntityModel {
        return entities.get(position)
    }

    override fun add(element: EntityModel) {
        entities.add(element)
    }

    override fun delete(position: Int) {
        entities.removeAt(position)
    }

    override fun delete(element: EntityModel) {
        entities.remove(element)
    }
}


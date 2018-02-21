package com.joseluissanchez_porrogodoy.domain.mappers

import android.util.Log
import com.joseluissanchez_porrogodoy.domain.model.EntitiesModel
import com.joseluissanchez_porrogodoy.domain.model.EntityModel
import com.joseluissanchez_porrogodoy.repository.model.Entity
import java.util.ArrayList

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
object EntitiyRepositoryIntoEntityModelMapper {

    fun map(entities: List<Entity>): EntitiesModel {
        val entityArr = ArrayList<EntityModel>()

        for (entity in entities) {
            val shop = EntityModel(
                    entity.type,
                    entity.id,
                    entity.name,
                    entity.address,
                    entity.img,
                    entity.logo,
                    entity.description,
                    entity.openingHours,
                    getCorrectCoordinateComponent(entity.latitude),
                    getCorrectCoordinateComponent(entity.longitude))


            entityArr.add(shop)
        }

        var ent = EntitiesModel(entityArr)
        return ent
    }

    fun getCorrectCoordinateComponent(coordinateComponent: String): Float {
        // problem: JSON has this errors "-3.9018104,275"
        var coordinate = 0.0f

        val s = coordinateComponent.replace(",", "")
        try {
            coordinate = java.lang.Float.parseFloat(s)
        } catch (e: Exception) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent))
        }

        return coordinate
    }
}

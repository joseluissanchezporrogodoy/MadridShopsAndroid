package com.joseluissanchez_porrogodoy.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.joseluissanchez_porrogodoy.repository.db.DBConstants
import com.joseluissanchez_porrogodoy.repository.db.DBHelper
import com.joseluissanchez_porrogodoy.repository.model.Entity

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
class EntityDAO
(
        val dbHelper: DBHelper

): DAOPersistable<Entity>
{
    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun insert(element: Entity, type: Int): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(DBConstants.TABLE_ENTITY, null, contentValues(element))

        return id
    }

    fun contentValues(entity: Entity): ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_ENTITY_ID_JSON, entity.id)
        content.put(DBConstants.KEY_ENTITY_NAME , entity.name)
        content.put(DBConstants.KEY_ENTITY_DESCRIPTION , entity.description)
        content.put(DBConstants.KEY_ENTITY_LATITUDE , entity.latitude)
        content.put(DBConstants.KEY_ENTITY_LONGITUDE , entity.longitude)
        content.put(DBConstants.KEY_ENTITY_IMAGE_URL , entity.img)
        content.put(DBConstants.KEY_ENTITY_LOGO_IMAGE_URL , entity.logo)
        content.put(DBConstants.KEY_ENTITY_ADDRESS , entity.address)
        content.put(DBConstants.KEY_ENTITY_OPENING_HOURS , entity.openingHours)

        return content
    }

    override fun delete(element: Entity): Long {
        if (element.databaseId < 1) {
            return 0
        }

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_ENTITY,
                DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_ENTITY,
                null,
                null
        ).toLong() >= 0
    }

    override fun query(id: Long): Entity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!
    }

    override fun query(): List<Entity> {
        val queryResult = ArrayList<Entity>()

        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ENTITY_DATABASE_ID)

        while (cursor.moveToNext()) {
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        return queryResult
    }

    fun entityFromCursor(cursor: Cursor): Entity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return Entity(
                cursor.getInt(cursor.getColumnIndex(DBConstants.KEY_ENTITY_TYPE)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ENTITY_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ENTITY_ADDRESS)))
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_ENTITY_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_ENTITY_DATABASE_ID
        )
        return cursor
    }

     override fun query(type: Int):  List<Entity> {
        val queryResult = ArrayList<Entity>()
        val cursor = dbReadOnlyConnection.query(DBConstants.TABLE_ENTITY,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_ENTITY_TYPE + " = ?",
                arrayOf(type.toString()),
                "",
                "",
                DBConstants.KEY_ENTITY_DATABASE_ID
        )
         while (cursor.moveToNext()) {
             val se = entityFromCursor(cursor)
             queryResult.add(se!!)
         }

         return queryResult
    }

    override fun update(id: Long, element: Entity): Long {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstants.TABLE_ENTITY,
                contentValues(element)
                , DBConstants.KEY_ENTITY_DATABASE_ID + " = ?", arrayOf(id.toString()))
        return numberOfRecordsUpdated.toLong()
    }


}

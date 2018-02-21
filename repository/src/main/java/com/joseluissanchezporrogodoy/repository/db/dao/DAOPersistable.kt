package com.joseluissanchezporrogodoy.repository.db.dao

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

import android.database.Cursor

interface DAOReadOperations<T> {
    fun query(id: Long): T
    fun query(type: Int): List<T>
    fun query(): List<T>
    fun queryCursor(id: Long): Cursor
}

interface DAOWriteOperations<T> {
    fun insert(element: T, type: Int): Long
    fun update(id: Long, element: T): Long

    /**
     * deletes the element passed from DB
     */
    fun delete(element: T): Long

    /**
     * deletes the element with id from DB
     */
    fun delete(id: Long): Long
    fun deleteAll(): Boolean
}

interface DAOPersistable<T>: DAOReadOperations<T>, DAOWriteOperations<T>


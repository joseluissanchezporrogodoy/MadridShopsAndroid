package com.joseluissanchezporrogodoy.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.joseluissanchezporrogodoy.repository.cache.Cache
import com.joseluissanchezporrogodoy.repository.cache.CacheImpl
import com.joseluissanchezporrogodoy.repository.model.Entity
import com.joseluissanchezporrogodoy.repository.model.ResponseEntity
import com.joseluissanchezporrogodoy.repository.network.GetJsonManager
import com.joseluissanchezporrogodoy.repository.network.GetJsonManagerVolleyImpl
import com.joseluissanchezporrogodoy.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */

class RepositoryImpl(context: Context): Repository {


    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImpl(weakContext.get() !!)

    override fun getAllShops(success: (shops: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // read all Shops from cache
        cache.getAllShops(
                success = {
                    // if there's shops in cache --> return them

                    success(it)
                }, error = {
            // if no shops in cache --> network

            populateCache(success, error)
        })
    }

    private fun populateCache(success: (shops: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get() !!)
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, success =  object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                var responseEntity: ResponseEntity
                try {
                    responseEntity = parser.parse<ResponseEntity>(e)
                } catch (e: InvalidFormatException) {
                    error("ERROR PARSING")
                    return
                }
                // store result in cache
                cache.saveAllShops(responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happened on the way to heaven!")
                })
            }
        }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }


    override fun deleteAll(success: () -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAll(success, error)
    }
    override fun getAllActivities(success: (activities: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // read all Shops from cache
        cache.getAllActivities(
                success = {
                    // if there's shops in cache --> return them

                    success(it)
                }, error = {
            // if no shops in cache --> network

            populateCache(success, error)
        })
    }


}

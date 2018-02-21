package com.joseluissanchezporrogodoy.repository.cache

import android.content.Context

import com.joseluissanchezporrogodoy.repository.DispatchOnMainTread
import com.joseluissanchezporrogodoy.repository.db.DBHelper
import com.joseluissanchezporrogodoy.repository.db.build
import com.joseluissanchezporrogodoy.repository.db.dao.EntityDAO
import com.joseluissanchezporrogodoy.repository.model.Entity
import java.lang.ref.WeakReference

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
internal class CacheImpl(context: Context): Cache {
    val context = WeakReference<Context>(context)

    override fun getAllShops(success: (shops: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops = EntityDAO(cacheDBHelper()).query(type = 0)
            DispatchOnMainTread(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("No shops")
                }
            })
        }).run()
    }

    override fun saveAllShops(shops: List<Entity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { EntityDAO(cacheDBHelper()).insert(it,type = 0) }

                DispatchOnMainTread(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainTread(Runnable {
                    error("Error inserting shops")
                })
            }
        }).run()
    }

    override fun getAllActivities(success: (activities: List<Entity>) -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var shops = EntityDAO(cacheDBHelper()).query(type = 1)
            DispatchOnMainTread(Runnable {
                if (shops.count() > 0) {
                    success(shops)
                } else {
                    error("No shops")
                }
            })
        }).run()
    }

    override fun saveAllActivities(activities: List<Entity>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                activities.forEach { EntityDAO(cacheDBHelper()).insert(it,type = 1) }

                DispatchOnMainTread(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainTread(Runnable {
                    error("Error inserting shops")
                })
            }
        }).run()
    }

    override fun deleteAll(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = EntityDAO(cacheDBHelper()).deleteAll()
            DispatchOnMainTread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
            })
        }).run()
    }

    private fun cacheDBHelper(): DBHelper {
        return build(context.get() !!, "MadridShops.sqlite", 1)
    }

}

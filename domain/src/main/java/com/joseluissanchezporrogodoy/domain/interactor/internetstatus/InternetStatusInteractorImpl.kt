package com.joseluissanchezporrogodoy.domain.interactor.internetstatus

import android.content.Context
import android.net.ConnectivityManager

import com.joseluissanchezporrogodoy.domain.interactor.CodeClosure
import com.joseluissanchezporrogodoy.domain.interactor.ErrorClosure

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(context: Context,success: CodeClosure, error: ErrorClosure) {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

            if (connectivityManager != null) {
                val netInfo = connectivityManager.activeNetworkInfo

                if (netInfo != null && netInfo.isConnected) {
                    success()
                } else {
                    error("Conexion error. Unable connect to server.")
                }
            } else {
                error("Conexion error. Unable connect to server.")
            }
    }
}


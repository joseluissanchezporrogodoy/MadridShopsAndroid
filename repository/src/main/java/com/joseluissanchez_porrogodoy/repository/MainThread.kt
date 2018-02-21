package com.joseluissanchez_porrogodoy.repository

import android.os.Handler
import android.os.Looper

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
fun DispatchOnMainTread(codeToRun: Runnable) {
    val uiHandler = Handler(Looper.getMainLooper())
    uiHandler.post(codeToRun)
}

package appart.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.joseluissanchezporrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchezporrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchezporrogodoy.domain.interactor.getallactivities.GetAllActivitiesInteractorImpl
import com.joseluissanchezporrogodoy.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel


/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
class MadridShopsApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")




        /*

        DeleteAllShopsImpl(this).execute(success = {
            Log.d("success", "success")
        }, error = {
            Log.d("error", "error deleting " + it)

        })
    */
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}

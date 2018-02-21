package appart.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.joseluissanchez_porrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchez_porrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchez_porrogodoy.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.joseluissanchez_porrogodoy.domain.model.EntitiesModel

/**
 * Created by joseluissanchez-porrogodoy on 21/02/2018.
 */
class MadridShopsApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")


        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(object: SuccessCompletion<EntitiesModel> {
            override fun successCompletion(shops: EntitiesModel) {
                Log.d("Shops", "Count: " + shops.count())

                shops.entities.forEach { Log.d("Shop", it.name) }
            }
        }, object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("ERROR", errorMessage)
            }
        })





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

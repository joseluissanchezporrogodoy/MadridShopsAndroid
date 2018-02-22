package appart.madridshops.router

import android.content.Intent
import appart.madridshops.activity.MainMenuActivity
import appart.madridshops.activity.MapListActivity
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel

/**
 * Created by joseluissanchez-porrogodoy on 22/02/2018.
 */
class Router {
    fun navigateeFromMainMenuActivityToMapListActivity(main: MainMenuActivity, items: EntitiesModel){

        val intent = Intent(main, MapListActivity::class.java)
        intent.putExtra(MapListActivity.ENTITIES_LIST, items)
        main.startActivity(intent)
    }

}

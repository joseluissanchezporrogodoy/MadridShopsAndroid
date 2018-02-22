package appart.madridshops.activity

import android.animation.ValueAnimator
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import appart.madridshops.R
import appart.madridshops.router.Router
import com.joseluissanchezporrogodoy.domain.interactor.ErrorCompletion
import com.joseluissanchezporrogodoy.domain.interactor.SuccessCompletion
import com.joseluissanchezporrogodoy.domain.interactor.getallactivities.GetAllActivitiesInteractorImpl
import com.joseluissanchezporrogodoy.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.joseluissanchezporrogodoy.domain.interactor.internetstatus.InternetStatusInteractorImpl
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity() {

    var shopItems: EntitiesModel? = null
    var activitiesItems: EntitiesModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        checkInternetStatus()
        shops_button.visibility = View.GONE
        activity_button.visibility = View.GONE
        shops_button.setOnClickListener{
            goToShopVView()
        }
        activity_button.setOnClickListener{
            goToActivitiesView()
        }

    }
    private fun checkInternetStatus() {


        InternetStatusInteractorImpl().execute(this, success = {

            getShops()
            getActivities()

        }, error = {
            AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(it)
                    .setPositiveButton("Retry?", { dialog, which ->
                        dialog.dismiss()
                        checkInternetStatus()
                    })
                    .setNegativeButton("Exit", { dialog, which ->
                        finish()
                    })
                    .show()
        })
    }

    private fun goToActivitiesView() {
        Router().navigateeFromMainMenuActivityToMapListActivity(this,shopItems!!)
    }

    private fun goToShopVView() {
        Router().navigateeFromMainMenuActivityToMapListActivity(this,activitiesItems!!)
    }

    private fun getShops() {
        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(object : SuccessCompletion<EntitiesModel> {
            override fun successCompletion(shops: EntitiesModel) {
                Log.d("Shops", "Count: " + shops.count())
                progressBar.visibility = View.GONE
                shops.entities.forEach { Log.d("Shop", it.name) }
                shopItems  = shops
            }
        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d("ERROR", errorMessage)
                val snack = Snackbar.make(layout, "Error", Snackbar.LENGTH_LONG)
                snack.show()
                progressBar.visibility = View.GONE

            }
        })
    }

    private fun getActivities() {
        progressBar.visibility = View.VISIBLE
        val allActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        allActivitiesInteractor.execute(object : SuccessCompletion<EntitiesModel> {
            override fun successCompletion(shops: EntitiesModel) {
                Log.d("Activities", "Count: " + shops.count())
                progressBar.visibility = View.GONE
                shops.entities.forEach { Log.d("Activity", it.name) }
                activitiesItems = shops
                showAndAnimateButtons()

            }
        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                val snack = Snackbar.make(layout, "Error", Snackbar.LENGTH_LONG)
                snack.show()
                progressBar.visibility = View.GONE
                Log.d("ERROR", errorMessage)
            }
        })
    }
    fun showAndAnimateButtons(){

        shops_button.visibility = View.VISIBLE
        activity_button.visibility = View.VISIBLE

        val animator = ValueAnimator.ofFloat(0f, 200.0f)

        animator.addUpdateListener {
            val value = it.animatedValue as Float
            shops_button.translationY = value
            activity_button.translationY = value
        }

        // 2
        animator.repeatMode = ValueAnimator.REVERSE
        // 3
        animator.repeatCount = 5

        // 4
        animator.duration = 500L
        animator.start()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        getMenuInflater()?.inflate(R.menu.settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.reintent) {
            checkInternetStatus()
        }

        return true
    }

}

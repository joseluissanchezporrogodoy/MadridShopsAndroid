package appart.madridshops.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import appart.madridshops.R
import appart.madridshops.adapters.InfoWindowArrayAdapter
import appart.madridshops.adapters.RecyclerViewAdapter
import appart.madridshops.fragments.ListFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel
import com.joseluissanchezporrogodoy.domain.model.EntityModel

class MapListActivity : AppCompatActivity(), RecyclerViewAdapter.OnEntitySelectedListener {

    companion object {
        val ENTITIES_LIST = "ENTITIES_LIST"
    }


    lateinit var items: EntitiesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_list)
        items = intent.getSerializableExtra(ENTITIES_LIST) as EntitiesModel
        setupMapAndList()
    }


    private fun setupMapAndList() {
        initializeMap(items)
        addList(items)
    }

    private fun addList(entity: EntitiesModel) {
        val fragment = ListFragment.newInstance()
        fragment.list = entity
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_list_fragment,fragment)
                .commit()
    }

    private fun initializeMap(entities: EntitiesModel) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment

        mapFragment.getMapAsync({
            centerMapInPosition(it,40.416775, -3.703790)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext,it)
            it.setInfoWindowAdapter(InfoWindowArrayAdapter.InfoWindowArrayAdapter(context = this))
            map = it
            addAllPins(entities)
            map!!.setOnInfoWindowClickListener {
                Log.d("Map", "Me has tocado???")
            }
        })

    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double){
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder().
                target(coordinate).
                zoom(15f).
                build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    private fun showUserPosition(context: Context, map: GoogleMap) {

        if (android.os.Build.VERSION.SDK_INT > 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 10)

                return
            } else {
                map.isMyLocationEnabled = true
            }
        } else {
            map.isMyLocationEnabled = true
        }


    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException){

            }
        }
    }

    private fun addAllPins(entities: EntitiesModel) {
        entities.all().map {
            addPin(this.map !!,it.latitude.toDouble(), it.longitude.toDouble(), it.name, it)
        }
    }

    private fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String, entity: EntityModel) {
        var marker = map.addMarker(MarkerOptions().position(LatLng(latitude,longitude)).title(title))
        marker.tag = entity
    }


    /// Listener for selected entity in list
    override fun onEntitySelected(entity: EntityModel, position: Int) {
        Log.d("Seleccionado", entity.name)

        startActivity(DetailActivity.intent(this,entity))
    }
}

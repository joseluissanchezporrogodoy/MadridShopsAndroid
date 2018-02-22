package appart.madridshops.adapters

import android.content.Context

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import appart.madridshops.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.joseluissanchezporrogodoy.domain.model.EntityModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.marker_info.view.*

/**
 * Created by joseluissanchez-porrogodoy on 22/02/2018.
 */
class InfoWindowArrayAdapter {
    class InfoWindowArrayAdapter(val context: Context): GoogleMap.InfoWindowAdapter {
        override fun getInfoContents(p0: Marker): View {
            val view = LayoutInflater.from(context).inflate(R.layout.marker_info, null)

            if (p0.tag is EntityModel) {
                val shop = p0.tag as EntityModel


                view.name_list.text = shop.name
                view.hours_list.text = shop.openingHours
                val shopImage = view.image_list

                Picasso
                        .with(context)
                        .load(shop.logoUrl)
                        .placeholder(R.drawable.no_image)
                        .into(shopImage, object: Callback {
                            override fun onSuccess() {
                                if(p0.isInfoWindowShown){
                                    p0.hideInfoWindow()
                                    Picasso
                                            .with(context)
                                            .load(shop.logoUrl)
                                            .placeholder(R.drawable.no_image)
                                            .into(shopImage)
                                    p0.showInfoWindow()
                                }
                            }

                            override fun onError() {
                                Log.d("PICASSO", " Error to update marker in google map")
                            }


                        } )

            }

            return view
        }

        override fun getInfoWindow(p0: Marker?): View? {
            return  null
        }
    }

}

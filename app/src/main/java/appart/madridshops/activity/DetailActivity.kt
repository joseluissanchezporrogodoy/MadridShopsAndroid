package appart.madridshops.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import appart.madridshops.R
import com.joseluissanchezporrogodoy.domain.model.EntityModel

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    companion object {

        val EXTRA_ENTITY = "EXTRA_ENTITY"

        fun intent(context: Context, entity: EntityModel) : Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ENTITY, entity)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var shop = intent.getSerializableExtra(EXTRA_ENTITY) as EntityModel

        Picasso.with(this).load(getMapImageUrl(shop)).placeholder(R.drawable.map_placeholder).fit().into(image_detail)
        description_detail.text = shop.description
        title = shop.name
    }


    fun getMapImageUrl(shop: EntityModel): String {

        return String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x220&markers=%f,%f",
                shop.latitude, shop.longitude,shop.latitude,shop.longitude)
    }


}

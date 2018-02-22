package appart.madridshops.adapters
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import appart.madridshops.R
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel
import com.joseluissanchezporrogodoy.domain.model.EntityModel

import com.squareup.picasso.Picasso

/**
 * Created by joseluissanchez-porrogodoy on 17/02/2018.
 */
class RecyclerViewAdapter(private val entities:EntitiesModel, private val listener: OnEntitySelectedListener?): RecyclerView.Adapter<RecyclerViewAdapter.EntitiesListViewHolder>() {


    private var onEntitySelectedListener: RecyclerViewAdapter.OnEntitySelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EntitiesListViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item,parent,false)
        onEntitySelectedListener = listener
        return EntitiesListViewHolder(view)
    }

    override fun getItemCount(): Int {
       return entities.count()
    }

    override fun onBindViewHolder(holder: EntitiesListViewHolder?, position: Int) {
       holder?.bindEntity(entities.get(position), position)
    }


    inner  class EntitiesListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameTextView = itemView.findViewById<TextView>(R.id.name)
        val imageView = itemView.findViewById<ImageView>(R.id.image)
        val hourTextView = itemView.findViewById<TextView>(R.id.hours)

        fun bindEntity(entity: EntityModel , position: Int){
            val context = itemView.context
            nameTextView.text  = entity.name
            hourTextView.text = entity.openingHours
            Picasso.with(context).load(entity.logoUrl).fit().into(imageView)
            itemView.setOnClickListener {
                onEntitySelectedListener?.onEntitySelected(entity,position)
            }
        }


    }

    interface OnEntitySelectedListener{
        fun onEntitySelected(entity: EntityModel,position: Int )
    }

}

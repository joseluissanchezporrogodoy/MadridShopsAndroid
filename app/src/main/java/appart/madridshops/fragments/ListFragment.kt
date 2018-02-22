package appart.madridshops.fragments


import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import appart.madridshops.R
import appart.madridshops.adapters.RecyclerViewAdapter
import com.joseluissanchezporrogodoy.domain.model.EntitiesModel




/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }
    lateinit var entityListRecyclerView: RecyclerView
    lateinit var root: View
    lateinit var list : EntitiesModel
    lateinit var adapter : RecyclerViewAdapter

    var onEntitySelectedListener: RecyclerViewAdapter.OnEntitySelectedListener? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if( inflater != null){
            root = inflater.inflate(R.layout.fragment_list,container,false)
            entityListRecyclerView = root.findViewById(R.id.entity_list)
            entityListRecyclerView.layoutManager = LinearLayoutManager(activity)
            entityListRecyclerView.itemAnimator = DefaultItemAnimator()

            // Adapter
            adapter = RecyclerViewAdapter(list,onEntitySelectedListener)
            entityListRecyclerView.adapter = adapter
        }
        return root
    }
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    fun commonOnAttach(context: Context?) {
        if (context is RecyclerViewAdapter.OnEntitySelectedListener) {
            onEntitySelectedListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onEntitySelectedListener = null
    }

}

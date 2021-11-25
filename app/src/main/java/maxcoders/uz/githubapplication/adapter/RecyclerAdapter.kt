package maxcoders.uz.githubapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recylcer_list_items.view.*
import maxcoders.uz.githubapplication.R
import maxcoders.uz.githubapplication.model.RecyclerData

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var listData: List<RecyclerData>? = null

    fun setUpdateData(listData: List<RecyclerData>){
        this.listData = listData
    }

    class MyViewHolder(view:View): RecyclerView.ViewHolder(view){
        val imageView = view.imageView
        val textViewName = view.textviewName
        val coder = view.coder

        fun bind(data: RecyclerData){
            textViewName.setText(data.name)
            coder.setText(data.languages_url)

            Glide.with(imageView)
                .load(data.owmer?.avatar_url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.recylcer_list_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)

    }

    override fun getItemCount(): Int {
        if (listData == null) return 0
        else return listData?.size!!
    }
}
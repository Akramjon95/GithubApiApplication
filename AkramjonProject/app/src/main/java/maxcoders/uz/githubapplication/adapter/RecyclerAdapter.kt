package maxcoders.uz.githubapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.recylcer_list_items.view.*
import maxcoders.uz.githubapplication.R
import maxcoders.uz.githubapplication.model.Item

class RecyclerAdapter(var listener: Listener) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    private var listData = mutableListOf<Item>()

    fun submitList(items: MutableList<Item>) {
        this.listData.clear()
        this.listData.addAll(items)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.imageView
        val textViewName = view.textviewName
        val coder = view.coder

        init {
            view.setOnClickListener {
                listener.onItemClick(listData[adapterPosition])
            }
        }

        fun bind(data: Item) {
            textViewName.text = "Name: " + data.name
            coder.text ="Language: " + data.language

            data.owner?.avatar_url?.let {
                Glide.with(imageView)
                    .load(it)
                    .apply(RequestOptions.centerCropTransform())
                    .into(imageView)
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recylcer_list_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listData[position])

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    interface Listener {
        fun onItemClick(item: Item)
    }


}
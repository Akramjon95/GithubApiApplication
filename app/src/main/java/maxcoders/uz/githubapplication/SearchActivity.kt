package maxcoders.uz.githubapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_search.*
import maxcoders.uz.githubapplication.adapter.RecyclerAdapter
import maxcoders.uz.githubapplication.model.RecyclerList

class SearchActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerAdapter
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
    }

    private fun initViewModel(){
        viewModel.getLiveDataObserver().observe(this,object : Observer<RecyclerList>{
            override fun onChanged(t: RecyclerList?) {
                if (t != null){
                    adapter.setUpdateData(t.items)
                    adapter.notifyDataSetChanged()
                } else{
                    Toast.makeText(this@SearchActivity, "Error getting the data !", Toast.LENGTH_LONG).show()

                }
            }
        })
        viewModel.makeApicall()
    }
}
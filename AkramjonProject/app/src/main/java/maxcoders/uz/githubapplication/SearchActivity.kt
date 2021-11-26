package maxcoders.uz.githubapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import maxcoders.uz.githubapplication.adapter.RecyclerAdapter
import maxcoders.uz.githubapplication.data.GithubViewModel
import maxcoders.uz.githubapplication.model.Item
import maxcoders.uz.githubapplication.util.Resource

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), RecyclerAdapter.Listener {

    private lateinit var adapter: RecyclerAdapter
    private lateinit var viewModel:GithubViewModel

    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(toolbar2)

        viewModel = ViewModelProvider(this).get(GithubViewModel::class.java)
        initRecyclerView()

        viewModel.users.observe(this, Observer { response->
            Log.d(TAG, "onCreate: response "+response.data)
            when(response){
                is Resource.Loading->{
                   progressbarrr.visibility = View.VISIBLE
                }
                is Resource.Error->{
                    progressbarrr.visibility = View.GONE
                    response.message?.let {
                        Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Success->{
                    progressbarrr.visibility = View.GONE
                    response.data?.let { userResponse ->
                        adapter.submitList(userResponse.items as MutableList<Item>)
                    }
                }
            }

        })


    }

    private fun initRecyclerView(){
        adapter = RecyclerAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchview: SearchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextSubmit: "+p0)
                if (!p0.isNullOrEmpty()){
                    viewModel.searchUser(p0)
                }



                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d(TAG, "onQueryTextChange: "+p0)
                return false
            }

        })

        return true
    }

    override fun onItemClick(item: Item) {
        Log.d(TAG, "onItemClick: item "+item.name)
        val inten = Intent(this, UserActivity::class.java)
        inten.putExtra("full_name", item.full_name)
        inten.putExtra("avatar", item.owner?.avatar_url)
        inten.putExtra("description", item.description)
        inten.putExtra("language", item.language)
        startActivity(inten)
    }


}
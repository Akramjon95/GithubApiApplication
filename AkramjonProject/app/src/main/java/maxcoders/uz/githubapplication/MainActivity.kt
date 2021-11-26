package maxcoders.uz.githubapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import maxcoders.uz.githubapplication.data.GithubViewModel
import maxcoders.uz.githubapplication.util.Resource

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var viewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(GithubViewModel::class.java)

        checkLogin()
    }

    private fun checkLogin(){



            checkLogin.setOnClickListener {

                val text = login_user_et.text.toString().trim()
                if(text.isNotEmpty()) {

                    viewModel.loginUser(text)

                    viewModel.usersLogin.observe(this, Observer { loginResponse ->
                        when(loginResponse){
                            is Resource.Loading -> {
                                progress_login.visibility = View.VISIBLE
                            }
                            is Resource.Error ->{
                                progress_login.visibility = View.GONE
                                loginResponse.message?.let {
                                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                                }
                            }
                            is Resource.Success -> {
                                progress_login.visibility = View.GONE
                                loginResponse.data?.let {
                                    startActivity(Intent(this, SearchActivity::class.java))
                                }

                            }

                        }
                    })


                }
                else {
                    //progress_login.visibility = View.GONE
                    Toast.makeText(this, "You should enter a login", Toast.LENGTH_LONG).show()
                }
        }
    }
}
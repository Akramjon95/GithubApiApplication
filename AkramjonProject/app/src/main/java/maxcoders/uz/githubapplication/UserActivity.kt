package maxcoders.uz.githubapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val intent = intent
        val full_name = intent.getStringExtra("full_name")
        val avatar = intent.getStringExtra("avatar")
        val description = intent.getStringExtra("description")
        val language = intent.getStringExtra("language")

        if (intent != null) {
            user_name.text = "Name: " + full_name
            user_desc.text = "Desription: " + description
            user_language.text = "Profession: " + language

            Glide.with(this)
                .load(avatar)
                .into(user_image)
        }
    }


    private fun initItems(){

    }
}
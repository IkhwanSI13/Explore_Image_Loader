package ikhwankoto.com.explore_image_loader

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_glide.setOnClickListener {
            startActivity(Intent(this, GlideActivity::class.java))
        }

        btn_picasso.setOnClickListener {
            startActivity(Intent(this, PicassoActivity::class.java))
        }
    }
}

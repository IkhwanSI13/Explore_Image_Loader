package ikhwankoto.com.explore_image_loader

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ikhwankoto.com.explore_image_loader.Coil.CoilActivity
import ikhwankoto.com.explore_image_loader.Fresco.FrescoActivity
import ikhwankoto.com.explore_image_loader.Glide.GlideActivity
import ikhwankoto.com.explore_image_loader.Picasso.PicassoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_glide.setOnClickListener(this)
        btn_picasso.setOnClickListener(this)
        btn_fresco.setOnClickListener(this)
        btn_coil.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.id?.let {
            when (it) {
                R.id.btn_glide ->
                    startActivity(Intent(this, GlideActivity::class.java))
                R.id.btn_picasso ->
                    startActivity(Intent(this, PicassoActivity::class.java))
                R.id.btn_fresco ->
                    startActivity(Intent(this, FrescoActivity::class.java))
                R.id.btn_coil ->
                    startActivity(Intent(this, CoilActivity::class.java))
            }
        }
    }
}

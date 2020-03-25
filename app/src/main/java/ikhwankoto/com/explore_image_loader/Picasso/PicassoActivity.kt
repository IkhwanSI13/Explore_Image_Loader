package ikhwankoto.com.explore_image_loader.Picasso

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ikhwankoto.com.explore_image_loader.R
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    /**
     * https://square.github.io/picasso/
     * todo: Custom transformation
     */

    var urlImage =
        "https://media.gettyimages.com/photos/landscape-of-a-buddha-statue-at-borobudur-in-java-indonesia-picture-id124490579?s=2048x2048"
    var urlImageForFailure =
        "https://blabla.bla.bla/bla.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)
        Log.e("CheckLog", "onCreat PicassoActivity")

        title = "Picasso"

        btn_standart.setOnClickListener {
            Picasso.get().load(urlImage).into(iv_standart)
        }

        /**
         * Center crop need resize method
         * */
        btn_transformation.setOnClickListener {
            Picasso.get()
                .load(urlImage)
                .resize(50, 50)
                .centerCrop()
                .into(iv_transformation)
        }

        btn_centercrop_withgravity.setOnClickListener {
            Picasso.get()
                .load(urlImage)
                .resize(50, 50)
                .centerCrop(Gravity.RIGHT)
                .into(iv_centercrop_withgravity)
        }

        btn_resize.setOnClickListener {
            Picasso.get()
                .load(urlImage)
                .resize(50, 50)
                .into(iv_resize)
        }

        btn_rotate.setOnClickListener {
            Picasso.get()
                .load(urlImage)
                .rotate(90f)
                .into(iv_rotate)
        }

        btn_placeholder.setOnClickListener {
            Picasso.get()
                .load(urlImage)
                .placeholder(R.drawable.ic_loading)
                .into(iv_placeholder)
        }

        btn_error.setOnClickListener {
            Picasso.get()
                .load(urlImageForFailure)
                .placeholder(R.drawable.ic_error)
                .into(iv_error)
        }

    }
}

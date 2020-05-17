package ikhwankoto.com.explore_image_loader.Glide

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
import ikhwankoto.com.explore_image_loader.R
import kotlinx.android.synthetic.main.activity_glide.*

class GlideActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * https://bumptech.github.io/glide/
     * todo: Component Options
     * todo: Custom transformations
     * todo: Custom configuration https://bumptech.github.io/glide/doc/configuration.html
     * todo: Custom Cache Invalidation https://bumptech.github.io/glide/doc/caching.html
     */

    var urlImageForFailure =
        "https://blabla.bla.bla/bla.png"
    var urlImage =
        "https://media.gettyimages.com/photos/landscape-of-a-buddha-statue-at-borobudur-in-java-indonesia-picture-id124490579?s=2048x2048"
    var urlImageThumbnail =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQv5jtHhGCNRRh5GTdFQDWjUAih3bdozBJegE8p6UIbJxOo37d4"
    var urlImageError =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR9AkzpnzXNes1ZsaJzqwtasSGywSfTBHacISlcArgTI-ao-t-T"
    var urlGif = "https://media.giphy.com/media/Tia2InBEWaQgckP3UG/giphy.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        title = "Glide"

        btn_standart.setOnClickListener(this)
        btn_standart_clear.setOnClickListener(this)
        btn_placeholder.setOnClickListener(this)
        btn_request_option.setOnClickListener(this)
        btn_gif.setOnClickListener(this)
        btn_placeholder_color.setOnClickListener(this)
        btn_error.setOnClickListener(this)
        btn_fallback.setOnClickListener(this)
        btn_request_builder.setOnClickListener(this)
        btn_thumbnail.setOnClickListener(this)
        btn_thumbnail_different_image.setOnClickListener(this)
        btn_newrequest_onfailure.setOnClickListener(this)
        btn_centercrop.setOnClickListener(this)
        btn_fitcenter.setOnClickListener(this)
        btn_circlecrop.setOnClickListener(this)
        btn_specifictarget.setOnClickListener(this)
        btn_specifictarget_gif.setOnClickListener(this)
        btn_transition.setOnClickListener(this)
        btn_cache.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.id?.let {
            when (view.id) {
                R.id.btn_standart -> {
                    /**
                     * Simple Call
                     * */
                    Glide.with(this)
                        .load(urlImage)
                        .into(iv_standart)
                }
                R.id.btn_standart_clear -> {
                    /**
                     * Clear
                     * */
                    Glide.with(this).clear(iv_standart)
                    /**
                     * Although it’s good practice to clear loads you no longer need, you’re not required
                     * to do so. In fact, Glide will automatically clear the load and recycle any resources
                     * used by the load when the Activity or Fragment you pass in to Glide.with() is destroyed.
                     * */
                }
                R.id.btn_placeholder -> {
                    /**
                     * With place holder and fit center
                     * */
                    Glide.with(this)
                        .load(urlImage)
                        .placeholder(R.drawable.ic_loading)
                        .fitCenter()
                        .into(iv_placeholder)
                }
                R.id.btn_request_option -> {
                    /**
                     * With request option
                     * */
                    val sharedOptions = RequestOptions()
                        .placeholder(R.drawable.ic_loading)
                        .fitCenter()

                    Glide.with(this)
                        .load(urlImage)
                        .apply(sharedOptions)
                        .into(iv_request_option)

                    Glide.with(this)
                        .load(urlImage)
                        .apply(sharedOptions)
                        .into(iv_request_option2)
                }
                R.id.btn_gif -> {
                    /**
                     * GIF
                     * */
                    Glide.with(this)
                        .asGif()
                        .load(urlGif)
                        .into(iv_gif)
                }
                R.id.btn_placeholder_color -> {
                    /**
                     * Placeholder
                     * */
                    Glide.with(this)
                        .load(urlImage)
                        //.placeholder(R.mipmap.ic_launcher_round)
                        .placeholder(ColorDrawable(Color.BLACK))
                        .into(iv_placeholder_color)
                }
                R.id.btn_error -> {
                    /**
                     * Error
                     * are shown when a request permanently fails. Error Drawables are also shown
                     * if the requested url/model is null and no fallback Drawable is set
                     * */
                    Glide.with(this)
                        .load("https://blla.bla.bla/bla.pjg")
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .into(iv_error)
                }
                R.id.btn_fallback -> {
                    /**
                     * Fallback
                     * If null url
                     * */
                    var urlImageNull: String? = null
                    Glide.with(this)
                        .load(urlImageNull)
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .fallback(R.drawable.ic_camera)
                        .into(iv_fallback)
                }
                R.id.btn_request_builder -> {
                    /**
                     * RequestBuilder
                     * reusable
                     * */
                    val requestBuilder: RequestBuilder<Bitmap> = Glide.with(this).asBitmap()
                    requestBuilder.load(urlImage)
                        .into(iv_request_builder)
                }
                R.id.btn_thumbnail -> {
                    /**
                     * Thumbnail with same url image, but low percentage
                     * */
                    Glide.with(this)
                        .load(urlImage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .thumbnail(0.25f)
                        .into(iv_thumbnail)
                }
                R.id.btn_thumbnail_different_image -> {
                    /**
                     * Thumbnail with different image
                     * */
                    Glide.with(this)
                        .load(urlImage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .thumbnail(
                            Glide.with(this)
                                .load(urlImageThumbnail)
                            //.override(thumbnailSize))
                        )
                        .into(iv_thumbnail_different_image)
                }
                R.id.btn_newrequest_onfailure -> {
                    /**
                     * New request when on failure
                     * */
                    Glide.with(this)
                        .load(urlImageForFailure)
                        .placeholder(R.drawable.ic_loading)
                        .error(
                            Glide.with(this)
                                .load(urlImageError)
                        )
                        .into(iv_newrequest_onfailure)
                }
                R.id.btn_centercrop -> {
                    Glide.with(this).load(urlImage).centerCrop().into(iv_centercrop)
                }
                R.id.btn_fitcenter -> {
                    Glide.with(this).load(urlImage).fitCenter().into(iv_fitcenter)
                }
                R.id.btn_circlecrop -> {
                    Glide.with(this).load(urlImage).circleCrop().into(iv_circlecrop)
                }
                R.id.btn_specifictarget -> {
                    /**
                     * Use Target, but to manny override method
                     * So use custom target
                     * */
                    //var target = object : CustomTarget<Drawable>(12,20) {
                    // 12 width, 20 height
                    val target = object : CustomTarget<Drawable>() {
                        override fun onLoadCleared(placeholder: Drawable?) {}

                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            iv_specifictarget.setImageDrawable(resource)
                        }
                    }
                    Glide.with(this).load(urlImage).into(target)

                    /**You can also use the returned Target to clear() out any pending loads and
                     * release any associated resources without starting a new load
                     * */
                    //Glide.with(this).clear(target)
                }
                R.id.btn_specifictarget_gif -> {
                    /**
                     * Start.() to run the gif
                     * */
                    val target = object : CustomTarget<Drawable>() {
                        override fun onLoadCleared(placeholder: Drawable?) {}

                        override fun onResourceReady(
                            resource: Drawable,
                            transition: Transition<in Drawable>?
                        ) {
                            if (resource is Animatable) resource.start()
                            iv_specifictarget_gif.setImageDrawable(resource)
                        }
                    }
                    Glide.with(this).load(urlGif).into(target)
                }
                R.id.btn_transition -> {
                    /**
                     * Transition
                     * performance tips: don't use transition on glide inside recyclerview
                     *
                     * factory to avoid issue with transparent image
                     * */
                    val factory =
                        DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

                    Glide.with(this)
                        .asBitmap()
                        .load(urlImage)
                        .placeholder(R.drawable.ic_loading)
                        .transition(withCrossFade(factory))
                        .into(iv_transition)
                }
                R.id.btn_cache -> {
                    /**
                     * Get image without save it in cache
                     * */
                    Glide.with(this)
                        .load(urlImage)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(iv_cache)
                }
                else -> {
                }
            }
        }


    }

}

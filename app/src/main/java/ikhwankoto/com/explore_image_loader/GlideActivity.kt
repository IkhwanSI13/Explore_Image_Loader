package ikhwankoto.com.explore_image_loader

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Animatable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.activity_glide.*

class GlideActivity : AppCompatActivity() {

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

        /**
         * Simple Call
         * */
        btn_standart.setOnClickListener {
            Glide.with(this)
                .load(urlImage)
                .into(iv_standart)
        }

        /**
         * Clear
         * */
        btn_standart_clear.setOnClickListener {
            Glide.with(this).clear(iv_standart)
            /**
             * Although it’s good practice to clear loads you no longer need, you’re not required
             * to do so. In fact, Glide will automatically clear the load and recycle any resources
             * used by the load when the Activity or Fragment you pass in to Glide.with() is destroyed.
             * */
        }

        /**
         * With place holder and fit center
         * */
        btn_placeholder.setOnClickListener {
            Glide.with(this)
                .load(urlImage)
                .placeholder(R.drawable.ic_loading)
                .fitCenter()
                .into(iv_placeholder)
        }

        /**
         * With request option
         * */
        btn_request_option.setOnClickListener {
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

        /**
         * GIF
         * */
        btn_gif.setOnClickListener {
            Glide.with(this)
                .asGif()
                .load(urlGif)
                .into(iv_gif)
        }

        /**
         * Placeholder
         * */
        btn_placeholder_color.setOnClickListener {
            Glide.with(this)
                .load(urlImage)
                //.placeholder(R.mipmap.ic_launcher_round)
                .placeholder(ColorDrawable(Color.BLACK))
                .into(iv_placeholder_color)
        }

        /**
         * Error
         * are shown when a request permanently fails. Error Drawables are also shown
         * if the requested url/model is null and no fallback Drawable is set
         * */
        btn_error.setOnClickListener {
            Glide.with(this)
                .load("https://blla.bla.bla/bla.pjg")
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .into(iv_error)
        }

        /**
         * Fallback
         * If null url
         * */
        btn_fallback.setOnClickListener {
            var urlImageNull: String? = null
            Glide.with(this)
                .load(urlImageNull)
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_error)
                .fallback(R.drawable.ic_camera)
                .into(iv_fallback)
        }

        /**
         * RequestBuilder
         * reusable
         * */
        btn_request_builder.setOnClickListener {
            val requestBuilder: RequestBuilder<Bitmap> = Glide.with(this).asBitmap()
            requestBuilder.load(urlImage)
                .into(iv_request_builder)
        }

        /**
         * Thumbnail with same url image, but low percentage
         * */
        btn_thumbnail.setOnClickListener {
            //urlImage
            Glide.with(this)
                .load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .placeholder(R.drawable.ic_loading)
                .thumbnail(0.25f)
                .into(iv_thumbnail)
        }

        /**
         * Thumbnail with different image
         * */
        btn_thumbnail_different_image.setOnClickListener {
            //urlImage
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

        /**
         * New request when on failure
         * */
        btn_newrequest_onfailure.setOnClickListener {
            Glide.with(this)
                .load(urlImageForFailure)
                .placeholder(R.drawable.ic_loading)
                .error(
                    Glide.with(this)
                        .load(urlImageError)
                )
                .into(iv_newrequest_onfailure)
        }

        btn_centercrop.setOnClickListener {
            Glide.with(this).load(urlImage).centerCrop().into(iv_centercrop)
        }

        btn_fitcenter.setOnClickListener {
            Glide.with(this).load(urlImage).fitCenter().into(iv_fitcenter)
        }

        btn_circlecrop.setOnClickListener {
            Glide.with(this).load(urlImage).circleCrop().into(iv_circlecrop)
        }

        /**
         * Use Target, but to manny override method
         * So use custom target
         * */
        btn_specifictarget.setOnClickListener {
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

        /**
         * Start.() to run the gif
         * */
        btn_specifictarget_gif.setOnClickListener {
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


        /**
         * Transition
         * performance tips: don't use transition on glide inside recyclerview
         *
         * factory to avoid issue with transparent image
         * */
        btn_transition.setOnClickListener {
            val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

            Glide.with(this)
                .asBitmap()
                .load(urlImage)
                .placeholder(R.drawable.ic_loading)
                .transition(withCrossFade(factory))
                .into(iv_transition)
        }

        /**
         * Get image without save it in cache
         * */
        btn_cache.setOnClickListener {
            Glide.with(this)
                .load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(iv_cache)
        }

    }

}

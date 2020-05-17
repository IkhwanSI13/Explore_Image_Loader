package ikhwankoto.com.explore_image_loader.Coil

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.api.load
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.extension.videoFrameMillis
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import coil.request.CachePolicy
import coil.request.GetRequest
import coil.request.LoadRequest
import coil.size.ViewSizeResolver
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import coil.transition.CrossfadeTransition
import ikhwankoto.com.explore_image_loader.R
import kotlinx.android.synthetic.main.activity_coil.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.File

/**
 * How to use?
 * 1. ImageView.load("url")
 * 2. ImageLoader with LoadRequest or GetRequest
 *      ImageLoader: ImageLoaders are service objects that execute Requests.
 *      LoadRequest: A request that is scoped to a Lifecycle and supports Targets, Transitions, and more.
 *      GetRequest: A request that suspends and returns a RequestResult.
 *    Data types that are supported by all IMageLoader:
 *      String, HttpUrl, Uri, File, @DrawableRes Int, Drawable, Bitmap
 *
 * - Coil's API is designed to be Kotlin-first.
 * - Use Java 8
 * - Coil performs best when you create a single ImageLoader and share it throughout your app.
 *   This is because each ImageLoader has its own memory cache and bitmap pool.
 *   Single ImageLoader can use from application class or DI (Like dagger)
 *
 * todo
 * - imageLoader.shutdown() is optional - though still recommended.
 * - PoolableViewTarget
 * - Custom transition
 * */

class CoilActivity : AppCompatActivity(), View.OnClickListener {

    var imageUrl =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Jam_Gadang_in_2011.jpg/1200px-Jam_Gadang_in_2011.jpg";
    var imageUrl2 =
        "https://cdn1-production-images-kly.akamaized.net/FDQTRxFNjaVYZMDQafKtsz5NspE=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/1439539/original/094814500_1482128249-095165600_1454230277-1.jpg";
    var imageUrl3 =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTL934SKpV7-JLS6jgKycpjJMqRT0AnQRqDvFbxdOUrwwJ24ABI&usqp=CAU";
    var urlGif = "https://media.giphy.com/media/Tia2InBEWaQgckP3UG/giphy.gif"
    var urlSvg = "http://ikhwankoto.com/_image/ic_yukngoding.svg"

    var fileVideo: File? = null

    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil)

        // Initiation dengan coil yang berasal dari App
        // Jika imageLoader di shutdown, coil tidak akan bisa digunakan kembali
        // imageLoader = Coil.imageLoader(this)

        // Initiation dengan Imageloader yang dikhususkan untuk kelas ini
        // Jadi jika imageLoader di shutdown, coil tetap bisa digunakan di activity yang lainnya
        imageLoader = ImageLoader.Builder(this).build()

        btn_standart.setOnClickListener(this)
        btn_imageloader_loadrequest.setOnClickListener(this)
        btn_imageloader_getrequest.setOnClickListener(this)
        btn_preload_memory.setOnClickListener(this)
        btn_preload_memory2.setOnClickListener(this)
        btn_preload_cache.setOnClickListener(this)
        btn_target.setOnClickListener(this)
        btn_target_custom.setOnClickListener(this)
        btn_transformation.setOnClickListener(this)
        btn_transition.setOnClickListener(this)
        btn_mapper.setOnClickListener(this)
        btn_gif.setOnClickListener(this)
        btn_svg.setOnClickListener(this)
        btn_videoframe.setOnClickListener(this)
        btn_palette.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.id?.let {
            when (it) {
                R.id.btn_standart -> {
                    iv_standart.load(imageUrl)
                    /**
                     * Cancelling request?
                     * val disposable = imageView.load("https://www.example.com/image.jpg")
                     * disposable.dispose()
                     * */
                }
                R.id.btn_imageloader_loadrequest -> {
                    val request = LoadRequest.Builder(this)
                        .data(imageUrl)
                        .target(iv_imageloader_loadrequest)
                        .build()
                    imageLoader.execute(request)
                }
                R.id.btn_imageloader_getrequest -> {
                    val request = GetRequest.Builder(this)
                        .data(imageUrl)
                        .build()

                    GlobalScope.async {
                        val result = imageLoader.execute(request)
                        result.drawable?.let { image ->
                            iv_imageloader_getrequest.setImageDrawable(image)
                        }
                    }
                }
                R.id.btn_preload_memory -> {
                    Log.e("Ikhwan", "run preload memory")
                    iv_preload_memory.load(imageUrl2)
                }
                R.id.btn_preload_memory2 -> {
                    Log.e("Ikhwan", "run preload memory2")
                    /**
                     * To preload an image into memory, execute a LoadRequest without a Target:
                     */
                    //TODO
                    val request = LoadRequest.Builder(this)
                        .data(imageUrl2)
                        // Optional, but setting a ViewSizeResolver will conserve memory by limiting the size the image should be preloaded into memory at.
                        .size(ViewSizeResolver(iv_preload_memory))
                        .build()
                    //imageLoader.execute(request)
                }
                R.id.btn_preload_cache -> {
                    Log.e("Ikhwan", "run preload cache")
                    /**
                     * To preload a network image only into the disk cache, disable the memory cache for the request:
                     * */
                    val request = LoadRequest.Builder(this)
                        .data(imageUrl3)
                        .memoryCachePolicy(CachePolicy.DISABLED)
                        .target(iv_preload_cache)
                        .build()
                    imageLoader.execute(request)
                }
                R.id.btn_target -> {
                    iv_target.load(imageUrl) {
                        placeholder(R.drawable.ic_loading)
                        error(R.drawable.ic_error)
                    }
                }
                R.id.btn_target_custom -> {
                    val request = LoadRequest.Builder(this@CoilActivity)
                        .data(imageUrl)
                        .placeholder(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)
                        .target(
                            onStart = { placeholder ->
                                Log.e("Ikhwan", "PLaceholder run")
                                placeholder?.let {
                                    Log.e("Ikhwan", "PLaceholder tidak kosong")
                                    iv_target_custom.setImageDrawable(placeholder)
                                }
                            },
                            onSuccess = { result ->
                                Log.e("Ikhwan", "Result run")
                                iv_target_custom.setImageDrawable(result)
                            },
                            onError = { error ->
                                Log.e("Ikhwan", "PLaceholder error")
                                error?.let {
                                    Log.e("Ikhwan", "PLaceholder tidak kosong")
                                    iv_target_custom.setImageDrawable(error)
                                }
                            }
                        )
                        .build()
                    imageLoader.execute(request)
                }
                R.id.btn_transformation -> {
                    iv_blur.load(imageUrl) {
                        placeholder(R.drawable.ic_loading)
                        transformations(BlurTransformation(this@CoilActivity))
                    }
                    iv_circlecrop.load(imageUrl) {
                        placeholder(R.drawable.ic_loading)
                        transformations(CircleCropTransformation())
                    }
                    iv_grayscale.load(imageUrl) {
                        placeholder(R.drawable.ic_loading)
                        transformations(GrayscaleTransformation())
                    }
                    iv_roundedcorner.load(imageUrl) {
                        placeholder(R.drawable.ic_loading)
                        transformations(RoundedCornersTransformation(24F))
                        //apply to specific corner
                        //transformations(RoundedCornersTransformation(topLeft = 12F,bottomLeft = 12F))
                    }
                }
                R.id.btn_transition -> {
                    iv_transition.load(imageUrl) {
                        placeholder(R.drawable.ic_loading)
                        transition(CrossfadeTransition(5000))
                    }
                }
                R.id.btn_mapper -> {
                    // iniate imageloader with mapper
                    val imageLoaderMapper = ImageLoader.Builder(this@CoilActivity)
                        .componentRegistry {
                            add(SchoolMapper())
                        }
                        .build()

                    val school = School(
                        1,
                        "https://cdn2.tstatic.net/tribunnews/foto/bank/images/universitas-andalas.jpg",
                        "Universitas Andalas"
                    )
                    val request = LoadRequest.Builder(this@CoilActivity)
                        .data(school)
                        .target(iv_mapper)
                        .build()
                    imageLoaderMapper.execute(request)
                }
                R.id.btn_gif -> {
                    // iniate imageloader with GIF
                    val imageLoaderGif = ImageLoader.Builder(this@CoilActivity)
                        .componentRegistry {
                            if (SDK_INT >= 28) {
                                add(ImageDecoderDecoder())
                            } else {
                                add(GifDecoder())
                            }
                        }
                        .build()
                    val request = LoadRequest.Builder(this@CoilActivity)
                        .data(urlGif)
                        .target(iv_gif)
                        .build()
                    imageLoaderGif.execute(request)
                }
                R.id.btn_svg -> {
                    // iniate imageloader with SVG
                    val imageLoaderSvg = ImageLoader.Builder(this@CoilActivity)
                        .componentRegistry {
                            add(SvgDecoder(this@CoilActivity))
                        }
                        .build()
                    val request = LoadRequest.Builder(this@CoilActivity)
                        .data(urlSvg)
                        .target(iv_svg)
                        .build()
                    imageLoaderSvg.execute(request)
                }
                R.id.btn_videoframe -> {
                    /**
                     * Video frame decoding is only supported for Files and Uris
                     * (content and file schemes only).
                     * */
                    getVideoFromExternalStorage()
                    /**
                     * If init coil on app use code shown below
                     * gif and svg too
                     * */
//                    iv_videoframe.load(urlVideo){
//                        videoFrameMillis(300)
//                    }
                }
                R.id.btn_palette->{
                    //todo Palette class not found
//                    val request = LoadRequest.Builder(this@CoilActivity)
//                        .data("https://www.example.com/image.jpg")
//                        .allowHardware(false) // Disable hardware bitmaps.
//                        .target { drawable ->
////                            val bitmap = (result.drawable as BitmapDrawable).bitmap
//
//                            // Generate the Palette on a background thread.
//                            val task = Palette.Builder(bitmap).generateAsync { palette ->
//                                // Consume the palette.
//                            }
//                        }
//                        .build()
//                    val disposable = imageLoader.execute(request)
                }
                else -> {

                }
            }
        }

        /**
         * With header
         * */
        //val request = LoadRequest.Builder(context)
        //    .data("https://www.example.com/image.jpg")
        //    .setHeader("Cache-Control", "max-age=31536000,public")
        //    .target(imageView)
        //    .build()
        //imageLoader.execute(request)
    }

    fun getVideoFromExternalStorage() {
        val intent = Intent()
        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Video"),
            10
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 10) {
            data?.let {
                it.data?.let { data ->
                    data.path?.let { path ->
                        Log.e("Ikhwan", "execute with coil")
                        //todo not work
                        fileVideo = File(path)
                        val imageLoaderVideoFrame = ImageLoader.Builder(this@CoilActivity)
                            .componentRegistry {
                                add(VideoFrameFileFetcher(this@CoilActivity))
                                add(VideoFrameUriFetcher(this@CoilActivity))
                            }
                            .build()
                        val request = LoadRequest.Builder(this@CoilActivity)
                            .data(fileVideo)
                            .videoFrameMillis(1000)
                            .target(iv_videoframe)
                            .build()
                        imageLoaderVideoFrame.execute(request)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        imageLoader.shutdown()
        super.onDestroy()
    }

}

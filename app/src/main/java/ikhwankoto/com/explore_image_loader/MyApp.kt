package ikhwankoto.com.explore_image_loader

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import com.facebook.drawee.backends.pipeline.Fresco

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

}

/**
 * Singleton in Application Class
 */
//class MyApp : Application(), ImageLoaderFactory {
//    override fun onCreate() {
//        super.onCreate()
//        Fresco.initialize(this)
//    }
//
//    override fun newImageLoader(): ImageLoader =
//        ImageLoader.Builder(this)
//            .build()
//}

/**
 * Singleton set via method from everywhere
 */
//    val imageLoader = ImageLoader.Builder(this)
//        .availableMemoryPercentage(0.25)
//        .crossfade(true)
//        .placeholder(R.drawable.ic_camera)
//        .build()
//    Coil.setImageLoader(imageLoader)
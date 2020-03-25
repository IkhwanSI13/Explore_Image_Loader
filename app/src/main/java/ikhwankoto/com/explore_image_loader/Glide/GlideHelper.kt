package ikhwankoto.com.explore_image_loader.Glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class GlideHelper {

    //Call this method from Background Threads
    fun loadBitmapOnBackgroundThread(
        context: Context,
        url: String,
        width: Int,
        height: Int
    ): Bitmap {
        val futureTarget = Glide.with(context)
            .asBitmap()
            .load(url)
            .submit(width, height)

        val bitmap = futureTarget.get()

        //todo
        // Do something with the Bitmap and then when you're done with it:
        // Glide.with(context).clear(futureTarget)

        return bitmap
    }

    //Foreground Threads
//    fun loadBitmapOnForegroundThread(context: Context, url: String, width: Int, height: Int): Bitmap {
//        Glide.with(context)
//          .asBitmap()
//          .load(url)
//          .into(object:Target<Bitmap> {})
//    }

    fun loadGlideForRecyclerView() {
        //Sometimes when using RecyclerView, a View may be re-used and
        // retain the size from a previous position that will be changed for
        // the current position. To handle those cases, you can create a
        // new [ViewTarget and pass in true for waitForLayout]:

        //Glide.with(fragment)
        //  .load(urls.get(position))
        //.into(DrawableImageViewTarget(holder.imageView, true))
    }

    /**
     * This method must be called on the main thread.
     * Clearing all memory isn’t particularly efficient and should be
     * avoided whenever possible to avoid jank and increased loading
     * times.
     * */
    fun clearGlideCache(context: Context) {
        Glide.get(context).clearMemory()
    }

}
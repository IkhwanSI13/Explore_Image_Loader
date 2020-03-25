package ikhwankoto.com.explore_image_loader.Fresco

import android.graphics.PointF
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.drawable.ProgressBarDrawable
import com.facebook.drawee.drawable.ScalingUtils
import com.facebook.imagepipeline.common.RotationOptions
import com.facebook.imagepipeline.request.ImageRequestBuilder
import ikhwankoto.com.explore_image_loader.R
import kotlinx.android.synthetic.main.activity_fresco.*

class FrescoActivity : AppCompatActivity() {

    /**
     * https://frescolib.org/docs/index.html
     * todo: advanced topic
     * todo: image formats
     */

    /**
     * Supported URIs
     * network  :   http://     &       https://
     * device   :   file://
     * content provider     :   content://
     * asset    :   asset://
     * res      :   res://      &       res:///12345
     * data     :   data:mime/type;base64,
     * */

    var urlImageForFailure =
        "https://blabla.bla.bla/bla.png"
    var urlImage =
        "https://media.gettyimages.com/photos/landscape-of-a-buddha-statue-at-borobudur-in-java-indonesia-picture-id124490579?s=2048x2048"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)

        title = "Fresco"

        /**
         * The placeholder is shown until the image is ready. The image will be downloaded,
         * cached, displayed, and cleared from memory when your view goes off-screen.
         * */
        btn_standart.setOnClickListener {
            iv_standart.setImageURI(urlImage)
        }

        /**
         * SimpleDraweeView does not support wrap_content for layout_width or layout_height
         * attributes. More information can be found here. The only exception to this is
         * when you are setting an aspect ratio, like so:
         * */
        btn_aspecratio.setOnClickListener {
            iv_aspecratio.setImageURI(urlImage)
        }

        btn_placeholder.setOnClickListener {
            iv_placeholder.setImageURI(urlImage)
        }

        btn_error.setOnClickListener {
            iv_error.setImageURI(urlImageForFailure)
        }

        /**
         * Only images that resolve to BitmapDrawable or ColorDrawable can be rounded.
         * Rounding NinePatchDrawable, ShapeDrawable and other such drawables is not
         * supported (regardless whether they are specified in XML or programmatically).
         *
         * Animations are not rounded.
         *
         * Make image full to the size to get rounded effect, use center crop etc
         * */
        btn_rounded_border.setOnClickListener {
            iv_rounded_border.setImageURI(urlImage)
            iv_rounded_border2.setImageURI(urlImage)
        }

        btn_progress.setOnClickListener {
            //iv_progress

            var progrssDrawable = ProgressBarDrawable()
            progrssDrawable.color = resources.getColor(R.color.colorBlue)
            progrssDrawable.backgroundColor = resources.getColor(R.color.colorAccent)
            progrssDrawable.radius = 12


            iv_progress.hierarchy.setProgressBarImage(progrssDrawable)
            iv_progress.hierarchy.setFailureImage(
                resources.getDrawable(R.drawable.ic_error),
                ScalingUtils.ScaleType.CENTER_INSIDE
            )

            iv_progress.setImageURI(urlImage)
        }

        btn_scaletype.setOnClickListener {
            iv_scaletype1.setImageURI(urlImage)
            iv_scaletype2.setImageURI(urlImage)
            iv_scaletype3.setImageURI(urlImage)
            iv_scaletype4.setImageURI(urlImage)
            iv_scaletype5.setImageURI(urlImage)
            iv_scaletype6.setImageURI(urlImage)
            iv_scaletype7.setImageURI(urlImage)

            val focusPoint = PointF(20f, 10f)
            iv_scaletype8.hierarchy.setActualImageFocusPoint(focusPoint)
            iv_scaletype8.setImageURI(urlImage)
        }

        btn_rotate_auto.setOnClickListener {
            /**
             * JPEG files sometimes store orientation information in the image metadata.
             * If you want images to be automatically rotated to match the device’s orientation,
             * you can do so in the image request:
             * */
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(urlImage))
                .setRotationOptions(RotationOptions.autoRotate())
                .build()
            iv_rotate_auto.setImageRequest(imageRequest)
        }

        btn_rotate_90.setOnClickListener {
            val imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(urlImage))
                .setRotationOptions(RotationOptions.forceRotation(RotationOptions.ROTATE_90))
                .build()
            iv_rotate_90.setImageRequest(imageRequest)
        }

    }

}

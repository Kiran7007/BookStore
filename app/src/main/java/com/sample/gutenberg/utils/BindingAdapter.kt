package com.sample.gutenberg.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("app:bookImage")
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    imageView.setImageBitmap(ImageHelper.getRoundedCornerBitmap(resource, 16))
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }

    @JvmStatic
    @BindingAdapter("app:src")
    fun imageResource(imageView: ImageView, resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    @JvmStatic
    @BindingAdapter("app:fontFamily")
    fun textFontFamily(textView: TextView, fontFamily: String) {
        textView.typeface = CustomFontFamily.getFont(textView.context, fontFamily);
    }
}
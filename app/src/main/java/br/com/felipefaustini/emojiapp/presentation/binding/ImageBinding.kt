package br.com.felipefaustini.emojiapp.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import br.com.felipefaustini.domain.models.Emoji
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageBinding {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String) {
        if (url.isNotEmpty()) {
            Glide.with(imageView)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, emojiLiveData: LiveData<Emoji>) {
        if (emojiLiveData.value != null) {
            Glide.with(imageView)
                .load(emojiLiveData.value!!.url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }

}
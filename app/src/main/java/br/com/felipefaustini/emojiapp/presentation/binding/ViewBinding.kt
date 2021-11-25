package br.com.felipefaustini.emojiapp.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import br.com.felipefaustini.emojiapp.utils.extensions.showOrGoneInCondition
import br.com.felipefaustini.emojiapp.utils.extensions.showOrInvisibleInCondition

object ViewBinding {

    @JvmStatic
    @BindingAdapter("app:visibility")
    fun changeVisibility(view: View, condition: LiveData<Boolean>) {
        if (condition.value != null) {
            view.showOrGoneInCondition(condition.value!!)
        }
    }

    @JvmStatic
    @BindingAdapter("app:invisibility")
    fun changeInvisiblity(view: View, condition: LiveData<Boolean>) {
        if (condition.value != null) {
            view.showOrInvisibleInCondition(!condition.value!!)
        }
    }

}
package br.com.felipefaustini.emojiapp.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.showOrGoneInCondition(condition: Boolean) {
    if (condition) makeVisible()
    else makeGone()
}

fun View.showOrInvisibleInCondition(condition: Boolean) {
    if (condition) makeVisible()
    else makeInvisible()
}

fun ViewGroup.inflate(@LayoutRes viewId: Int, attatchToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(viewId, this, attatchToRoot)
}

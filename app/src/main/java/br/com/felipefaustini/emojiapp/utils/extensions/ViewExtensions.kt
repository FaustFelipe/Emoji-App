package br.com.felipefaustini.emojiapp.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

fun View.hideKeyboard() {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

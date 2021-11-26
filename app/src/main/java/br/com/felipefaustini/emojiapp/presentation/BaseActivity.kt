package br.com.felipefaustini.emojiapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<A: ViewDataBinding, V: BaseViewModel>(
    @LayoutRes private val layoutRes: Int
): AppCompatActivity() {

    protected var binding: A? = null

    protected open val viewModel: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding?.lifecycleOwner = this

        setupViews()
        setupObservables()
    }

    protected abstract fun setupViews()

    protected open fun setupObservables() {
        viewModel?.errorLiveData?.observe(this) {
            showSnackbar(text = it)
        }
    }

    protected open fun showSnackbar(
        view: View? = binding?.root,
        text: String,
        length: Int = Snackbar.LENGTH_SHORT
    ) {
        view?.let {
            Snackbar.make(it, text, length).show()
        }
    }

    protected fun showToast(text: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, length).show()
    }

}
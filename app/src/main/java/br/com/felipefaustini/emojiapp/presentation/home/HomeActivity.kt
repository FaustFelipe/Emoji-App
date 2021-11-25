package br.com.felipefaustini.emojiapp.presentation.home

import android.view.View
import androidx.core.widget.doOnTextChanged
import br.com.felipefaustini.emojiapp.R
import br.com.felipefaustini.emojiapp.databinding.ActivityHomeBinding
import br.com.felipefaustini.emojiapp.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModel()
    
    override fun setupViews() {
        binding?.view = this
        binding?.viewModel = viewModel

        binding?.edtSearchUserField?.apply {
            doOnTextChanged { _, _, _, _ -> binding?.edtSearchUser?.error = null }
        }
    }

    override fun setupObservables() {
        viewModel.errorUnitLiveData.observe(this) {
            binding?.edtSearchUser?.error = getString(R.string.main_invalid_username)
        }

        viewModel.userSavedLiveData.observe(this) {
            showToast(getString(R.string.main_success_searching_for_user))
        }
    }

    fun onClickSearchUser(view: View) {
        viewModel.getUser()
    }

}
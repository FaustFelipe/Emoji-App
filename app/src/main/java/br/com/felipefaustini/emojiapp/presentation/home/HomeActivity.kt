package br.com.felipefaustini.emojiapp.presentation.home

import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import br.com.felipefaustini.emojiapp.R
import br.com.felipefaustini.emojiapp.databinding.ActivityHomeBinding
import br.com.felipefaustini.emojiapp.presentation.BaseActivity
import br.com.felipefaustini.emojiapp.presentation.emojilist.EmojiListActivity
import br.com.felipefaustini.emojiapp.presentation.googlereposlist.GoogleReposListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModels()
    
    override fun setupViews() {
        binding?.view = this
        binding?.viewModel = viewModel

        binding?.edtSearchUserField?.apply {
            doOnTextChanged { _, _, _, _ -> binding?.edtSearchUser?.error = null }
        }
    }

    override fun setupObservables() {
        super.setupObservables()

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

    fun onClickGetEmoji(view: View) {
        viewModel.getEmoji()
    }

    fun goToEmojiList(view: View) {
        EmojiListActivity.open(this)
    }

    fun goToAvatarList(view: View) {

    }

    fun goToReposList(view: View) {
        GoogleReposListActivity.open(this)
    }

}
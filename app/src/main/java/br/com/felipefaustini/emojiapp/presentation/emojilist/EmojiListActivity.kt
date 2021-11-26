package br.com.felipefaustini.emojiapp.presentation.emojilist

import android.app.Activity
import android.content.Intent
import br.com.felipefaustini.emojiapp.R
import br.com.felipefaustini.emojiapp.databinding.ActivityEmojiListBinding
import br.com.felipefaustini.emojiapp.presentation.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmojiListActivity: BaseActivity<ActivityEmojiListBinding, EmojiListViewModel>(
    R.layout.activity_emoji_list
) {

    override val viewModel: EmojiListViewModel by viewModel()

    private val adapter: EmojiListAdapter = EmojiListAdapter()

    override fun setupViews() {
        binding?.viewModel = viewModel

        setupRecycler()

        setupSwipeRefresh()
    }

    override fun setupObservables() {
        super.setupObservables()

        viewModel.emojisLiveData.observe(this) {
            adapter.setData(it)
        }
    }

    private fun setupRecycler() {
        binding?.recyclerEmoji?.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding?.swipeRefresh?.isRefreshing = false
        binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.loadEmojis()
            binding?.swipeRefresh?.isRefreshing = false
        }
    }

    companion object {
        fun open(activity: Activity?) {
            activity?.let { act ->
                val intent = Intent(act, EmojiListActivity::class.java)
                act.startActivity(intent)
            }
        }
    }

}
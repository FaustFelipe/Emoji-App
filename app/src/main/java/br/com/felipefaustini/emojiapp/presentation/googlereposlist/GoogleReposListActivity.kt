package br.com.felipefaustini.emojiapp.presentation.googlereposlist

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.felipefaustini.emojiapp.R
import br.com.felipefaustini.emojiapp.databinding.ActivityGoogleReposBinding
import br.com.felipefaustini.emojiapp.presentation.BaseActivity
import br.com.felipefaustini.emojiapp.presentation.PaginationListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleReposListActivity: BaseActivity<ActivityGoogleReposBinding, GoogleReposListViewModel>(
    R.layout.activity_google_repos
) {

    override val viewModel: GoogleReposListViewModel by viewModels()

    private val adapter: GoogleReposAdapter = GoogleReposAdapter()

    override fun setupViews() {
        setupRecycler()
    }

    override fun setupObservables() {
        super.setupObservables()

        viewModel.reposLiveData.observe(this) {
            adapter.setData(it)
        }
    }

    private fun setupRecycler() {
        binding?.recyclerRepos?.adapter = adapter
        
        val layoutManager = LinearLayoutManager(this)
        
        binding?.recyclerRepos?.layoutManager = layoutManager

        binding?.recyclerRepos?.addOnScrollListener(object : PaginationListener(layoutManager) {
            override val pageSize: Int = GoogleReposListViewModel.PAGE_SIZE

            override fun loadMoreItems() {
                viewModel.loadRepos()
            }

            override fun isLastPage(): Boolean = viewModel.isLastPage

            override fun isLoading(): Boolean = viewModel.isLoading
        })
    }

    companion object {
        fun open(activity: Activity?) {
            activity?.let { act ->
                val intent = Intent(act, GoogleReposListActivity::class.java)
                act.startActivity(intent)
            }
        }
    }

}
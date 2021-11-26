package br.com.felipefaustini.emojiapp.presentation.googlereposlist

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.Repos
import br.com.felipefaustini.emojiapp.R
import br.com.felipefaustini.emojiapp.databinding.ItemRepoBinding
import br.com.felipefaustini.emojiapp.utils.extensions.inflate

class GoogleReposAdapter: RecyclerView.Adapter<GoogleReposAdapter.GoogleReposViewHolder>() {

    private val reposList = arrayListOf<Repos>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleReposViewHolder {
        return GoogleReposViewHolder(parent.inflate(R.layout.item_repo))
    }

    override fun onBindViewHolder(holder: GoogleReposViewHolder, position: Int) {
        val item = reposList[position]
        holder.binding?.repo = item
        holder.binding?.executePendingBindings()
    }

    fun setData(list: List<Repos>) {
        reposList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = reposList.size

    inner class GoogleReposViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<ItemRepoBinding>(itemView)
    }

}
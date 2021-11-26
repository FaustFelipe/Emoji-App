package br.com.felipefaustini.emojiapp.presentation.emojilist

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.Emoji
import br.com.felipefaustini.emojiapp.R
import br.com.felipefaustini.emojiapp.databinding.ItemEmojiBinding
import br.com.felipefaustini.emojiapp.utils.extensions.inflate

class EmojiListAdapter: ListAdapter<Emoji, EmojiListAdapter.EmojiViewHolder>(
    EmojiListDiffUtils()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        return EmojiViewHolder(parent.inflate(R.layout.item_emoji))
    }

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding?.emoji = item
        holder.binding?.root?.setOnClickListener {
            val newList = ArrayList(currentList)
            newList.remove(item)
            setData(newList)
        }
        holder.binding?.executePendingBindings()
    }

    fun setData(list: List<Emoji>) {
        this.submitList(list.toMutableList())
    }

    inner class EmojiViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<ItemEmojiBinding>(itemView)
    }

    class EmojiListDiffUtils: DiffUtil.ItemCallback<Emoji>() {
        override fun areItemsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
            return oldItem == newItem
        }
    }

}
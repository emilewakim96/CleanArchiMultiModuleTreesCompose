package com.example.cleanarchimultimoduletreescompose.presentation.treeslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchimultimoduletreescompose.R
import com.example.cleanarchimultimoduletreescompose.databinding.TreeItemBinding
import com.example.domain.models.Tree

class TreeListAdapter(private val itemList: MutableList<Tree> = mutableListOf(),
                      val listener: OnItemClickListener
) : RecyclerView.Adapter<TreeListAdapter.TreeListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeListViewHolder {
        val binding = DataBindingUtil.inflate<TreeItemBinding>(LayoutInflater.from(parent.context),
            R.layout.layout_tree_item, parent, false)
        return TreeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TreeListViewHolder, position: Int) {
        return holder.bind(itemList[position], listener)
    }

    class TreeListViewHolder(private val binding: TreeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tree: Tree?, clickListener: OnItemClickListener) {
            binding.tree = tree
            binding.treeItemClick = clickListener
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = itemList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateTreeList(list: List<Tree>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onTreeItemClick(tree: Tree)
    }
}
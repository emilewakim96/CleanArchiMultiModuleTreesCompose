package com.example.cleanarchimultimoduletreescompose.presentation.treeslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchimultimoduletreescompose.MainActivity
import com.example.cleanarchimultimoduletreescompose.R
import com.example.cleanarchimultimoduletreescompose.databinding.FragmentTreeListBinding
import com.example.cleanarchimultimoduletreescompose.presentation.treedetail.TreeDetailFragment
import com.example.cleanarchimultimoduletreescompose.ui.base.BaseFragment
import com.example.domain.models.Tree

class TreeListFragment: BaseFragment(), TreeListAdapter.OnItemClickListener {

    companion object {
        val tag: String = TreeListFragment::class.java.simpleName
    }

    private var treeListAdapter: TreeListAdapter? = null
    private lateinit var binding: FragmentTreeListBinding
    private val viewModel by viewModels<TreeListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tree_list, container, false
        )

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = viewModel.uiState

        //When using livedata/flows inside binding implementation, we should specify the lifecycle owner
        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.clickMeButton = this

        //Set adapter, divider and default animator to the recycler view
        treeListAdapter = TreeListAdapter(listener = this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(),
            LinearLayoutManager.VERTICAL
        )

        with(binding.treesRecyclerView) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = treeListAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectLatestLifecycleFlow(viewModel.treesList) {
            updateTreeList(it)
        }
    }

    fun onClickMeButtonClick() {
        println("EMILE")
    }

    private fun updateTreeList(list: List<Tree>) {
        treeListAdapter?.updateTreeList(list)
    }

    override fun onTreeItemClick(tree: Tree) {
        (activity as MainActivity).loadFragment(TreeDetailFragment.tag, isAddToBackStack = true, params = listOf(tree))
    }
}
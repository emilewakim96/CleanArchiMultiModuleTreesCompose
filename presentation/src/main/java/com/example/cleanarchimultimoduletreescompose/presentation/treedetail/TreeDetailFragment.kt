package com.example.cleanarchimultimoduletreescompose.presentation.treedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.cleanarchimultimoduletreescompose.R
import com.example.cleanarchimultimoduletreescompose.databinding.FragmentTreeDetailsBinding
import com.example.cleanarchimultimoduletreescompose.ui.base.BaseFragment
import com.example.domain.models.Tree
import com.google.gson.Gson

class TreeDetailFragment: BaseFragment() {

    companion object {
        const val TREE = "TREE"
    }

    private lateinit var binding: FragmentTreeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_tree_details, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tree = arguments?.getParcelable(TREE) as? Tree

        tree?.let {
            binding.tree = it
        }
    }
}
package com.example.cleanarchimultimoduletreescompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cleanarchimultimoduletreescompose.presentation.treedetail.TreeDetailFragment
import com.example.cleanarchimultimoduletreescompose.presentation.treedetail.TreeDetailFragment.Companion.TREE
import com.example.cleanarchimultimoduletreescompose.presentation.treeslist.TreeListFragment
import com.example.domain.models.Tree
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(TreeListFragment.tag)
    }

    fun loadFragment(tag: String, isAddToBackStack: Boolean = false, params: List<Any> = listOf()) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container_layout, getFragmentByTag(tag, params))
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    private fun getFragmentByTag(tag: String, params: List<Any>): Fragment {
        return when (tag) {
            TreeListFragment.tag -> TreeListFragment()
            TreeDetailFragment.tag -> {
                if (params.size == 1 && params[0] is Tree) {
                    val args = Bundle()
                    args.putSerializable(TREE, Gson().toJson(params[0]))
                    TreeDetailFragment.newInstance(args)
                } else {
                    TreeDetailFragment()
                }
            }
            else -> TreeListFragment()
        }
    }
}
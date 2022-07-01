package com.example.testegit.features.main.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.testegit.R
import com.example.testegit.core.ui.BaseFragment
import com.example.testegit.databinding.FragmentGitListBinding
import com.example.testegit.features.main.ui.adapter.GitListAdapter
import com.example.testegit.features.main.viewModel.MainViewModel
import com.example.testegit.features.main.viewState.MainViewState
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GitListFragment : BaseFragment<FragmentGitListBinding>() {

    private val viewModel: MainViewModel by sharedViewModel()
    private var adapter: GitListAdapter? = null

    override fun layoutId() = R.layout.fragment_git_list

    override fun create() {
        viewModel.getGitList()
    }

    override fun init() {
        setObservers()
        render()
    }

    private fun setObservers() {

        viewModel.gitList.observe(viewLifecycleOwner) {
            showLoading(false)
            adapter?.setList(ArrayList(it))
        }

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is MainViewState.ShowLoading -> showLoading(it.show)
                is MainViewState.Error -> {}
            }
        }
    }

    private fun render() {

        adapter = GitListAdapter {}

        binding?.apply {

            listGitAdapter.addOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                        viewModel.getGitList()
                    }
                }
            })
            listGitAdapter.adapter = adapter
        }

    }
}
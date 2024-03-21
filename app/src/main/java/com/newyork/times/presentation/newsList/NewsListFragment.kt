package com.newyork.times.presentation.newsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.newyork.times.R
import com.newyork.times.databinding.FragmentMostPopularNewsBinding
import com.newyork.times.utils.ResponseStatus
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private val viewModel: NewsListViewModel by activityViewModels()

    private var _binding: FragmentMostPopularNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostPopularNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchNews()
        viewModel.newsList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                newsListAdapter.updateList(it)
            }
        }

        viewModel.responseStatus.observe(viewLifecycleOwner) {
            with(binding) {
                when (it) {
                    is ResponseStatus.Loading -> {
                        progressBarLayout.visibility = View.VISIBLE
                        mostPopularRecyclerView.visibility = View.GONE
                        errorLayout.visibility = View.GONE
                    }
                    is ResponseStatus.Success -> {
                        progressBarLayout.visibility = View.GONE
                        mostPopularRecyclerView.visibility = View.VISIBLE
                        errorLayout.visibility = View.GONE
                    }
                    is ResponseStatus.Error -> {
                        progressBarLayout.visibility = View.GONE
                        mostPopularRecyclerView.visibility = View.GONE
                        errorLayout.visibility = View.VISIBLE
                        errorMessage.text = it.message
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        newsListAdapter = NewsListAdapter()
        binding.mostPopularRecyclerView.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }

        newsListAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("newsEntity", it)
            }
            findNavController().navigate(
                R.id.action_mostPopularNewsFragment_to_newsDetailFragment,
                bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
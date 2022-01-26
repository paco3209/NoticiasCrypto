package com.example.noticiascrypto.ui.Fragments.MainNews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.noticiascrypto.R
import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.data.remote.NewsDataSource
import com.example.noticiascrypto.databinding.FragmentMainNewsBinding
import com.example.noticiascrypto.presentation.NewsViewModel
import com.example.noticiascrypto.presentation.NewsViewModelFactory
import com.example.noticiascrypto.repository.NewsRepositoryImpl
import com.example.noticiascrypto.repository.RetrofitInstance
import com.example.noticiascrypto.utils.Resource


class MainNewsFragment : Fragment(R.layout.fragment_main_news), NewsAdapter.OnNewsClickListener {

    private lateinit var newsadapter: NewsAdapter
    private lateinit var binding: FragmentMainNewsBinding
    private val viewModel by viewModels<NewsViewModel> {
        NewsViewModelFactory(
            NewsRepositoryImpl(
                NewsDataSource(RetrofitInstance.api)
            )
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsadapter = NewsAdapter(requireContext(),this)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainNewsBinding.bind(view)

        val verticalDecorator = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        val horizontalDecorator = DividerItemDecoration(view.context, DividerItemDecoration.HORIZONTAL)

        val drawable = ContextCompat.getDrawable(view.context, R.drawable.divider_shape)
        verticalDecorator.setDrawable(drawable!!)
        horizontalDecorator.setDrawable(drawable!!)

        binding.rvLatestNews.apply {
            addItemDecoration(verticalDecorator)
            addItemDecoration(horizontalDecorator)
            layoutManager = LinearLayoutManager(requireContext())
            adapter =  newsadapter

        }



        viewModel.fetchLatestNews().observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    binding.paginationProgressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.paginationProgressBar.visibility = View.GONE
                    binding.rvLatestNews.visibility = View.VISIBLE
                    it.data?.let { newsResponse ->
                        newsadapter.setNewsList(newsResponse.data)
                    }


                }
                is Resource.Failure -> {
                    binding.paginationProgressBar.visibility = View.GONE
                    Log.d("Error", "${it.exception}")
                }
            }
        })


    }



    override fun onNewsClick(data: Data, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("data",data)
        findNavController().navigate(R.id.action_mainNewsFragment_to_detailNewsFragment,bundle)
    }


}
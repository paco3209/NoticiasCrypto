package com.example.noticiascrypto.ui.Fragments.DetailsNews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.noticiascrypto.R
import com.example.noticiascrypto.data.model.Data
import com.example.noticiascrypto.data.remote.NewsDataSource
import com.example.noticiascrypto.databinding.FragmentDetailNewsBinding
import com.example.noticiascrypto.presentation.NewsViewModel
import com.example.noticiascrypto.presentation.NewsViewModelFactory
import com.example.noticiascrypto.repository.NewsRepositoryImpl
import com.example.noticiascrypto.repository.RetrofitInstance


class DetailNewsFragment : Fragment(R.layout.fragment_detail_news) {

    private lateinit var binding: FragmentDetailNewsBinding
    val args: DetailNewsFragmentArgs by navArgs()
    private lateinit var data:Data

    private val viewModel by viewModels<NewsViewModel> {
        NewsViewModelFactory(
            NewsRepositoryImpl(
                NewsDataSource(RetrofitInstance.api)
            )
        )
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding =  FragmentDetailNewsBinding.bind(view)

        val notice = args.data

        binding.cocktailTitle.text = notice.title
        binding.cocktailDesc.text = notice.content
        Glide.with(requireContext())
                .load(notice.imageUrl)
                .centerCrop()
                .into(binding.imgCocktail)


        binding.btnSaveOrDeleteCocktail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(notice.readMoreUrl)
            startActivity(intent)
        }

    }
}
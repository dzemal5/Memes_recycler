package com.crnkic.memes.ui.memesdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.crnkic.memes.R
import com.crnkic.memes.databinding.FragmentMemeDetailsBinding

class MemeDetailsFragment : Fragment(R.layout.fragment_meme_details) {

    private var _binding: FragmentMemeDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: MemeDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataForFragmentDetails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMemeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun getDataForFragmentDetails() {
        val meme = args.Memes
        context?.let {
            Glide.with(it).load(meme.url).into(binding.imageViewDetails)
        }
        binding.height.text = meme.height.toString()
        binding.width.text = meme.width.toString()
        binding.id.text = meme.id
        binding.name.text = meme.name
        binding.boxCount.text = meme.boxCount.toString()
    }
}
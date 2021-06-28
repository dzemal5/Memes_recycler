package com.crnkic.memes.ui.memeslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crnkic.memes.R
import com.crnkic.memes.data.model.Data
import com.crnkic.memes.data.model.Memes
import com.crnkic.memes.data.model.MemesContainer
import com.crnkic.memes.data.model.MemesContainerResult
import com.crnkic.memes.databinding.FragmentMemeListBinding
import com.crnkic.memes.ui.adapters.MemesListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemesListFragment : Fragment(R.layout.fragment_meme_list) {

    private var _binding: FragmentMemeListBinding? = null
    private val binding get() = _binding!!

    val memesViewModel: MemesListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        memesViewModel.memesContainerResultLiveData.observe(viewLifecycleOwner, Observer {memesContainerResult ->

            memesContainerResult?.let {
                when (it) {
                    is MemesContainerResult.Success -> {
                        getMemesRecyclerList()
                        Log.d("RECYCLER", "success")
                    }
                    is MemesContainerResult.Failure -> {
                        Toast.makeText(context, "failed to fetch the data", Toast.LENGTH_SHORT).show()
                        Log.d("RECYCLER", "failure")
                    }
                    is MemesContainerResult.IsLoading -> {
                        Toast.makeText(context, "data is loading", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMemeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getMemesRecyclerList() {
        val memesListAdapter = MemesListAdapter()

        binding.recyclerViewInListFragment.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = memesListAdapter

        }

        memesListAdapter.setOnItemClickListener {
            val directions =
                MemesListFragmentDirections.actionMemesListFragmentToMemeDetailsFragment(it)
            findNavController().navigate(directions)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
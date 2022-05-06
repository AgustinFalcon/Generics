package com.example.generics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.generics.databinding.FragmentHomeBinding
import com.example.generics.retrofit.ui.MainViewModel
import com.example.generics.retrofit.utils.ApiResponse
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collect


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        getPosts()

        return binding.root
    }

    private fun getPosts() {
        lifecycleScope.launchWhenCreated {
            mainViewModel.data.collect {
                when (it) {
                    is ApiResponse.Success -> {
                        Log.d(TAG, "getPosts: ${it.data}")
                    }
                    is ApiResponse.Failure -> {
                        Log.d(TAG, "Failure: ${it.msg}")
                    }
                    ApiResponse.Loading -> {
                        Log.d(TAG, "Loading: $it")
                    }
                    is ApiResponse.Error -> {
                        Log.d(TAG, "Error: ${it.e}")
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        private const val TAG = "HomeFragment"
    }
}
package com.meddemo3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meddemo3.adapters.ActualsAdapter
import com.meddemo3.common.MyInfo
import com.meddemo3.data.Story
import com.meddemo3.databinding.FragmentHomeBinding
import com.meddemo3.utils.MyLogger

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    lateinit var viewModel:HomeViewModel
    lateinit var recyclerViewActuals:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MyLogger.d("HomeFragment - onCreateView")
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        viewModel.actualsResponse.observe(viewLifecycleOwner) {
            setActualsAdapter(it.stories)
        }

        recyclerViewActuals = binding.homeActualsRecycler
        return root
    }

    private fun setActualsAdapter(stories: List<Story>) {
        MyLogger.d("HomeFragment - setActualsAdapter")
        recyclerViewActuals?.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        val actualsAdapter = ActualsAdapter(requireContext(), stories)
        recyclerViewActuals.adapter = actualsAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        MyLogger.d("HomeFragment - onResume")
        viewModel.showActuals()
    }
}
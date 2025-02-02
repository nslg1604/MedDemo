package com.meddemo3.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.meddemo3.R
import com.meddemo3.databinding.FragmentDoctorsBinding
import com.meddemo3.tools.MyProgress
import com.meddemo3.utils.MyLogger
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DoctorsFragment : Fragment() {
    @Inject lateinit var myProgress:MyProgress
    lateinit var viewModel:DoctorsViewModel
    private var _binding: FragmentDoctorsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(DoctorsViewModel::class.java)

        _binding = FragmentDoctorsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        viewModel.progressStarted.observe(viewLifecycleOwner) {
            MyLogger.d("DoctorsFragment - progressStarted=" + it)
            if (!it){
                myProgress.cancelAlert()
                viewModel.addDoctorsToDatabaseAsync()
            }
        }

        viewModel.progressStr.observe(viewLifecycleOwner) {
            MyLogger.d("DoctorsFragment - progressStr=" + it)

        }

        viewModel.doctorsResponse.observe(viewLifecycleOwner) {
            MyLogger.d("DoctorsFragment - doctors.size=" + it.doctors.size)
//            setActualsAdapter(it.doctors)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        myProgress.showProgressRound(requireActivity(),
            resources.getString(R.string.loading),
            5, 5)
        viewModel.showDoctors()
    }
}
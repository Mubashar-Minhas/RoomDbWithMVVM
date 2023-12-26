package com.example.roomdbdemoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbdemoapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StudentViewModel by viewModels {
        val application = requireActivity().application as MyApp
        val repository = application.repository
        StudentViewModelFactory(repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.buttonSubmit.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val age = binding.editTextClass.text.toString().toIntOrNull() ?: 0

            if (name.isNotEmpty() && email.isNotEmpty()) {
                val newStudent = Student(name = name, email = email, age = age)
                viewModel.insert(newStudent)
            } else {
                // Handle empty input fields
            }
            Toast.makeText(requireContext(), "saved", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

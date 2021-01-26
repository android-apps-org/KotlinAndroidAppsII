package com.jdemaagd.gdgfinder.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.material.snackbar.Snackbar

import com.jdemaagd.gdgfinder.R
import com.jdemaagd.gdgfinder.databinding.AddGdgFragmentBinding

class AddGdgFragment : Fragment() {

    private val viewModel: AddGdgViewModel by lazy {
        ViewModelProvider(this).get(AddGdgViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = AddGdgFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.application_submitted),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()

                binding.button.text = getText(R.string.done)
                binding.button.contentDescription = getText(R.string.submitted)
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }
}

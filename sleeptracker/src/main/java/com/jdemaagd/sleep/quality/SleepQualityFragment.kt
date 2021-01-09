package com.jdemaagd.sleep.quality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.jdemaagd.sleep.R
import com.jdemaagd.sleep.database.SleepDatabase
import com.jdemaagd.sleep.databinding.FragmentSleepQualityBinding

class SleepQualityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_quality, container, false)

        val application = requireNotNull(this.activity).application

        // Note: get safeArgs passed from bundle passed in navigation
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)

        // Note: create ViewModel associated with this Fragment
        val sleepQualityViewModel = ViewModelProvider(
                this, viewModelFactory).get(SleepQualityViewModel::class.java)

        // Note: explicitly bind ViewModel
        binding.sleepQualityViewModel = sleepQualityViewModel

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped
        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, { tapped ->
            if (tapped == true) {
                this.findNavController().navigate(
                        SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                sleepQualityViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}

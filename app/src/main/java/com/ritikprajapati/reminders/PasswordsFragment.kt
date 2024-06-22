package com.ritikprajapati.reminders

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ritikprajapati.reminders.databinding.DialogEditReminderBinding
import com.ritikprajapati.reminders.databinding.FragmentPasswordsBinding

class PasswordsFragment : Fragment() {

    private lateinit var binding : FragmentPasswordsBinding
    private val preferences by lazy { requireActivity().getSharedPreferences("password", Context.MODE_PRIVATE)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPasswordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()
        binding.cardViewWifi.setOnClickListener { showEditDialog("pref_wifi") }
        binding.cardViewTabletPin.setOnClickListener { showEditDialog("pref_tablet_pin") }
        binding.cardViewBikeLock.setOnClickListener { showEditDialog("pref_bike_lock") }
    }

    private fun showEditDialog( preferenceKey : String) {
        val dialogBinding = DialogEditReminderBinding.inflate(requireActivity().layoutInflater)
        //below is a one line to that when you click on it, it will show the previous enttered data in the entry box
        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey, null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update Value")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") {_, _ ->
                preferences.edit { putString(preferenceKey, dialogBinding.editTextValue.text?.toString()) }
                displayValues()
            }
            .setNegativeButton("Cancel") { _, _ ->

            }.show()
    }

    private fun displayValues() {
        binding.textViewWifiValue.text = preferences.getString("pref_wifi", null)
        binding.textViewTabletPinValue.text = preferences.getString("pref_tablet_pin", null)
        binding.textViewBikeLockValue.text = preferences.getString("pref_bike_lock", null)
    }

}
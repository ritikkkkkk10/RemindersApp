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
import com.ritikprajapati.reminders.databinding.FragmentGeneralInfoBinding
import com.ritikprajapati.reminders.databinding.FragmentPasswordsBinding

class GeneralInfoFragment : Fragment() {

    private lateinit var binding : FragmentGeneralInfoBinding
    private val preferences by lazy { requireActivity().getSharedPreferences("generalInfo", Context.MODE_PRIVATE)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGeneralInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayValues()
        binding.cardViewThought.setOnClickListener { showEditDialog("pref_thought") }
        binding.cardViewAccountPin.setOnClickListener { showEditDialog("pref_account_pin") }
        binding.cardViewEmail.setOnClickListener { showEditDialog("pref_email") }
    }

    private fun showEditDialog( preferenceKey : String) {
        val dialogBinding = DialogEditReminderBinding.inflate(requireActivity().layoutInflater)
        //below is a one line to that when you click on it, it will show the previous entered data in the entry box
        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey, null))
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update Valuee")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") {_, _ ->
                preferences.edit { putString(preferenceKey, dialogBinding.editTextValue.text?.toString()) }
                displayValues()
            }
            .setNegativeButton("Cancel") { _, _ ->

            }.show()
    }

    private fun displayValues() {
        binding.textViewThoughtValue.text = preferences.getString("pref_thought", null)
        binding.textViewAccountPinValue.text = preferences.getString("pref_account_pin", null)
        binding.textViewEmailValue.text = preferences.getString("pref_email", null)
    }

}
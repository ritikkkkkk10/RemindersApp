package com.ritikprajapati.reminders

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ritikprajapati.reminders.databinding.ActivityMainBinding
import com.ritikprajapati.reminders.databinding.FragmentGeneralInfoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ScreenSlidePagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when(position) {
                0 -> tab.text = "Passwords"
                1 -> tab.text = "General Info"
            }
        }.attach()
    }


    //Below is the PROCESS to use the feature of SLIDING b/w FRAGMENTS
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2  //2 Is the number of Fragments

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> PasswordsFragment()
                else -> GeneralInfoFragment()
            }
        }

    }

}
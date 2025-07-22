package com.example.freshblooms

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.freshblooms.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.bind(inflater.inflate(R.layout.fragment_profile, container, false))

        binding.logoutButton.setOnClickListener{
            val intent = Intent(context,LoginActivity::class.java)
            context?.startActivity(intent)
            activity?.finishAffinity()
        }

        binding.name.text = PreferenceHelper(requireActivity()).getUserName()
        binding.phone.text = PreferenceHelper(requireActivity()).getUserPhone()
        binding.email.text = PreferenceHelper(requireActivity()).getUserEmail()

        return binding.root
    }


}
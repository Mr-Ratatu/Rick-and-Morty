package com.rick.and.morti.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.rick.and.morti.common.base.BaseFragment
import com.rick.and.morti.databinding.FragmentDetailCharacterBinding

class DetailCharacterFragment : BaseFragment<FragmentDetailCharacterBinding>() {

    private val args by navArgs<DetailCharacterFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.result = args.result

    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailCharacterBinding.inflate(inflater, container, false)

}
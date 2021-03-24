package com.rick.and.morti.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.rick.and.morti.common.base.BaseFragment
import com.rick.and.morti.common.utils.States
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.databinding.FragmentListCharacterBinding
import com.rick.and.morti.extension.gone
import com.rick.and.morti.extension.visible
import com.rick.and.morti.ui.adapters.CharacterListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharacterFragment : BaseFragment<FragmentListCharacterBinding>(),
    CharacterListAdapter.CharacterListener {

    private var listAdapter: CharacterListAdapter? = null
    private val lisViewModel by viewModel<ListCharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = CharacterListAdapter(this)

        binding.adapter = listAdapter

        observeData()
    }

    private fun observeData() {
        with(lisViewModel) {
            listCharacter.observe(viewLifecycleOwner) {
                setStateView(it.state)
                it.data?.characterResult?.let { item -> listAdapter?.setData(item) }
            }
        }
    }

    private fun setStateView(state: States) {
        with(binding) {
            when (state) {
                States.SUCCESS -> {
                    recyclerView.visible()
                    loading.gone()
                    wifiOff.gone()
                }
                States.LOADING -> {
                    recyclerView.gone()
                    loading.visible()
                    wifiOff.gone()
                }
                States.ERROR -> {
                    recyclerView.gone()
                    loading.gone()
                    wifiOff.visible()
                }
            }
        }
    }

    override fun onClickCharacter(result: CharacterResult) {
        view?.findNavController()?.navigate(
            ListCharacterFragmentDirections.actionListCharacterFragmentToDetailCharacterFragment(
                result
            )
        )
    }

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentListCharacterBinding.inflate(inflater, container, false)

    override fun onDestroyView() {
        super.onDestroyView()
        listAdapter = null
    }

}
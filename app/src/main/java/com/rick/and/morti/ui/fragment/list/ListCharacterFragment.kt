package com.rick.and.morti.ui.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.ExperimentalPagingApi
import com.rick.and.morti.common.base.BaseFragment
import com.rick.and.morti.common.utils.States
import com.rick.and.morti.data.model.CharacterResult
import com.rick.and.morti.databinding.FragmentListCharacterBinding
import com.rick.and.morti.extension.gone
import com.rick.and.morti.extension.visible
import com.rick.and.morti.ui.adapters.CharacterListAdapter
import com.rick.and.morti.ui.adapters.CharacterListState
import kotlinx.android.synthetic.main.item_state.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCharacterFragment : BaseFragment<FragmentListCharacterBinding>(),
    CharacterListAdapter.CharacterListener {

    private var listAdapter: CharacterListAdapter? = null
    private val lisViewModel by viewModel<ListCharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = CharacterListAdapter(this)

        binding.recyclerView.adapter = listAdapter?.withLoadStateHeaderAndFooter(
            header = CharacterListState { listAdapter?.retry() },
            footer = CharacterListState { listAdapter?.retry() }
        )

        observeData()
    }

    private fun observeData() {
        with(lisViewModel) {
            viewState.feed.onEach { pagedList ->
                listAdapter?.submitData(pagedList)
            }.launchIn(lifecycleScope)
        }
    }

    override fun onClickCharacter(result: CharacterResult?) {
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
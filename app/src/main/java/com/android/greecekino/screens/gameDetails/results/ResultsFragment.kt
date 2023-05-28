package com.android.greecekino.screens.gameDetails.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.greecekino.DATE_TIME_EXTRA
import com.android.greecekino.GAME_ID_EXTRA
import com.android.greecekino.contracts.gameDetails.results.ResultsUiEvent
import com.android.greecekino.contracts.gameDetails.results.ResultsUiState
import com.android.greecekino.databinding.FragmentPreviousResultsBinding
import com.android.greecekino.ui.getFormattedTime
import com.android.model.local.result.Result
import org.koin.android.ext.android.inject

class ResultsFragment : Fragment() {

    private val resultsViewModel: ResultsViewModel by inject()
    private var binding: FragmentPreviousResultsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreviousResultsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        getResults()
    }

    private fun setListeners() {
        lifecycleScope.launchWhenCreated {
            resultsViewModel.state.collect { state ->
                when (state) {
                    is ResultsUiState.Loading -> binding?.progressGroup?.visibility = View.VISIBLE
                    is ResultsUiState.Idle -> setUpViews()
                    is ResultsUiState.ShowPreviousResults -> showPreviousResults(state.results)
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            resultsViewModel.effect.collect { effect ->
            }
        }
    }

    private fun getResults() {
        val gameId = arguments?.getInt(GAME_ID_EXTRA, 0)
        val dateTime = arguments?.getLong(DATE_TIME_EXTRA) ?: 0
        val formattedDate = getFormattedTime(dateTime, "yyyy-MM-dd")
        resultsViewModel.setEvent(
            ResultsUiEvent.GetPreviousResults(
                gameId = gameId.toString(),
                fromDate = formattedDate,
                toDate = formattedDate
            )
        )
    }

    private fun setUpViews() {
        binding?.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.setHasFixedSize(true)
        }
    }

    private fun showPreviousResults(results: List<Result>) {
        binding?.apply {
            progressGroup.visibility = View.GONE
            recyclerView.adapter = ResultsRecyclerAdapter(results)
        }
    }
}
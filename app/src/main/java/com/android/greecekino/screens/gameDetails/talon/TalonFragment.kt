package com.android.greecekino.screens.gameDetails.talon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.android.greecekino.DRAW_ID_EXTRA
import com.android.greecekino.GAME_ID_EXTRA
import com.android.greecekino.R
import com.android.greecekino.contracts.gameDetails.talon.TalonUiEvent
import com.android.greecekino.contracts.gameDetails.talon.TalonUiState
import com.android.greecekino.databinding.FragmentTalonBinding
import com.android.greecekino.ui.getFormattedTime
import com.android.model.local.upcoming.UpcomingGameDraw
import org.koin.android.ext.android.inject

class TalonFragment : Fragment(), TalonRecyclerAdapter.OnListChangeListener {

    private val talonViewModel: TalonViewModel by inject()
    private var binding: FragmentTalonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTalonBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        getDrawDetails()
    }

    private fun getDrawDetails() {
        val drawId = arguments?.getInt(DRAW_ID_EXTRA)
        val gameId = arguments?.getInt(GAME_ID_EXTRA)
        talonViewModel.setEvent(
            TalonUiEvent.GetDrawGameDetails(
                gameId = gameId.toString(),
                drawId = drawId.toString()
            )
        )
    }

    private fun setListeners() {
        lifecycleScope.launchWhenCreated {
            talonViewModel.state.collect { state ->
                when (state) {
                    is TalonUiState.Loading -> binding?.progressGroup?.visibility = View.VISIBLE
                    is TalonUiState.ShowTalon -> showGameDetails(state.upcomingGameDraw)
                    is TalonUiState.ShowSelectedNumbersCount -> updateSelectedNumbersCount(state.numbersCount)
                    is TalonUiState.Idle -> setUpViews()
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            talonViewModel.effect.collect { effect ->
            }
        }
    }

    private fun showGameDetails(upcomingGameDraw: UpcomingGameDraw) {
        binding?.apply {
            progressGroup.visibility = View.GONE
            textTime.text = getString(
                R.string.game_details_placeholder,
                getFormattedTime(upcomingGameDraw.drawTime, "hh:mm"),
                upcomingGameDraw.visualDraw.toString()
            )
        }
    }

    private fun setUpViews() {
        binding?.apply {
            context?.let {
                recyclerView.layoutManager = GridLayoutManager(it, 5)
                recyclerView.setHasFixedSize(true)
                recyclerView.adapter =
                    TalonRecyclerAdapter(
                        it.resources.getIntArray(R.array.numbers).toTypedArray(),
                        this@TalonFragment
                    )
            }
            textNumbersCount.text = context?.getString(R.string.remaining_numbers_to_select, 8, 0)
        }
    }

    private fun updateSelectedNumbersCount(numbersCount: Int) {
        binding?.textNumbersCount?.text =
            context?.getString(
                R.string.remaining_numbers_to_select,
                numbersCount,
                8 - numbersCount
            )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onNumbersSelected(numbersCount: Int) {
        talonViewModel.setEvent(TalonUiEvent.UpdateNumbersCount(numbersCount))
    }

    //
    override fun onNumbersLimitReached() {
        Toast.makeText(
            context,
            getString(R.string.max_number_you_can_choose),
            Toast.LENGTH_SHORT
        ).show()
    }
}
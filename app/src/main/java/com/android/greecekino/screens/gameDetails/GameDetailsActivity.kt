package com.android.greecekino.screens.gameDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.greecekino.DATE_TIME_EXTRA
import com.android.greecekino.DRAW_ID_EXTRA
import com.android.greecekino.GAME_ID_EXTRA
import com.android.greecekino.R
import com.android.greecekino.contracts.gameDetails.GameDetailsUiEvent
import com.android.greecekino.contracts.gameDetails.GameDetailsUiState
import com.android.greecekino.databinding.ActivityGameDetailsBinding
import com.android.greecekino.screens.gameDetails.live.LiveDrawingFragment
import com.android.greecekino.screens.gameDetails.results.ResultsFragment
import com.android.greecekino.screens.gameDetails.talon.TalonFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class GameDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailsBinding
    private val gameDetailsViewModel: GameDetailsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        gameDetailsViewModel.setEvent(GameDetailsUiEvent.PopulateViewPager)
    }

    private fun setListeners() {
        lifecycleScope.launchWhenCreated {
            gameDetailsViewModel.state.collect { state ->
                when (state) {
                    is GameDetailsUiState.Loading -> {}
                    is GameDetailsUiState.ShowScreens -> setupPager()
                    else -> {}
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            gameDetailsViewModel.effect.collect { effect ->
            }
        }
    }

    private fun setupPager() {
        val gameId = intent.getIntExtra(GAME_ID_EXTRA, 0)
        val drawId = intent.getIntExtra(DRAW_ID_EXTRA, 0)
        val date = intent.getLongExtra(DATE_TIME_EXTRA, 0)

        val pagerAdapter = ScreenSlidePagerAdapter(this, gameId, drawId, date)

        binding.apply {
            pager.isUserInputEnabled = false
            pager.adapter = pagerAdapter
            tabLayout.setTabTextColors(R.color.black, R.color.white)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.talon)
                    1 -> getString(R.string.live_drawings)
                    else -> getString(R.string.results)
                }
            }.attach()
        }
    }

    private inner class ScreenSlidePagerAdapter(
        fa: FragmentActivity,
        val gameId: Int,
        val drawId: Int,
        val date: Long
    ) :
        FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment = when (position) {
            0 -> {
                val talonFragment = TalonFragment()
                val args = Bundle()
                args.putInt(DRAW_ID_EXTRA, drawId)
                args.putInt(GAME_ID_EXTRA, gameId)
                talonFragment.arguments = args
                talonFragment
            }

            1 -> LiveDrawingFragment()
            else -> {
                val resultsFragment = ResultsFragment()
                val args = Bundle()
                args.putLong(DATE_TIME_EXTRA, date)
                args.putInt(GAME_ID_EXTRA, gameId)
                resultsFragment.arguments = args
                resultsFragment
            }
        }
    }
}

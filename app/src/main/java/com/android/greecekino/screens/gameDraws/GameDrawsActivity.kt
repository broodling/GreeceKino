package com.android.greecekino.screens.gameDraws

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.greecekino.DATE_TIME_EXTRA
import com.android.greecekino.DRAW_ID_EXTRA
import com.android.greecekino.GAME_ID_EXTRA
import com.android.greecekino.R
import com.android.greecekino.contracts.gameDraw.GameDrawsUiEffect
import com.android.greecekino.contracts.gameDraw.GameDrawsUiEvent
import com.android.greecekino.contracts.gameDraw.GameDrawsUiState
import com.android.greecekino.screens.gameDetails.GameDetailsActivity
import com.android.greecekino.ui.getFormattedTime
import com.android.model.local.upcoming.UpcomingGameDraw
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


class GameDrawsActivity : ComponentActivity() {

    private val gameDrawsViewModel: GameDrawsViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val state = gameDrawsViewModel.state.collectAsStateWithLifecycle()

            when (val stateValue = state.value) {
                is GameDrawsUiState.Loading -> ShowLoader()
                is GameDrawsUiState.ShowGameDraws -> GameDrawsList(draws = stateValue.draws) { gameDraw ->
                    gameDrawsViewModel.setEvent(GameDrawsUiEvent.OnGameDrawClick(gameDraw))
                }

                else -> {}
            }
            LaunchedEffect(key1 = Unit) {
                gameDrawsViewModel.setEvent(GameDrawsUiEvent.GetUpcomingGames)

                gameDrawsViewModel.effect.collect { effect ->
                    when (effect) {
                        is GameDrawsUiEffect.GoToGameDetails -> goToGameDetailsActivity(
                            upcomingDraw = effect.upcomingGameDraw,
                            context = context
                        )

                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
private fun GameDrawsList(draws: List<UpcomingGameDraw>, onDrawClick: (UpcomingGameDraw) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.time_of_draft),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
                color = Color.White
            )
            Text(
                text = stringResource(id = R.string.remaining_time),
                textAlign = TextAlign.End,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
                color = Color.White
            )
        }
        LazyColumn {
            itemsIndexed(draws) { index, draw ->
                DrawItem(upcomingGameDraw = draw, onDrawClick = onDrawClick)
                if (index != draws.size - 1) Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Composable
private fun DrawItem(
    upcomingGameDraw: UpcomingGameDraw, onDrawClick: (UpcomingGameDraw) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black)
        .padding(horizontal = 12.dp, vertical = 12.dp)
        .clickable {
            onDrawClick(upcomingGameDraw)
        }) {

        Text(
            text = getFormattedTime(upcomingGameDraw.drawTime, "hh:mm"),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = Color.White,
        )
        var remainingTime by remember {
            mutableStateOf("")
        }
        LaunchedEffect(key1 = Unit) {
            object : CountDownTimer(
                upcomingGameDraw.drawTime - System.currentTimeMillis(), 1000
            ) {
                override fun onTick(millisUntilFinished: Long) {
                    remainingTime = calculateRemainingTime(millisUntilFinished)
                }

                override fun onFinish() {
                }
            }.start()
        }
        Text(
            text = remainingTime,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
fun ShowLoader() {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

private fun calculateRemainingTime(millisUntilFinished: Long): String {
    var remainingTimeValue = millisUntilFinished
    val days: Long = TimeUnit.MILLISECONDS.toDays(remainingTimeValue)
    remainingTimeValue -= TimeUnit.DAYS.toMillis(days)
    val hours: Long = TimeUnit.MILLISECONDS.toHours(remainingTimeValue)
    remainingTimeValue -= TimeUnit.HOURS.toMillis(hours)
    val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(remainingTimeValue)
    remainingTimeValue -= TimeUnit.MINUTES.toMillis(minutes)
    return String.format("%02d : %02d", hours, minutes)
}

private fun goToGameDetailsActivity(
    upcomingDraw: UpcomingGameDraw,
    context: Context
) {
    context.startActivity(
        Intent(context, GameDetailsActivity::class.java)
            .putExtra(GAME_ID_EXTRA, upcomingDraw.gameId)
            .putExtra(DRAW_ID_EXTRA, upcomingDraw.drawId)
            .putExtra(DATE_TIME_EXTRA, upcomingDraw.drawTime)
    )
}


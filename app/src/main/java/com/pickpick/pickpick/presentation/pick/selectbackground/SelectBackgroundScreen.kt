package com.pickpick.pickpick.presentation.pick.selectbackground

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pickpick.pickpick.R
import com.pickpick.pickpick.core.ui.component.ParticipationAppBar
import com.pickpick.pickpick.core.ui.component.timer.TimerText
import com.pickpick.pickpick.core.ui.component.timer.rememberTimerState
import com.pickpick.pickpick.core.ui.theme.Black
import com.pickpick.pickpick.core.ui.theme.PrimaryDefault
import com.pickpick.pickpick.core.ui.theme.clickableNoRipple
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Body1Bold
import com.pickpick.pickpick.core.ui.theme.font.PyeojinGothicTypography.Heading3
import com.pickpick.pickpick.presentation.pick.selectbackground.viewmodel.SelectBackgroundViewModel
import kotlinx.coroutines.launch

@Composable
fun SelectBackgroundScreen(
    modifier: Modifier = Modifier,
    viewModel: SelectBackgroundViewModel = hiltViewModel(),
    onNavigateToResult: () -> Unit,
) {
    val profileImages = MutableList(3) {
        "https://i.guim.co.uk/img/media/327aa3f0c3b8e40ab03b4ae80319064e401c6fbc/377_133_3542_2834/master/3542.jpg?width=1200&height=1200&quality=85&auto=format&fit=crop&s=34d32522f47e4a67286f9894fc81c863"
    }

    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        10
    }

    val currentPage by remember {
        derivedStateOf { pagerState.currentPage }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    // 타이머 상태 관리
    val timerState = rememberTimerState(
        initialSeconds = 3, autoStart = true
    )

    // 타이머 완료시 자동 선택 및 네비게이션
    LaunchedEffect(timerState.isCompleted) {
        if (timerState.isCompleted) {
            // 현재 페이지의 배경 자동 선택
            viewModel.selectBackground(currentPage)
            // 다음 화면으로 네비게이션
            onNavigateToResult()
        }
    }




    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ) {
        ParticipationAppBar(
            profileImages = profileImages
        )
        Column(
            modifier = modifier.padding(
                horizontal = 16.dp, vertical = 32.dp,
            ),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(stringResource(R.string.select_background), style = Heading3)
            Spacer(modifier = modifier.height(8.dp))
            TimerText(time = timerState.remainingSeconds)
            Spacer(modifier = modifier.height(40.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    space = 20.dp, alignment = Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "배경 이름", style = Body1Bold.copy(color = Black)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = modifier
                            .width(16.dp)
                            .height(30.dp)
                            .clickableNoRipple(onClick = {
                                val prevPage = (currentPage - 1).coerceAtLeast(0)
                                scope.launch {
                                    pagerState.animateScrollToPage(prevPage)
                                }
                            }),
                        painter = painterResource(R.drawable.button),
                        contentDescription = "leftButton"
                    )
                    HorizontalPager(
                        modifier = Modifier.weight(1f),
                        state = pagerState,
                        pageSpacing = 40.dp,
                        contentPadding = PaddingValues(horizontal = 20.dp)
                    ) { page ->
                        val isSelected = uiState.selectedBackgroundIndex == page
                        Box {
                            Box(
                                modifier = modifier
                                    .border(
                                        color = if (isSelected) PrimaryDefault else Color.Transparent,
                                        width = 3.dp
                                    )
                                    .clickableNoRipple(onClick = {
                                        viewModel.selectBackground(page)
                                    })
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.frame),
                                    contentDescription = "background"
                                )
                            }

                            if (isSelected) Box(
                                modifier = modifier
                                    .size(24.dp)
                                    .offset(x = 10.dp, y = -12.dp)
                                    .align(alignment = Alignment.TopEnd)
                                    .background(
                                        color = PrimaryDefault,
                                        shape = CircleShape,
                                    )
                                    .padding(4.dp),
                            ) {
                                Icon(
                                    Icons.Rounded.Check,
                                    contentDescription = "check",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                    Image(
                        modifier = modifier
                            .width(16.dp)
                            .height(30.dp)
                            .rotate(180f)
                            .clickableNoRipple(onClick = {
                                val afterPage =
                                    (currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
                                scope.launch {
                                    pagerState.animateScrollToPage(afterPage)
                                }
                            }),
                        painter = painterResource(R.drawable.button),
                        contentDescription = "rightButton"
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp, alignment = Alignment.CenterHorizontally
                    )
                ) {
                    repeat(pagerState.pageCount) { page ->
                        Box(
                            modifier = modifier
                                .size(10.dp)
                                .background(
                                    color = if (page == currentPage) Black else Color.Transparent,
                                    shape = CircleShape
                                )
                                .border(
                                    width = 1.dp,
                                    shape = CircleShape, color = Black,
                                )
                        )
                    }
                }
            }


        }
    }
}


@Composable
@Preview(showBackground = true)
fun SelectBackgroundScreenPreview() {
    SelectBackgroundScreen(
        onNavigateToResult = {})
}

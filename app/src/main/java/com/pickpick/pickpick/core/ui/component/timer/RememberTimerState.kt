package com.pickpick.pickpick.core.ui.component.timer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay


data class TimerState(
    val remainingSeconds: Int,
    val isRunning: Boolean,
    val isCompleted: Boolean,
    val start: () -> Unit,
    val pause: () -> Unit,
    val reset: () -> Unit,
)


/**
 * 타이머 상태를 반환하는 Hook 스타일 함수
 *
 * @param initialSeconds 초기 시간
 * @param autoStart 자동 시작 여부
 * @return TimerState 객체
 */
@Composable
fun rememberTimerState(
    initialSeconds: Int,
    autoStart: Boolean = true,
): TimerState {
    var remainingSeconds by remember(initialSeconds) {
        mutableIntStateOf(initialSeconds)
    }

    var isRunning by remember(autoStart) {
        mutableStateOf(autoStart)
    }

    var isCompleted by remember(initialSeconds) {
        mutableStateOf(false)
    }

    // 타이머 로직
    LaunchedEffect(remainingSeconds, isRunning) {
        if (isRunning && remainingSeconds > 0 && !isCompleted) {
            delay(1000)
            remainingSeconds--
        } else if (remainingSeconds == 0 && !isCompleted) {
            isCompleted = true
            isRunning = false
        }
    }

    return remember(remainingSeconds, isRunning, isCompleted) {
        TimerState(
            remainingSeconds = remainingSeconds,
            isRunning = isRunning,
            isCompleted = isCompleted,
            start = {
                if (!isCompleted) {
                    isRunning = true
                }
            },
            pause = { isRunning = false },
            reset = {
                remainingSeconds = initialSeconds
                isRunning = autoStart
                isCompleted = false
            }
        )
    }
}

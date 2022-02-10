package com.example.cleanandroidarchitecture

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import com.example.cleanandroidarchitecture.ui.DetailActivity
import com.example.cleanandroidarchitecture.ui.MainActivity
import com.example.cleanandroidarchitecture.ui.SplashActivity
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
class SplashActivityTest {

    @Test
    fun 스플래시화면_일정시간_후_메인액티비티로_이동하는지_테스트() {
        ActivityScenario.launch(SplashActivity::class.java).use { scenario ->
            scenario.onActivity { activity: SplashActivity ->
                // 초기 화면은 DetailActivity -> 딜레이 후 MainActivity
                val expectedIntentBeforeDelay = Intent(activity, DetailActivity::class.java)
                val expectedIntentAfterDelay = Intent(activity, MainActivity::class.java)
                var actual = Shadows.shadowOf(RuntimeEnvironment.getApplication()).nextStartedActivity

                Assert.assertEquals(expectedIntentBeforeDelay.component, actual.component)

                // splash 실행 대기
                ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
                actual = Shadows.shadowOf(RuntimeEnvironment.getApplication()).nextStartedActivity

                Assert.assertEquals(expectedIntentAfterDelay.component, actual.component)
            }
        }

    }

}
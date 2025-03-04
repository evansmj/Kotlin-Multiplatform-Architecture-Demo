package com.oldgoat5.udemo.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.oldgoat5.udemo.network.stats.BitcoinStatsData
import com.oldgoat5.udemo.network.stats.IBitcoinStatsRepository
import com.oldgoat5.udemo.util.formatDollars
import org.koin.mp.KoinPlatform.getKoin

class UDemoWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        // Replace these calls with a call to the database.  It is best practice to get widget data
        // from local storage.  Price data can be scheduled to be updated every once in a while.
        val repository: IBitcoinStatsRepository = getKoin().get()
        val bitcoinStats = repository.getStats(true)

        provideContent {
            Widget(bitcoinStats)
        }
    }
}

@Composable
fun Widget(bitcoinStatsData: BitcoinStatsData) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(Color(0xFF002851))),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Price = " + formatDollars(bitcoinStatsData.quote.usd.price),
            style = TextStyle(color = ColorProvider(Color.White)))
    }
}

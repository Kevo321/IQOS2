package com.digitas.Screens.subscription

import android.graphics.Color
import com.github.mikephil.charting.data.PieEntry

object DataUtil {
    val mentholEntries = listOf<PieEntry>(
        PieEntry(33f, "Tidal pearl"),
        PieEntry(33f, "Oasis Pearl"),
        PieEntry(33f, "Arbor Pearl"),
    )
    val stoneFruitEntries = listOf<PieEntry>(
        PieEntry(33f, "Tropical Swift"),
        PieEntry(33f, "Bright Vibe"),
        PieEntry(33f, "Purple Wave"),
    )
    val firstSectionEntries = listOf<PieEntry>(
        PieEntry(25f, "StoneFruit"),
        PieEntry(10f, "Menthol"),
    )
    val yellowChartEntry = listOf<PieEntry>(
        PieEntry(25f, "Area 1"),
        PieEntry(10f, "Area 3"),
    )

    val colorCombinations = listOf<Int>(
        Color.rgb(92, 169, 198),
        Color.rgb(191, 68, 130),
        Color.rgb(12, 147, 113),
    )

    val stoneFruitCombination = listOf<Int>(
        Color.rgb(252, 195, 83),
        Color.rgb(238, 220, 0),
        Color.rgb(108, 84, 163),
    )

    val aromaNotesColors = listOf<Int>(
        Color.rgb(255,164,137),
        Color.rgb(134,202,221)
    )


}
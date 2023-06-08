package com.digitas.Screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.digitas.iqos.R
import com.digitas.iqos.ui.theme.*

class Flavour(
    @DrawableRes val headerImage: Int,
    val backgroundColor: androidx.compose.ui.graphics.Color,
    val darkMode: Boolean,
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
    @StringRes val description: Int,
    @StringRes val aromaNote: Int,
    @DrawableRes val aromaIcon: Int,
    val cooling: Int,
    val intensity: Int,
    val body: Int,
    val aroma: Int,
    var like: Boolean = false
)

val TropicalFlavour = Flavour(
    R.drawable.img_tropical,
    YellowTropical,
    true,
    R.string.tropical_title,
    R.string.tropical_subtitle,
    R.string.tropical_description,
    R.string.tropical_aroma,
    R.drawable.aroma_tropical,
    2,
    3,
    2,
    3
)

val PurpleFlavour = Flavour(
    R.drawable.img_purple,
    PurpleWave,
    false,
    R.string.purple_title,
    R.string.purple_subtitle,
    R.string.purple_description,
    R.string.purple_aroma,
    R.drawable.aroma_purple,
    4,
    3,
    3,
    5
)

val BrightFlavour = Flavour(
    R.drawable.img_bright,
    GreenBright,
    true,
    R.string.bright_title,
    R.string.bright_subtitle,
    R.string.bright_description,
    R.string.bright_aroma,
    R.drawable.aroma_bright,
    4,
    3,
    3,
    3
)

val TidalFlavour = Flavour(
    R.drawable.img_tidal,
    BlueTidal,
    true,
    R.string.tidal_title,
    R.string.tidal_subtitle,
    R.string.tidal_description,
    R.string.tidal_aroma,
    R.drawable.aroma_tidal,
    2,
    3,
    2,
    3
)

val OasisFlavour = Flavour(
    R.drawable.img_oasis,
    PinkOasis,
    false,
    R.string.oasis_title,
    R.string.oasis_subtitle,
    R.string.oasis_description,
    R.string.oasis_aroma,
    R.drawable.aroma_oasis,
    2,
    3,
    3,
    3
)

val ArborFlavour = Flavour(
    R.drawable.img_arbor,
    GreenArbor,
    false,
    R.string.arbor_title,
    R.string.arbor_subtitle,
    R.string.arbor_description,
    R.string.arbor_aroma,
    R.drawable.aroma_arbor,
    2,
    3,
    3,
    3
)
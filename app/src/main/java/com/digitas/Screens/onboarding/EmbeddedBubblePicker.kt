package com.digitas.Screens.onboarding

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.digitas.bubblepicker.BubblePickerListener
import com.digitas.bubblepicker.adapter.BubblePickerAdapter
import com.digitas.bubblepicker.model.PickerItem
import com.digitas.bubblepicker.rendering.BubblePicker
import com.digitas.iqos.R


@Composable
fun EmbeddedBubblePicker(
    @ArrayRes titles: Int,
    @ArrayRes sizes: Int,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val bubblePicker: BubblePicker = rememberBubblePickerWithLifecycle(titles, sizes)
    DisposableEffect(key1 = true) {
        val observer = LifecycleEventObserver { source, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                bubblePicker.onResume()
            }
            if (event == Lifecycle.Event.ON_PAUSE) {
                bubblePicker.onPause()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}

@Composable
fun rememberBubblePickerWithLifecycle(
    @ArrayRes titlesRes: Int,
    @ArrayRes sizeRes: Int
): BubblePicker {
    val context = LocalContext.current
    val view = LayoutInflater.from(context).inflate(R.layout.bubble_picker, null, false)
    val picker = view.findViewById<BubblePicker>(R.id.picker)
    val resources = context.resources
    val titles = resources.getStringArray(titlesRes)
    val colors = resources.obtainTypedArray(R.array.colors)
    val images = resources.obtainTypedArray(R.array.images)
    picker.bubbleSize = 100
    picker.bubbleRadiouses = resources.getIntArray(sizeRes).toTypedArray()
    picker.adapter = object : BubblePickerAdapter {

        override val totalCount = titles.size
        override fun getItem(position: Int): PickerItem {
            return PickerItem().apply {
                title = titles[position]
                color = colors.getColor(position, 0)
                overlayAlpha = 0.0f
                textColor = ContextCompat.getColor(context, android.R.color.white)
                textSize = dipToPixels(context, 13f)
                typeface = ResourcesCompat.getFont(context, com.digitas.iqos.R.font.iqosbold)!!
                backgroundImage =
                    ContextCompat.getDrawable(
                        context,
                        images.getResourceId(position, 0)
                    )
            }
        }
    }
    colors.recycle()
    images.recycle()
    picker.listener = object : BubblePickerListener {
        override fun onBubbleSelected(item: PickerItem) {
            Log.d("BubblePicker", "Bubble selected " + item.title)
        }

        override fun onBubbleDeselected(item: PickerItem) {
            Log.d("BubblePicker", "Bubble deselected " + item.title)
        }
    }
    AndroidView(
        factory = { context ->
            // do whatever you want...
            view // return the view
        },
        update = { view ->
            // Update the view
        }
    )
    return picker
}

fun dipToPixels(context: Context, dipValue: Float): Float {
    val metrics: DisplayMetrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics)
}
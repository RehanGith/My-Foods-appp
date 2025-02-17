package com.example.myfoods

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class ExpandedRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        // Create a measure spec that allows the RecyclerView to be as tall as needed.
        val expandedSpec = MeasureSpec.makeMeasureSpec(Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        super.onMeasure(widthSpec, expandedSpec)
    }
}

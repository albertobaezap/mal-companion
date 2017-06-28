package com.example.alberto.malcompanion.data.extensions

import android.content.Context
import android.graphics.Matrix
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

val View.ctx: Context
    get() = context

/**
 * This extension function crops the original image to anchor from the top.
 * Need to put scaleType = matrix on the view.
 */
fun ImageView.setTopCrop() {
        val matrix = getImageMatrix();
        val imageWidth = getDrawable().getIntrinsicWidth();
        val screenWidth = getResources().getDisplayMetrics().widthPixels;
        val scaleRatio: Float = screenWidth.toFloat() / imageWidth;
        matrix.postScale(scaleRatio, scaleRatio);
        setImageMatrix(matrix);
}

/**
 * Set a text color programmatically to a textview
 */
var TextView.textColor: Int
        get() = currentTextColor
        set(v) = setTextColor(v)
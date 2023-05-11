package com.kkc.githubusers.placeHolder

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import com.kkc.usecase.entity.PlaceHolderInfo

class RectanglePlaceHolder(private val placeHolderInfo: com.kkc.usecase.entity.PlaceHolderInfo) : Drawable() {
    private val paint by lazy {
        Paint().apply {
            color = placeHolderInfo.backgroundColor
            style = Paint.Style.FILL
        }
    }
    override fun draw(canvas: Canvas) {
        canvas.drawRect(Rect(0, 0, placeHolderInfo.width, placeHolderInfo.height), paint)
        paint.color = placeHolderInfo.contentColor
        paint.textSize = 14f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(placeHolderInfo.holdingText, 0F, 0F, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}

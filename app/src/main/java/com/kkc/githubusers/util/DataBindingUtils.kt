package com.kkc.githubusers.util

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.kkc.githubusers.R
import com.kkc.usecase.entity.PlaceHolderInfo
import com.kkc.githubusers.placeHolder.CirclePlaceHolder
import com.kkc.githubusers.placeHolder.RectanglePlaceHolder

@BindingAdapter("thumbnail")
fun ImageView.setThumbnail(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .placeholder(
                CirclePlaceHolder(
                    com.kkc.usecase.entity.PlaceHolderInfo(
                        width = this.width,
                        height = this.height,
                        backgroundColor = ContextCompat.getColor(this.context, R.color.grey),
                        contentColor = Color.BLACK,
                        holdingText = this.context.getString(R.string.holding)
                    )
                )
            )
            .into(this)
    }
}

@BindingAdapter("glideImage")
fun ImageView.setGlideImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .placeholder(
                RectanglePlaceHolder(
                    com.kkc.usecase.entity.PlaceHolderInfo(
                        width = this.width,
                        height = this.height,
                        backgroundColor = ContextCompat.getColor(this.context, R.color.grey),
                        contentColor = Color.BLACK,
                        holdingText = this.context.getString(R.string.holding)
                    )
                )
            )
            .into(this)
    }
}

@BindingAdapter("linkText")
fun TextView.setLinkText(link: String?) {
    link?.let {
        val linkSpannable = SpannableStringBuilder(it)
        linkSpannable.setSpan(
            object : ClickableSpan() {
                override fun onClick(view: View) {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(link)
                    context.startActivity(intent)
                }
            },
            0,
            it.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        this.text = linkSpannable
        this.movementMethod = LinkMovementMethod.getInstance()
    }
}

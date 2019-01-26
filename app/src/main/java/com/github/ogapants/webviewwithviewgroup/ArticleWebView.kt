package com.github.ogapants.webviewwithviewgroup

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView

class ArticleWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    WebView(context, attrs, defStyle), ArticleContainer.WebViewOnTouchDispatcher {

    private var onScrollChangeListener: OnScrollChangeListenerCompat? = null

    fun screenPxToWebPx(screenPx: Int): Int {
        return (screenPx / scale).toInt()
    }

    fun webPxToScreenPx(webPx: Int): Int {
        return (webPx * scale).toInt()
    }

    fun setOnScrollChangeListenerCompat(onScrollChangeListener: OnScrollChangeListenerCompat) {
        this.onScrollChangeListener = onScrollChangeListener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onScrollChangeListener!!.onScrollChange(this, l, t, oldl, oldt)
    }

    override fun dispatcherOnTouchEvent(ev: MotionEvent): Boolean {
        return onTouchEvent(ev)
    }

    interface OnScrollChangeListenerCompat {
        fun onScrollChange(v: View, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int)
    }
}

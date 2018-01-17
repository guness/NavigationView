package com.guness.widget

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.annotation.UiThread
import android.support.design.internal.NavigationMenuView
import android.support.v7.widget.TintTypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by guness on 17.01.2018.
 */
class NavigationView : android.support.design.widget.NavigationView {

    private var mHeader: View? = null
    private var mFooter: View? = null
    private var mMenuView: NavigationMenuView? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = TintTypedArray.obtainStyledAttributes(context, attrs,
                R.styleable.NavigationView, defStyleAttr,
                R.style.Widget_Design_NavigationView)

        if (a.hasValue(R.styleable.NavigationView_footerLayout)) {
            inflateFooterView(a.getResourceId(R.styleable.NavigationView_footerLayout, 0))
        }

        a.recycle()

        (mFooter?.layoutParams as FrameLayout.LayoutParams?)?.gravity = Gravity.BOTTOM
    }

    init {
        (0 until childCount)
                .map { getChildAt(it) }
                .filter { it is NavigationMenuView }
                .forEach {
                    mMenuView = it as NavigationMenuView
                    mMenuView!!.overScrollMode = View.OVER_SCROLL_NEVER
                }
    }

    override fun inflateHeaderView(@LayoutRes res: Int): View {
        mHeader = LayoutInflater.from(context).inflate(res, this, false)
        setHeaderView(mHeader!!)
        return mHeader!!
    }

    @Deprecated("There can only be one header", ReplaceWith("#setHeaderView(view: View)"))
    override fun addHeaderView(view: View) {
        throw IllegalAccessException("Please use #setHeaderView")
    }

    @UiThread
    fun setHeaderView(view: View) {
        removeHeaderView()
        mHeader = view
        addView(mHeader, 0)
    }

    @Deprecated("No need to use params", ReplaceWith("#removeHeaderView()"))
    override fun removeHeaderView(view: View) {
        removeHeaderView()
    }

    @UiThread
    fun removeHeaderView() {
        if (mHeader != null) {
            removeView(mHeader)
            mHeader = null
        }
    }

    @Deprecated("No need to count, it is either 1 or zero", ReplaceWith("#hasHeader()"))
    override fun getHeaderCount(): Int {
        return if (mHeader == null) 0 else 1
    }

    @Deprecated("No need to use params", ReplaceWith("#getHeaderView()"))
    override fun getHeaderView(index: Int): View? {
        return getHeaderView()
    }

    fun getHeaderView(): View? {
        return mHeader
    }

    fun hasHeader(): Boolean {
        return mHeader != null
    }

    fun inflateFooterView(@LayoutRes res: Int): View {
        mFooter = LayoutInflater.from(context).inflate(res, this, false)
        setFooterView(mFooter!!)
        return mFooter!!
    }

    @UiThread
    fun setFooterView(view: View) {
        removeFooterView()
        mFooter = view
        addView(mFooter, 0)
    }

    @UiThread
    fun removeFooterView() {
        if (mFooter != null) {
            removeView(mFooter)
            mFooter = null
        }
    }

    fun hasFooter(): Boolean {
        return mFooter != null
    }

    fun getFooterView(): View? {
        return mFooter
    }

    fun setOnClickListener(@IdRes res: Int, listener: View.OnClickListener) {
        mHeader?.findViewById<View>(res)?.setOnClickListener(listener)
        mFooter?.findViewById<View>(res)?.setOnClickListener(listener)
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        val headerHeight = mHeader?.measuredHeight ?: 0
        val footerHeight = mFooter?.measuredHeight ?: 0
        val params = (mMenuView?.layoutParams as ViewGroup.MarginLayoutParams?)
        var changed = false
        if (params?.topMargin != headerHeight) {
            params?.topMargin = headerHeight
            changed = true
        }
        if (params?.bottomMargin != footerHeight) {
            params?.bottomMargin = footerHeight
            changed = true
        }
        if (changed) {
            mMenuView!!.measure(widthSpec, heightSpec)
        }
    }
}
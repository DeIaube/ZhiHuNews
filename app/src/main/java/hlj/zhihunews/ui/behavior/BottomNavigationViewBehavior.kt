package hlj.zhihunews.ui.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View


/**
 * Created by Administrator on 2017/6/14 0014.
 */
class BottomNavigationViewBehavior : CoordinatorLayout.Behavior<View> {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onLayoutChild(parent: CoordinatorLayout?, child: View?, layoutDirection: Int): Boolean {
        (child!!.getLayoutParams() as CoordinatorLayout.LayoutParams).topMargin = parent!!.measuredHeight - child!!.getMeasuredHeight()
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val top = ((dependency.getLayoutParams() as CoordinatorLayout.LayoutParams).behavior as AppBarLayout.Behavior).topAndBottomOffset
        ViewCompat.setTranslationY(child, (-top).toFloat())
        return false
    }
}
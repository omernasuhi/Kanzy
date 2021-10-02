package com.kanzy.music.base

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomDialog<VB : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null

    val binding
        get() = _binding
            ?: throw IllegalStateException(
                "Cannot access view in after view destroyed " +
                        "and before view creation"
            )

    protected var viewId: Int = -1

    protected fun requireBinding(): VB = requireNotNull(_binding)

    abstract fun createBinding(): VB

    abstract fun onViewReady(bundle: Bundle?)

    open fun onObserveState() {}

    open fun onViewListener() {}

    open fun onPreCreateDialog() {}

    open fun showLoading() {
        (activity as BaseActivity<*>).showLoading()
    }

    open fun hideLoading() {
        (activity as BaseActivity<*>).hideLoading()
    }

    lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    var bottomView: View? = null

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding()
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewId = binding.root.id
        onObserveState()
        onViewReady(savedInstanceState)
        onViewListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupFullHeight(bottomSheet: View?) {
        val layoutParams = bottomSheet?.layoutParams
        layoutParams?.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet?.layoutParams = layoutParams
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            bottomView =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomView?.let {
                bottomSheetBehavior = BottomSheetBehavior.from(it)
            }

            //bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

            bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels

            bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(view: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        dismissAllowingStateLoss()
                    }
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(p0: View, p1: Float) {}

            })
            onPreCreateDialog()
        }
        return dialog
    }

}
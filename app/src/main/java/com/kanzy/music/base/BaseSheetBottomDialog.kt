package com.kanzy.music.base

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseSheetBottomDialog<VB : ViewBinding> : BottomSheetDialogFragment() {

    private var _binding: VB? = null

    protected val binding
        get() = _binding
            ?: throw IllegalStateException(
                "Cannot access view in after view destroyed " +
                        "and before view creation"
            )

    abstract fun createBinding(): VB

    abstract fun onViewReady(bundle: Bundle?)

    open fun onViewListener() {}

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private var bottomView: View? = null
    private var isCanceledOnTouchOutsideMode = false

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
        onViewReady(savedInstanceState)
        onViewListener()
        requireDialog().setCanceledOnTouchOutside(isCanceledOnTouchOutsideMode)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var isCanceledOnTouchOutside: Boolean
        get() = isCanceledOnTouchOutsideMode
        set(value) {
            isCanceledOnTouchOutsideMode = value

        }

    fun closeDialog() {
        dialog?.dismiss()
    }

    fun setupFullHeight() {
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            bottomView =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomView?.let {
                bottomSheetBehavior = BottomSheetBehavior.from(it)
            }

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
        }
        return dialog
    }

}
package com.sample.gutenberg.ui.base

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    companion object {
        private val TAG = BaseFragment::class.simpleName
    }

    protected fun debugLog(message: String) = Log.d(TAG, message)

    protected fun errorLog(message: String) = Log.e(TAG, message)

    protected fun verboseLog(message: String) = Log.v(TAG, message)

    protected fun showToast(message: String) =
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
}
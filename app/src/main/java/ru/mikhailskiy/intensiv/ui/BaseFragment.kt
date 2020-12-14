package ru.mikhailskiy.intensiv.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }
}
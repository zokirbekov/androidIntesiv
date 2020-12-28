package ru.mikhailskiy.intensiv.presentation

import android.content.Context
import android.net.ConnectivityManager
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import ru.mikhailskiy.intensiv.domain.repository.NetworkRepository

open class BaseFragment : Fragment(), NetworkRepository {
    protected val compositeDisposable = CompositeDisposable()

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun isAvailable(): Boolean {
        val manager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetworkInfo?.isConnected == true
    }
}
package ru.mikhailskiy.intensiv.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.common.VerticalSpaceDecoration
import ru.mikhailskiy.intensiv.data.tvShow.TvShow
import ru.mikhailskiy.intensiv.data.tvShow.TvShowMockRepository
import ru.mikhailskiy.intensiv.data.tvShow.TvShowResponse
import ru.mikhailskiy.intensiv.network.client.TvShowApiClient
import timber.log.Timber

class TvShowsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tv_shows_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_tv_shows?.addItemDecoration(VerticalSpaceDecoration(12))
        list_tv_shows?.adapter = adapter
        getTvShows()
    }

    private fun getTvShows()
    {
        tv_progress.isVisible = true
        TvShowApiClient.api.getPopular()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.results
            }
            .subscribe(
                {
                    it?.let {
                        tvShows ->
                        adapter.addAll(tvShows.map { tvShow ->
                            TvShowItem(tvShow, this@TvShowsFragment::tvShowItemClicked)
                        })
                    }
                },
                {
                    Timber.e(it)
                }
            )
    }

    private fun tvShowItemClicked(item:TvShow)
    {
        findNavController().navigate(R.id.movie_details_fragment)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TvShowsFragment().apply {

            }
    }
}
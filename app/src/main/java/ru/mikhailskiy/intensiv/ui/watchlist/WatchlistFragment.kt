package ru.mikhailskiy.intensiv.ui.watchlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_watchlist.movies_recycler_view
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.movie.MovieDto
import ru.mikhailskiy.intensiv.db.MovieDatabase
import ru.mikhailskiy.intensiv.extension.applySchedulers
import ru.mikhailskiy.intensiv.mapper.MovieMapper
import ru.mikhailskiy.intensiv.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.ui.BaseFragment

class WatchlistFragment : BaseFragment() {
    val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies_recycler_view.layoutManager = GridLayoutManager(context, 4)
        movies_recycler_view.adapter = adapter

        val disposable = MovieDatabase.get(requireContext()).movieDao().getOnlyFavorites()
            .applySchedulers()
            .map {
                it.map { movieEntity ->
                    MoviePreviewItem(MovieMapper.entityToDto(movieEntity), this::onItemClicked)
                }
            }
            .subscribe {
                adapter.addAll(it)
            }

        compositeDisposable.add(disposable)
    }

    private fun onItemClicked(item:MovieDto) {

    }

    companion object {
        fun newInstance(): WatchlistFragment{
            return WatchlistFragment()
        }
    }


}
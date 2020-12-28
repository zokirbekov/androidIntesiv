package ru.mikhailskiy.intensiv.presentation.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_watchlist.movies_recycler_view
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.useCase.FavoriteMovieUseCase
import ru.mikhailskiy.intensiv.presentation.BaseFragment
import timber.log.Timber

class WatchlistFragment : BaseFragment() {
    val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies_recycler_view.layoutManager = GridLayoutManager(context, 4)

        movies_recycler_view.adapter = adapter
        adapter.clear()
        val disposable = getFavoriteMovies()
        disposable?.let {
            compositeDisposable.add(it)
        }
    }

    private fun getFavoriteMovies() =
        (parentFragment as? FavoriteMoviesHelper)
            ?.getFavoriteUseCase()
            ?.getFavoriteMovies()
            ?.subscribe(
                {
                    adapter.addAll(
                        it.map { movie ->
                            MoviePreviewItem(movie, this::movieClicked)
                        }
                    )
                },
                {
                    Timber.e(it)
                }
            )


    private fun movieClicked(movie: MovieVo) {
        (parentFragment as? FavoriteMoviesHelper)?.navigateToDetail(movie)
    }

    companion object {
        fun newInstance(): WatchlistFragment {
            return WatchlistFragment()
        }
    }

    interface FavoriteMoviesHelper {
        fun navigateToDetail(movieVo: MovieVo)
        fun getFavoriteUseCase() : FavoriteMovieUseCase
    }

}
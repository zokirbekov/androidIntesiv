package ru.mikhailskiy.intensiv.ui.feed

import android.os.Bundle
import android.view.*
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.movie.Movie
import ru.mikhailskiy.intensiv.data.movie.MovieResponse
import ru.mikhailskiy.intensiv.extension.applySchedulers
import ru.mikhailskiy.intensiv.extension.setProgressOnFinalAndOnSubscribe
import ru.mikhailskiy.intensiv.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.ui.BaseFragment
import ru.mikhailskiy.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : BaseFragment() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val categoryNameObservable =
        Observable.just(R.string.upcoming, R.string.now_playing, R.string.popular)
    private val movieCategories = Observable.just(
        MovieApiClient.api.getUpcoming(),
        MovieApiClient.api.getNowPlaying(),
        MovieApiClient.api.getPopular()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.feed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Добавляем recyclerView
        movies_recycler_view.layoutManager = LinearLayoutManager(context)
        movies_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        search_toolbar.search_edit_text.afterTextChanged {
            val str = it.toString().trim()
            Timber.d(str)
            if (str.length > 3) {
                openSearch(str)
            }
        }

        movies_recycler_view.adapter = adapter

        val disposable = getCategoryMovies()
        compositeDisposable.add(disposable)
//
//        getCategoryMovies(R.string.upcoming, MovieApiClient.api.getUpcoming())
//        getCategoryMovies(R.string.now_playing, MovieApiClient.api.getNowPlaying())
//        getCategoryMovies(R.string.popular, MovieApiClient.api.getPopular())
    }

    private fun getCategoryMovies() : Disposable{
        return Observable
            .zip(
                categoryNameObservable,
                movieCategories,
                BiFunction<Int, Single<MovieResponse>, Pair<Int, Single<MovieResponse>>> { t1, t2 ->
                    Pair(t1, t2)
                })
            .doOnEach { pair->
                pair.value?.second?.applySchedulers()
                    ?.map { response ->
                        response.results?.map { movie ->
                            MovieItem(movie) { movieItem ->
                                openMovieDetails(
                                    movieItem
                                )
                            }
                        }
                    }
                    ?.subscribe(
                        { movieItems ->
                            movieItems?.let { items->
                                val container = MainCardContainer(
                                    pair.value?.first!!,
                                    items.toList()
                                )
                                adapter.add(container)
                            }
                        },
                        {
                            Timber.e(it)
                        }
                    )
            }
            .setProgressOnFinalAndOnSubscribe(movie_progress)
            .subscribe()
    }
//    private fun getCategoryMovies(
//        @StringRes categoryTitle: Int,
//        observable: Single<MovieResponse>
//    ) {
//        val disposable = observable
//            .applySchedulers()
//            .map {
//                it.results?.map { movie ->
//                    MovieItem(movie) { movieItem ->
//                        openMovieDetails(
//                            movieItem
//                        )
//                    }
//                }
//            }
//            .subscribe({ movies ->
//                movies?.let {
//                    val container = MainCardContainer(
//                        categoryTitle,
//                        it.toList()
//                    )
//                    adapter.add(container)
//                }
//            },
//                { t ->
//                    Timber.e(t)
//                }
//            )
//        compositeDisposable.add(disposable)
//    }

    private fun openMovieDetails(movie: Movie) {
        val action = FeedFragmentDirections.actionHomeDestToMovieDetailFragment(movie.id ?: -1)
        findNavController().navigate(action)
    }

    private fun openSearch(searchText: String) {
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        val bundle = Bundle()
        bundle.putString("search", searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        search_toolbar.clear()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }
}
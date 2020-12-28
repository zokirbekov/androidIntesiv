package ru.mikhailskiy.intensiv.presentation.feed

import android.graphics.Movie
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.db.MovieDatabase
import ru.mikhailskiy.intensiv.data.repository.*
import ru.mikhailskiy.intensiv.extension.setProgressOnFinalAndOnSubscribe
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.useCase.FeedUseCase
import ru.mikhailskiy.intensiv.presentation.BaseFragment
import ru.mikhailskiy.intensiv.presentation.afterTextChanged
import timber.log.Timber

class FeedFragment : BaseFragment() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val movieDao by lazy {
        MovieDatabase.get(requireContext()).movieDao()
    }

    private val categoryDao by lazy {
        MovieDatabase.get(requireContext()).categoryDao()
    }

    private val feedUseCase by lazy {
        FeedUseCase(
            hashMapOf(
                CategoryRepository.CategoryType.NOW_PLAYING to NowplayingMoviesRepository(
                    this,
                    NowplayingMoviesRemoteRepository(),
                    NowplayingMoviesLocaleRepository(movieDao, categoryDao)
                ),
                CategoryRepository.CategoryType.POPULAR to PopularMoviesRepository(
                    this,
                    PopularMoviesRemoteRepository(),
                    PopularMoviesLocaleRepository(movieDao, categoryDao)
                ),
                CategoryRepository.CategoryType.UPCOMING to UpcomingMoviesRepository(
                    this,
                    UpcomingMoviesRemoteRepository(),
                    UpcomingMoviesLocaleRepository(movieDao, categoryDao)
                )
            )
        )
    }

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
    }

    private fun getCategoryTitle(categoryType: Long) =
        when (categoryType) {
            CategoryRepository.CategoryType.UPCOMING -> R.string.upcoming
            CategoryRepository.CategoryType.NOW_PLAYING -> R.string.now_playing
            CategoryRepository.CategoryType.POPULAR -> R.string.popular
            else -> R.string.movie
        }

    private fun getCategoryMovies() =
        feedUseCase.getZippedMovies()
//            .singleOrError()
            .setProgressOnFinalAndOnSubscribe(movie_progress)
            .map {
                it.map { keyValue ->
                    val movieItems = keyValue.value.map {  movie ->
                        MovieItem(movie,this::openMovieDetails)
                    }
                    MainCardContainer(
                        getCategoryTitle(keyValue.key),
                        movieItems
                    )
                }
            }
            .doOnError {
                Timber.e(it)
            }
            .subscribe(
                {
                    adapter.addAll(it)
                },
                {
                    Timber.e(it)
                }
            )

    private fun openMovieDetails(movie: MovieVo) {
        val action = FeedFragmentDirections.actionHomeDestToMovieDetailFragment(movie.id?.toInt() ?: -1)
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
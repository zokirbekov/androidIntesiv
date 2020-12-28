package ru.mikhailskiy.intensiv.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.presentation.common.VerticalSpaceDecoration
import ru.mikhailskiy.intensiv.data.repository.SearchMoviesRemoteRepository
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.useCase.SearchMoviesUseCase
import ru.mikhailskiy.intensiv.presentation.BaseFragment
import ru.mikhailskiy.intensiv.presentation.SearchBar
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val useCase = SearchMoviesUseCase(SearchMoviesRemoteRepository())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val searchTerm = requireArguments().getString("search")
        search_toolbar.setText(searchTerm)

//        movies_recycler_view.layoutManager = GridLayoutManager(requireContext(), 2)
        movies_recycler_view?.adapter = adapter
        movies_recycler_view?.addItemDecoration(VerticalSpaceDecoration(8))

        val disposable = search_toolbar.searcheObservable
            .debounce(SearchBar.SEARCH_AFTER_MILLISECONDS, TimeUnit.MILLISECONDS)
            .map {
                it.trim()
            }
            .filter {
                it.length > SearchBar.MIN_LENGTH_FOR_SEARCH
            }
            .subscribe {
                searchMovie(it)
            }

        compositeDisposable.add(disposable)

        searchMovie(searchTerm!!)
    }

    private fun searchMovie(searchText: String) {
        val disposable = useCase.searchMovies(searchText)
            .map { movies ->
                movies.map { movie ->
                    SearchResultItem(movie, this::openMovieDetails)
                }
            }
            .subscribe(
                { movies ->
                    adapter.clear()
                    movies?.let {
                        adapter.addAll(it.toList())
                    }
                },
                { throwable ->
                    Timber.e(throwable)
                }
            )
        compositeDisposable.addAll(disposable)
    }

    private fun openMovieDetails(movie: MovieVo) {
        val action = SearchFragmentDirections.actionSearchDestToMovieDetailsFragment(movie.id?.toInt() ?: -1)
        findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()

    }
}
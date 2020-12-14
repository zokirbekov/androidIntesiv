package ru.mikhailskiy.intensiv.ui.search

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
import ru.mikhailskiy.intensiv.common.VerticalSpaceDecoration
import ru.mikhailskiy.intensiv.data.movie.Movie
import ru.mikhailskiy.intensiv.extension.applySchedulers
import ru.mikhailskiy.intensiv.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.ui.BaseFragment
import ru.mikhailskiy.intensiv.ui.SearchBar
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : BaseFragment() {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

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
        val disposable = MovieApiClient.api.search(searchText)
            .applySchedulers()
            .map { response ->
                response.results?.map { movie ->
                    SearchResultItem(movie) { movieItem ->
                        openMovieDetails(
                            movieItem
                        )
                    }
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

    private fun openMovieDetails(movie: Movie) {
        val action = SearchFragmentDirections.actionSearchDestToMovieDetailsFragment(movie.id ?: -1)
        findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()

    }
}
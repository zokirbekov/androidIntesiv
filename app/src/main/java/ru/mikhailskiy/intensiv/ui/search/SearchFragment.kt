package ru.mikhailskiy.intensiv.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.common.VerticalSpaceDecoration
import ru.mikhailskiy.intensiv.data.movie.Movie
import ru.mikhailskiy.intensiv.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.ui.feed.FeedFragmentDirections
import ru.mikhailskiy.intensiv.ui.feed.MovieItem
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment() {

    lateinit var observable: Observable<String>
    lateinit var disposable: Disposable

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

        observable = Observable.create<String> { emitter ->
            search_toolbar.search_edit_text.addTextChangedListener {
                val str = it.toString().trim()
                if (str.length > 3)
                    emitter.onNext(str)
            }
        }

        disposable = observable
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                searchMovie(it)
            }

        searchMovie(searchTerm!!)
    }

    private fun searchMovie(searchText:String)
    {
        MovieApiClient.api.search(searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                response.results
            }
            .subscribe(
                { movies ->

                    adapter.clear()
                    movies?.let {m->
                        adapter.addAll(m.map { movie ->
                            SearchItem(movie) { movieItem ->
                                openMovieDetails(
                                    movieItem
                                )
                            }
                        }.toList())
                    }
                },
                {
                        throwable ->
                    Timber.e(throwable)
                }
            )
    }

    private fun openMovieDetails(movie: Movie) {
        val action = SearchFragmentDirections.actionSearchDestToMovieDetailsFragment(movie.id ?: -1)
        findNavController().navigate(action)
    }

    override fun onStop() {
        super.onStop()
        if (::disposable.isInitialized)
            disposable.dispose()
    }
}
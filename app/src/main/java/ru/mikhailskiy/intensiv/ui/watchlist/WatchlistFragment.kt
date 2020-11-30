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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WatchlistFragment : Fragment() {
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
        movies_recycler_view.adapter = adapter.apply { addAll(listOf()) }

//        val moviesList =
//            MockRepository.getMovies().map {
//                MoviePreviewItem(
//                    it
//                ) { movie -> }
//            }.toList()

        movies_recycler_view.adapter = adapter
    }

    companion object {
        fun newInstance(): WatchlistFragment{
            return WatchlistFragment()
        }
    }


}
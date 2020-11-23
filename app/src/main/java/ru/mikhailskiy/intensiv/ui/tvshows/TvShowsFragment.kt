package ru.mikhailskiy.intensiv.ui.tvshows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.common.VerticalSpaceDecoration
import ru.mikhailskiy.intensiv.data.tvShow.TvShow
import ru.mikhailskiy.intensiv.data.tvShow.TvShowMockRepository

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
        list_tv_shows?.adapter = adapter.apply {
            addAll(TvShowMockRepository.getMovies().map {
                TvShowItem(it, this@TvShowsFragment::tvShowItemClicked)
            })
        }

    }

    private fun tvShowItemClicked(item:TvShow)
    {

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TvShowsFragment().apply {

            }
    }
}
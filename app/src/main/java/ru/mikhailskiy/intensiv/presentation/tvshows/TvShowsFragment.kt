package ru.mikhailskiy.intensiv.presentation.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.presentation.common.VerticalSpaceDecoration
import ru.mikhailskiy.intensiv.data.repository.TvShowsRemoteRepository
import ru.mikhailskiy.intensiv.data.vo.tvShow.TvShowVo
import ru.mikhailskiy.intensiv.domain.useCase.TvShowsUseCase
import ru.mikhailskiy.intensiv.presentation.BaseFragment
import timber.log.Timber

class TvShowsFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val tvShowsUseCase = TvShowsUseCase(TvShowsRemoteRepository())

    private val viewModelFactory = TvShowsViewModelFactory(tvShowsUseCase)

    private val viewModel: TvShowsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(TvShowsViewModel::class.java)
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

        viewModel.liveOnProgress.observe(viewLifecycleOwner, Observer {
            tv_progress.isVisible = it
        })

        viewModel.liveTvShows.observe(viewLifecycleOwner, Observer {
            tvShowsToView(it)
        })

        list_tv_shows?.addItemDecoration(VerticalSpaceDecoration(12))
        list_tv_shows?.adapter = adapter
        getTvShows()
    }

    private fun getTvShows() {
        val disposable = viewModel.getTvShows()
        compositeDisposable.add(disposable)
    }

    private fun tvShowsToView(tvShows: List<TvShowVo>) {
        val items = tvShows.map {
            TvShowItem(it, this::tvShowItemClicked)
        }
        adapter.addAll(items)
    }

    private fun tvShowItemClicked(item: TvShowVo) {
        findNavController().navigate(R.id.movie_details_fragment)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TvShowsFragment().apply {

            }
    }
}
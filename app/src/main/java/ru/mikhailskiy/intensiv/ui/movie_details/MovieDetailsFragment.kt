package ru.mikhailskiy.intensiv.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.movie_details_fragment.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.common.HorizontalSpaceDecoration
import ru.mikhailskiy.intensiv.data.credit.Credit
import ru.mikhailskiy.intensiv.data.movie.MovieDto
import ru.mikhailskiy.intensiv.data.movie.MovieEntity
import ru.mikhailskiy.intensiv.data.movie.detail.MovieDetail
import ru.mikhailskiy.intensiv.data.movie.detail.MovieDtoDetail
import ru.mikhailskiy.intensiv.db.MovieDatabase
import ru.mikhailskiy.intensiv.extension.applySchedulers
import ru.mikhailskiy.intensiv.extension.setImageFromBackend
import ru.mikhailskiy.intensiv.extension.setProgressOnFinalAndOnSubscribe
import ru.mikhailskiy.intensiv.mapper.MovieMapper
import ru.mikhailskiy.intensiv.network.client.MovieApiClient
import ru.mikhailskiy.intensiv.ui.BaseFragment
import timber.log.Timber

class MovieDetailsFragment : BaseFragment() {

    private val args by navArgs<MovieDetailsFragmentArgs>()

    private var currentMovie:MovieEntity? = null

    private val actorAdapter: GroupAdapter<GroupieViewHolder> by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actors_list?.addItemDecoration(HorizontalSpaceDecoration(32))

        back_image?.setOnClickListener {
            findNavController().popBackStack()
        }

        favorite_image?.setOnClickListener {
            currentMovie?.let {
                it.isFavorite = true
                MovieDatabase.get(requireContext()).movieDao().update(it)
            }
        }

        if (args.movieId != -1) {
            getMovieDetail()
            getMovieCredits()
        }

    }

    private fun onFailure(t: Throwable) {
        Timber.e(t)
    }

    private fun getMovieDetail() {
        val disposable = MovieApiClient.api.getDetail(args.movieId)
            .applySchedulers()
            .setProgressOnFinalAndOnSubscribe(detail_progress)
            .subscribe(
                {
                    currentMovie = MovieMapper.dtoToEntity(it as MovieDto)
                    movieDetailToView(it)
                },
                {
                    onFailure(it)
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun getMovieCredits() {
        val disposable = MovieApiClient.api.getCredits(args.movieId)
            .applySchedulers()
            .map {
                it.cast
            }
            .subscribe(
                {
                    it?.let { credits ->
                        creditsToView(credits)
                    }

                },
                {
                    onFailure(it)
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun movieDetailToView(movieDetail: MovieDtoDetail) {
        title_text?.text = movieDetail.title
        description_text?.text = movieDetail.overview
        genre_text?.text = movieDetail.genre?.map { it.name }?.joinToString()
        studio_text?.text = movieDetail.productionCompanies?.map { it.name }?.joinToString()
        year_text?.text = movieDetail.releaseDate

        movie_image.setImageFromBackend(movieDetail.backdropPath)

        movie_rating.rating = movieDetail.rating

//        val imageId = when (movieDetail.movieQuality)
//        {
//            "4K" -> R.drawable.ic_4khd
//            else -> R.drawable.ic_4khd
//        }

//        movie_quality_image?.setImageResource(imageId)

    }

    private fun creditsToView(list: List<Credit>) {
        actors_list?.adapter = actorAdapter.apply {
            addAll(list.map {
                ActorItem(it, this@MovieDetailsFragment::actorClicked)
            })
        }
    }

    private fun actorClicked(credit: Credit) {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieDetailsFragment()
    }
}
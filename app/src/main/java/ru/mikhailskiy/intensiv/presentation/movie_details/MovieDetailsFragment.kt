package ru.mikhailskiy.intensiv.presentation.movie_details

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
import ru.mikhailskiy.intensiv.presentation.common.HorizontalSpaceDecoration
import ru.mikhailskiy.intensiv.extension.setImageFromBackend
import ru.mikhailskiy.intensiv.extension.setProgressOnFinalAndOnSubscribe
import ru.mikhailskiy.intensiv.data.repository.CreditRemoteRepository
import ru.mikhailskiy.intensiv.data.repository.MovieDetailRemoteRepository
import ru.mikhailskiy.intensiv.data.vo.credit.CreditVo
import ru.mikhailskiy.intensiv.data.vo.movie.MovieDetailVo
import ru.mikhailskiy.intensiv.domain.useCase.MovieDetailAndCreditUseCase
import ru.mikhailskiy.intensiv.presentation.BaseFragment
import timber.log.Timber

class MovieDetailsFragment : BaseFragment() {

    private val args by navArgs<MovieDetailsFragmentArgs>()

    private val detailUseCase = MovieDetailAndCreditUseCase(MovieDetailRemoteRepository(), CreditRemoteRepository())

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

        if (args.movieId != -1) {
            compositeDisposable.add(getDetail())
        }

    }

    private fun getDetail() =
        detailUseCase.getDetail(args.movieId)
            .setProgressOnFinalAndOnSubscribe(detail_progress)
            .subscribe(
                {
                    movieDetailToView(it.movie)
                    creditsToView(it.credits)
                },
                {
                    Timber.e(it)
                }
            )

    private fun movieDetailToView(movieDetail: MovieDetailVo) {
        title_text?.text = movieDetail.title
        description_text?.text = movieDetail.overview
        genre_text?.text = movieDetail.genre?.map { it.name }?.joinToString()
        studio_text?.text = movieDetail.productionCompanies?.map { it.name }?.joinToString()
        year_text?.text = movieDetail.releaseDate

        movie_rating?.rating = movieDetail.rating

        movie_image.setImageFromBackend(movieDetail.backdropPath)

    }

    private fun creditsToView(list: List<CreditVo>) {
        actors_list?.adapter = actorAdapter.apply {
            addAll(list.map {
                ActorItem(it, this@MovieDetailsFragment::actorClicked)
            })
        }
    }

    private fun actorClicked(creditDto: CreditVo) {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieDetailsFragment()
    }
}
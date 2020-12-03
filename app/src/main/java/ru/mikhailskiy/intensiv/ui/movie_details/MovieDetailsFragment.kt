package ru.mikhailskiy.intensiv.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.movie_details_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.common.HorizontalSpaceDecoration
import ru.mikhailskiy.intensiv.data.credit.Credit
import ru.mikhailskiy.intensiv.data.credit.CreditsResponse
import ru.mikhailskiy.intensiv.data.movie.detail.MovieDetail
import ru.mikhailskiy.intensiv.extension.setImageFromBackend
import ru.mikhailskiy.intensiv.network.client.MovieApiClient
import timber.log.Timber

class MovieDetailsFragment : Fragment() {

    private val args by navArgs<MovieDetailsFragmentArgs>()

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
            getMovieDetail()
            getMovieCredits()
        }

    }

    private fun onFailure(t: Throwable) {
        Timber.e(t)
    }

    private fun progressVisible(isVisible: Boolean) {
        detail_progress.isVisible = isVisible
    }

    private fun getMovieDetail() {
        progressVisible(true)
        MovieApiClient.api.getDetail(args.movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    movieDetailToView(it)
                    progressVisible(false)
                },
                {
                    onFailure(it)
                    progressVisible(false)
                }
            )
    }

    private fun getMovieCredits() {
        MovieApiClient.api.getCredits(args.movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
    }

    private fun movieDetailToView(movieDetail: MovieDetail) {
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
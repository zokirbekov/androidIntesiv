package ru.mikhailskiy.intensiv.ui.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.movie_details_fragment.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.common.HorizontalSpaceDecoration
import ru.mikhailskiy.intensiv.data.actor.Actor
import ru.mikhailskiy.intensiv.data.movie.MovieDetail
import ru.mikhailskiy.intensiv.data.movie.MovieDetailMockRepository

class MovieDetailsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val actorAdapter:GroupAdapter<GroupieViewHolder> by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.movie_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actors_list?.addItemDecoration(HorizontalSpaceDecoration(32))

        val movieDetail = MovieDetailMockRepository.getDetail()
        movieDetailToView(movieDetail)
    }

    private fun movieDetailToView(movieDetail: MovieDetail)
    {
        title_text?.text = movieDetail.title
        description_text?.text = movieDetail.description
        genre_text?.text = movieDetail.genre
        studio_text?.text = movieDetail.studio
        year_text?.text = movieDetail.year

        Picasso.get().load(movieDetail.imageUrl).into(movie_image)

        movie_rating.rating = movieDetail.rating

        val imageId = when (movieDetail.movieQuality)
        {
            "4K" -> R.drawable.ic_4khd
            else -> R.drawable.ic_4khd
        }

        movie_quality_image?.setImageResource(imageId)

        actors_list?.adapter = actorAdapter.apply {
            addAll(movieDetail.actors.map {
                ActorItem(it, this@MovieDetailsFragment::actorClicked)
            })
        }
    }

    private fun actorClicked(actor: Actor)
    {

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MovieDetailsFragment()
    }
}
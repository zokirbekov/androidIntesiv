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
import ru.mikhailskiy.intensiv.data.actor.ActorMockRepository
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

        list_actors?.addItemDecoration(HorizontalSpaceDecoration(32))

        val movieDetail = MovieDetailMockRepository.getDetail()
        movieDetailToView(movieDetail)
    }

    private fun movieDetailToView(movieDetail: MovieDetail)
    {
        text_title?.text = movieDetail.title
        text_description?.text = movieDetail.description
        text_genre?.text = movieDetail.genre
        text_studio?.text = movieDetail.studio
        text_year?.text = movieDetail.year

        Picasso.get().load(movieDetail.imageUrl).into(image_movie)

        rating_movie.rating = movieDetail.rating

        val imageId = when (movieDetail.movieQuality)
        {
            "4K" -> R.drawable.ic_4khd
            else -> R.drawable.ic_4khd
        }

        image_moive_quality?.setImageResource(imageId)

        list_actors?.adapter = actorAdapter.apply {
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
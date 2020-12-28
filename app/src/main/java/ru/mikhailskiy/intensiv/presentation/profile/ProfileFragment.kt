package ru.mikhailskiy.intensiv.presentation.profile

import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.mikhailskiy.intensiv.R
import ru.mikhailskiy.intensiv.data.db.MovieDatabase
import ru.mikhailskiy.intensiv.data.repository.FavoriteMoviesRepositoryImpl
import ru.mikhailskiy.intensiv.data.vo.movie.MovieVo
import ru.mikhailskiy.intensiv.domain.useCase.FavoriteMovieUseCase
import ru.mikhailskiy.intensiv.presentation.BaseFragment
import ru.mikhailskiy.intensiv.presentation.watchlist.WatchlistFragment
import timber.log.Timber

class ProfileFragment : BaseFragment(), WatchlistFragment.FavoriteMoviesHelper {

    private lateinit var profileTabLayoutTitles: Array<String>

    private val favoriteMovieUseCase by lazy {
        FavoriteMovieUseCase(FavoriteMoviesRepositoryImpl(movieDao))
    }

    private val movieDao by lazy {
        MovieDatabase.get(requireContext()).movieDao()
    }

    private var profilePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Toast.makeText(
                requireContext(),
                "Selected position: $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(R.drawable.ic_avatar)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.ic_avatar)
            .into(avatar)

        profileTabLayoutTitles = resources.getStringArray(R.array.tab_titles)

        val profileAdapter = ProfileAdapter(
            this,
            profileTabLayoutTitles.size
        )
        doppelgangerViewPager.adapter = profileAdapter

        doppelgangerViewPager.registerOnPageChangeCallback(profilePageChangeCallback)

        TabLayoutMediator(tabLayout, doppelgangerViewPager) { tab, position ->

            // Выделение первой части заголовка таба
            // Название таба
            val title = profileTabLayoutTitles[position]
            // Раздеряем название на части. Первый элемент будет кол-во
            val parts = profileTabLayoutTitles[position].split(" ")
            val number = parts[0]
            val spannableStringTitle = SpannableString(title)
            spannableStringTitle.setSpan(RelativeSizeSpan(2f), 0, number.count(), 0)

            tab.text = spannableStringTitle
        }.attach()

        getCountOfFavorites()
    }

    fun getCountOfFavorites() = favoriteMovieUseCase
        .getCountOfFacorites()
        .subscribe(
            {
                tabLayout.getTabAt(0)?.text = it.toString()
            },
            {
                Timber.e(it)
            }
        )


    override fun navigateToDetail(movieVo: MovieVo) {
        val action = ProfileFragmentDirections.actionProfileFragmentToMovieDetailsFragment(movieVo.id?.toInt() ?: -1)
        findNavController().navigate(action)
    }

    override fun getFavoriteUseCase(): FavoriteMovieUseCase = favoriteMovieUseCase
}
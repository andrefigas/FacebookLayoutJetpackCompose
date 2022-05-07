package andrefigas.com.github.facebook.ui.entities

import andrefigas.com.github.facebook.R
import androidx.annotation.DrawableRes

class Story(@DrawableRes val avatarRes: Int, @DrawableRes val reelRes: Int, val name: String) {
    companion object {
        val data = listOf(
            Story(

                R.drawable.profile1,
                R.drawable.profile1_feed1,
                "Barney"
            ),
            Story(
                R.drawable.profile2,
                R.drawable.profile2_feed1,
                "Homer",
            ),
            Story(
                R.drawable.profile4,
                R.drawable.profile4_feed1,
                "Skinner",
            ),
            Story(
                R.drawable.profile5,
                R.drawable.profile5_feed1,
                "Moe",
            ),
            Story(
                R.drawable.profile6,
                R.drawable.profile6_feed1,
                "Apu",
            ),
            Story(
                R.drawable.profile7,
                R.drawable.profile7_feed1,
                "Milhouse",
            )
        )
    }
}
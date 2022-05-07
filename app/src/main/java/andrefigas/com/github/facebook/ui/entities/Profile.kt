package andrefigas.com.github.facebook.ui.entities

import andrefigas.com.github.facebook.R
import androidx.annotation.DrawableRes

data class Profile(@DrawableRes val avatarRes : Int){
    companion object{
        val data = Profile(R.drawable.profile3)
    }
}

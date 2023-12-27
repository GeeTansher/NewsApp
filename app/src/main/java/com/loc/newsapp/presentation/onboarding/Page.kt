package com.loc.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String,
    val desc: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = "On boarding 1",
        desc = "On Boarding 1 description.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "On boarding 2",
        desc = "On Boarding 2 description.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "On boarding 3",
        desc = "On Boarding 3 description.",
        image = R.drawable.onboarding3
    )
)
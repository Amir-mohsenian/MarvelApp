package com.example.marvelapp.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
@Composable
fun HomeScreen() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        HomeFeaturedList()
    }
}

@ExperimentalPagerApi
@Composable
fun HomeFeaturedList() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 5,
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)) {
    }
}
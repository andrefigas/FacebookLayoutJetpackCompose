package andrefigas.com.github.facebook

import andrefigas.com.github.facebook.ui.entities.FeedEntry
import andrefigas.com.github.facebook.ui.entities.Profile
import andrefigas.com.github.facebook.ui.entities.Story
import andrefigas.com.github.facebook.ui.theme.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppContent() {
    FacebookTheme {
        // A surface container using the 'background' color from the theme
        Surface() {
            LazyColumn {
                item { AppBar() }
                stickyHeader {
                    Column() {
                        TabNavigation()
                        Divider(color = GreyLight, thickness = 1.dp)
                    }
                }
                item { Publish() }
                item { PublishOptions() }
                item { Divider(color = GreyBlue, thickness = 6.dp) }
                item { StoriesTabNavigation() }
                item { Stories() }
                item { Divider(color = GreyBlue, thickness = 6.dp) }

                val feedEntries = FeedEntry.data
                items(feedEntries.size) { index ->
                    FeedEntryView(feedEntry = feedEntries[index])
                }
            }

        }
    }
}

@Composable
fun AppBar() {
    Card(backgroundColor = Color.White) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(5.dp)
        ) {
            Image(
                painterResource(R.drawable.text_logo),
                "logo",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 5.dp)
                    .width(120.dp),
                contentScale = ContentScale.FillWidth
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxSize()
            ) {
                AppBarIcon(iconRes = R.drawable.ic_search, iconDescription = "Search")
                AppBarIcon(iconRes = R.drawable.ic_messenger, iconDescription = "Messenger", 2)
            }

        }
    }
}

@Composable
fun AppBarIcon(@DrawableRes iconRes: Int, iconDescription: String, badge: Int = 0) {
    Box(modifier = Modifier.padding(start = 10.dp)) {
        Box() {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(GreyLight)

            ) {
                Image(
                    painterResource(iconRes),
                    iconDescription,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.FillWidth
                )
            }

            if (badge > 0) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(RedLight)
                        .align(Alignment.TopEnd)

                ) {
                    Text(
                        text = badge.toString(),
                        color = Color.White, fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }
    }
}


@Composable
fun TabNavigation() {
    TabRow(
        selectedTabIndex = 0,
        backgroundColor = Color.White,
        contentColor = Color.White,
        indicator = {
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(it[0]),
                color = MainBlue,
                height = TabRowDefaults.IndicatorHeight * 1.5F
            )
        }
    ) {
        TabItem(iconRes = R.drawable.ic_home, iconDescription = "Home", true)
        TabItem(iconRes = R.drawable.ic_tv, iconDescription = "Videos")
        TabItem(iconRes = R.drawable.ic_str, iconDescription = "Marketplace")
        TabItem(iconRes = R.drawable.ic_prfl, iconDescription = "Account")
        TabItem(iconRes = R.drawable.ic_bell, iconDescription = "Notifications")
        TabItem(iconRes = R.drawable.ic_hbgr, iconDescription = "Menu")
    }
}

@Composable
fun TabItem(@DrawableRes iconRes: Int, iconDescription: String, selected: Boolean = false) {
    Tab(
        selected = selected,
        onClick = { },
    ) {
        Image(
            painterResource(iconRes),
            iconDescription,
            modifier = Modifier
                .size(45.dp)
                .padding(7.5.dp),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(if (selected) MainBlue else GreyMedium)
        )
    }
}

@Composable
fun Publish() {

    Row(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
        ) {
            Image(
                painterResource(Profile.data.avatarRes),
                "profile",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .weight(8f)
                .fillMaxHeight()
                .border(width = 1.dp, color = GreyMedium, CircleShape)
                .align(CenterVertically)
        ) {
            Text(
                text = "What's on your mind?",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Center),
                color = GreyMedium,
                textAlign = TextAlign.Center
            )

        }

        Box(
            modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
        ) {
            Image(
                painterResource(R.drawable.ic_photos),
                "photos",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(45.dp)
                    .padding(10.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillWidth
            )
        }

    }
}

@Composable
fun PublishOptions() {

    Row(modifier = Modifier.background(GreySemitransparent)) {
        val weight = Modifier
            .weight(1f)
            .padding(vertical = 5.dp)
        PublishOptionItem(iconRes = R.drawable.ic_reel, label = "Reels", rowModifier = weight)
        PublishOptionItem(iconRes = R.drawable.ic_room, label = "Room", rowModifier = weight)
        PublishOptionItem(iconRes = R.drawable.ic_group, label = "Group", rowModifier = weight)
        PublishOptionItem(iconRes = R.drawable.ic_live, label = "Live", rowModifier = weight)
    }

}

@Composable
fun PublishOptionItem(@DrawableRes iconRes: Int, label: String, rowModifier: Modifier) {
    Box(modifier = rowModifier.padding(horizontal = 5.dp)) {
        Box(
            modifier = Modifier
                .height(36.dp)
                .fillMaxWidth()
                .clip(CircleShape)
                .background(Color.White)
                .border(width = 1.dp, color = GreyLight, CircleShape)
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxHeight()
            ) {
                Image(
                    painterResource(iconRes),
                    "Publish to $label",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(14.dp)
                        .align(CenterVertically)
                )

                Text(
                    text = label,
                    Modifier
                        .padding(horizontal = 5.dp)
                        .align(CenterVertically)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
            }
        }
    }

}


@Composable
fun StoriesTabNavigation() {
    TabRow(
        selectedTabIndex = 0,
        backgroundColor = Color.White,
        contentColor = Color.White,
        indicator = {
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(it[0]),
                color = MainBlue,
                height = TabRowDefaults.IndicatorHeight * 1.5F,
            )
        },
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        Tab(selected = true, onClick = { }) { Text(text = "Stories", color = MainBlue) }
        Tab(selected = false, onClick = { }) { Text(text = "Reels", color = MainBlue) }
        Tab(selected = false, onClick = { }) { Text(text = "Rooms", color = MainBlue) }
    }
}

@Composable
fun CreateYouStory() {
    Box(modifier = Modifier.padding(horizontal = 2.5.dp)) {
        Box(
            modifier = Modifier
                .height(250.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = GreyMedium, RoundedCornerShape(8.dp))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painterResource(Profile.data.avatarRes),
                    "Create story",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .background(Color.White)

                ) {
                    Text(
                        text = "Create story", color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(BottomCenter)
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .align(Center)
                    .padding(1.dp)

            ) {
                Image(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "Create story",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    colorFilter = ColorFilter.tint(MainBlue)
                )
            }

        }
    }
}

@Composable
fun Stories() {
    val stories = Story.data
    LazyRow(modifier = Modifier.padding(vertical = 12.dp)) {
        items(stories.size + 1) { index ->
            if (index == 0) CreateYouStory() else StoryItem(story = stories[index - 1])
        }
    }
}

@Composable
fun StoryItem(story: Story) {
    Box(modifier = Modifier.padding(horizontal = 2.5.dp)) {
        Box(
            modifier = Modifier
                .height(250.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = GreyMedium, RoundedCornerShape(8.dp))
        ) {

            Image(
                painterResource(story.reelRes),
                "${story.name}'s story",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = story.name, color = Color.White,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = MainBlue, CircleShape)

            ) {
                Box(modifier = Modifier.padding(2.dp)) {
                    Image(
                        painter = painterResource(story.avatarRes),
                        contentDescription = "${story.name}'s story",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedEntryPreview() {
    FeedEntryView(FeedEntry.data[0])
}

@Composable
fun FeedEntryView(feedEntry: FeedEntry) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        FeedEntryHeader(feed = feedEntry)

        //subtitle
        Text(
            text = feedEntry.subtitle, modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            color = GreyDark,
        )

        //content
        Image(
            painterResource(feedEntry.photoImgRes),
            "${feedEntry.profileName}'s photo'",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )
        //feedback
        FeedEntryFeedback(feed = feedEntry)
        //footer
        Divider(
            color = GreyLight,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
        )
        FeedEntryFooter()
        Divider(color = GreyBlue, thickness = 6.dp)
    }
}

@Composable
fun FeedEntryHeader(feed: FeedEntry) {
    Row(
        modifier = Modifier
            .height(45.dp)
            .padding(vertical = 2.5.dp)
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .weight(2f)
                .align(CenterVertically)
        ) {
            Image(
                painterResource(feed.profileImgRes),
                "${feed.profileName}'s avatar'",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .weight(8f)
                .fillMaxHeight()
        ) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Text(text = feed.profileName, fontWeight = FontWeight.Bold)

                Box(modifier = Modifier.padding(2.dp)) {
                    Box(
                        modifier = Modifier
                            .background(MainBlue, CircleShape)
                            .size(9.dp)
                            .padding(1.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_verified),
                            contentDescription = "verified",
                            colorFilter = ColorFilter.tint(Color.White),
                            modifier = Modifier
                                .align(Center)
                                .fillMaxSize()
                        )
                    }
                }

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "${feed.days}d", color = GreyDark)
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentDescription = "dot", colorFilter = ColorFilter.tint(GreyDark),
                    modifier = Modifier
                        .size(20.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(2.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_earth),
                    contentDescription = "public", colorFilter = ColorFilter.tint(GreyDark),
                    modifier = Modifier
                        .size(10.dp)
                )

            }


        }

        Image(
            painterResource(R.drawable.ic_more),
            "more",
            modifier = Modifier
                .height(50.dp)
                .weight(1.5f)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            contentScale = ContentScale.FillHeight
        )

        Image(
            painterResource(R.drawable.ic_close),
            "close",
            modifier = Modifier
                .height(50.dp)
                .weight(1.5f)
                .padding(horizontal = 10.dp, vertical = 5.dp),
            contentScale = ContentScale.FillHeight
        )

    }
}

@Composable
fun FeedEntryFooter() {
    Row(modifier = Modifier.height(40.dp)) {
        val modifier = Modifier.weight(1f)
        FeedEntryFooterOption(R.drawable.ic_like_outline, "Like", modifier)
        FeedEntryFooterOption(R.drawable.ic_comment, "Comment", modifier)
        FeedEntryFooterOption(R.drawable.ic_share_outline, "Share", modifier)
    }
}

@Composable
fun FeedEntryFooterOption(@DrawableRes iconRes: Int, label: String, rowModifier: Modifier) {
    Box(modifier = rowModifier.fillMaxHeight()) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .align(Center)
        ) {

            Box(modifier = Modifier.fillMaxHeight()) {
                Image(
                    painterResource(iconRes),
                    label,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .align(Center),
                    contentScale = ContentScale.FillHeight,
                    colorFilter = ColorFilter.tint(GreyMedium)
                )
            }

            Box(modifier = Modifier.fillMaxHeight()) {
                Text(
                    text = label, color = GreyMedium,
                    modifier = Modifier
                        .align(Center)
                        .padding(5.dp),
                    textAlign = TextAlign.Center
                )
            }


        }
    }

}

@Composable
fun FeedEntryFeedback(feed: FeedEntry) {
    Row(
        modifier = Modifier
            .height(40.dp)
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f), horizontalArrangement = Arrangement.Start
        ) {
            Box(modifier = Modifier.width(70.dp)) {

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(1.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape)
                        .align(CenterStart)
                ) {
                    Image(
                        painterResource(R.drawable.ic_emoticon_sad),
                        "emotion cry",
                        modifier = Modifier.size(30.dp),
                        contentScale = ContentScale.FillHeight
                    )
                }

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(1.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape)
                        .align(Center)
                ) {
                    Image(
                        painterResource(R.drawable.ic_emoticon_smiling),
                        "emotion smiling",
                        modifier = Modifier.size(30.dp),
                        contentScale = ContentScale.FillHeight
                    )
                }
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(1.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape)
                        .align(CenterEnd)
                ) {
                    Box(
                        modifier = Modifier
                            .size(22.dp)
                            .clip(CircleShape)
                            .background(RedLight)
                            .align(Alignment.Center)

                    ) {
                        Image(
                            painterResource(R.drawable.ic_heart),
                            "emotion smiling",
                            modifier = Modifier
                                .size(29.dp)
                                .padding(5.dp),
                            contentScale = ContentScale.FillHeight,
                            colorFilter = ColorFilter.tint(Color.White)
                        )
                    }


                }

            }

            Box(
                Modifier
                    .height(30.dp)
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = feed.reactions.toString(),
                    color = GreyDark,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Center)
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Box(
                Modifier
                    .height(30.dp)
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "${feed.comments} comments",
                    color = GreyDark,
                    modifier = Modifier.align(Center)
                )
            }

            Box(Modifier.height(30.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "dot", colorFilter = ColorFilter.tint(GreyDark),
                    modifier = Modifier
                        .size(20.dp)
                        .align(Center)
                )
            }

            Box(
                Modifier
                    .height(30.dp)
                    .padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "${feed.shares} shares",
                    color = GreyDark,
                    modifier = Modifier.align(Center)
                )
            }


        }

    }
}










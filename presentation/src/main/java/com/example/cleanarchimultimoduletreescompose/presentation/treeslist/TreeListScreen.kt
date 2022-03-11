package com.example.cleanarchimultimoduletreescompose.presentation.treeslist

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cleanarchimultimoduletreescompose.R
import com.example.cleanarchimultimoduletreescompose.presentation.destinations.TreeDetailScreenDestination
import com.example.domain.entities.TreeEntity
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ExperimentalMaterialApi
@Destination(
    start = true,
    route = "trees_screen")
@Composable
fun TreesListScreen(navigator: DestinationsNavigator) {
    val context = LocalContext.current
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(text = context.getString(R.string.trees_list),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp, bottom = 10.dp)
            )
            TreeList(navigator = navigator)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TreeList(navigator: DestinationsNavigator,
             viewModel: TreeListViewModel = hiltViewModel()
) {

    val loadError = viewModel.loadError.value  /* no need to use remember for viewModel variables */
    val isLoading = viewModel.isLoading.value
    val isOffline = viewModel.isOffline.value
    val treesList = viewModel.treesList

    val swipeRefreshState = rememberSwipeRefreshState(viewModel.isLoading.value)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.loadTreeList()
        },
    ) {
        Column {
            if (isOffline) {
                Text(
                    text = "You are currently offline",
                    color = MaterialTheme.colors.error,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
                items(items = treesList) { tree ->
                    val dismissState = rememberDismissState()

                    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                        LaunchedEffect(key1 = true, block = {
                            viewModel.removeTreeFromList(tree)
                            dismissState.snapTo(DismissValue.Default)
                        })
                    }
                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier
                            .padding(vertical = Dp(1f)),
                        directions = setOf(
                            DismissDirection.EndToStart
                        ),
                        dismissThresholds = { direction ->
                            FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                        },
                        background = {
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    DismissValue.Default -> Color.White
                                    else -> Color.Red
                                }
                            )
                            val alignment = Alignment.CenterEnd
                            val icon = Icons.Default.Delete

                            val scale by animateFloatAsState(
                                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                            )

                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = Dp(20f)),
                                contentAlignment = alignment
                            ) {
                                Icon(
                                    icon,
                                    contentDescription = "Delete Icon",
                                    modifier = Modifier.scale(scale)
                                )
                            }
                        },
                        dismissContent = {

                            Card(
                                elevation = animateDpAsState(
                                    if (dismissState.dismissDirection != null) 4.dp else 0.dp
                                ).value,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterVertically)
                            ) {
                                TreeCard(navigator = navigator, tree = tree)
                            }
                        }
                    )

                    Divider(Modifier.fillMaxWidth(), Color.DarkGray)
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        } else if(loadError.isNotEmpty() || viewModel.treesList.isNullOrEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadTreeList()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TreeCard(navigator: DestinationsNavigator, tree: TreeEntity) {
    val context = LocalContext.current
    Column(modifier = Modifier.clickable {
        navigator.navigate(TreeDetailScreenDestination(tree))
    }) {
        Divider(color = Color.Black, thickness = 1.dp)
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = context.getString(R.string.tree_type, tree.espece),
            color = MaterialTheme.colors.secondaryVariant
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = context.getString(R.string.tree_height, tree.hauteurenm.toString()),
            color = MaterialTheme.colors.secondaryVariant,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = context.getString(R.string.tree_circumference, tree.circonferenceencm.toString()),
            color = MaterialTheme.colors.secondaryVariant
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = context.getString(R.string.tree_address, tree.adresse),
            color = MaterialTheme.colors.secondaryVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}
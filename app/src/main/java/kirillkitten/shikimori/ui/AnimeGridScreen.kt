package kirillkitten.shikimori.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import kirillkitten.shikimori.R
import kirillkitten.shikimori.data.SearchQuery
import kirillkitten.shikimori.ui.components.AnimeGrid
import kirillkitten.shikimori.ui.components.InputChip
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnimeGridScreen(
    viewModel: AnimeGridViewModel,
    onAnimeClick: (Int) -> Unit
) {
    val animes = viewModel
        .animePagingFlow
        .collectAsLazyPagingItems()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.app_name))
            })
        },
        sheetContent = {
            FilterDialog()
        },
        sheetPeekHeight = 0.dp,
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                FilterPanel(query = SearchQuery.Default) {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
                AnimeGrid(
                    pagingItems = animes,
                    onClick = { (id) ->
                        onAnimeClick(id)
                    },
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    )
}

@Composable
fun FilterPanel(query: SearchQuery, onFilerClick: () -> Unit) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        InputChip(
            label = query.order.toString(),
            isSelected = true,
            onClear = { /*TODO*/ },
            onClick = {
                Timber.d("On filter chip clicked")
                onFilerClick()
            }
        )
        // Spacer(modifier = Modifier.width(16.dp))
        // InputChip(
        //     label = "Тип",
        //     onClear = { /*TODO*/ },
        //     onClick = { /*TODO*/ }
        // )
    }
}

@Composable
fun FilterDialog() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Hello World")
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterPanelPreview() {
    ShikimoriTheme {
        FilterPanel(query = SearchQuery.Default) {}
    }
}

@Preview
@Composable
private fun FilterDialogPreview() {
    ShikimoriTheme {
        FilterDialog()
    }
}

package kirillkitten.shikimori.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kirillkitten.shikimori.R
import kirillkitten.shikimori.data.Anime
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
            AnimeGridScreenContent(
                pagingItems = animes,
                modifier = Modifier.padding(innerPadding),
                onAnimeClick = onAnimeClick,
                onFilerClick = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun AnimeGridScreenContent(
    pagingItems: LazyPagingItems<Anime>,
    modifier: Modifier = Modifier,
    onAnimeClick: (Int) -> Unit,
    onFilerClick: () -> Unit,
) {
    Column(modifier) {
        FilterPanel(query = SearchQuery.Default, onFilerClick = onFilerClick)
        AnimeGrid(
            pagingItems = pagingItems,
            onClick = { (id) ->
                onAnimeClick(id)
            }
        )
    }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 12.dp)
    ) {
        // Header
        Text(
            text = "Сортировать по",
            style = MaterialTheme.typography.h6
        )

        // Filters
        // TODO Replace by string resources
        val filterOptions = listOf("Рейтингу", "Популярности", "Алфавиту", "Дате выхода")
        val (selectedOption, onOptionSelected) = rememberSaveable {
            mutableStateOf(filterOptions[0])
        }
        Column(Modifier.selectableGroup().padding(top = 16.dp)) {
            filterOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption), onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        // Apply Button
        Button(
            onClick = {
                /*TODO*/
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Сохранить")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterPanelPreview() {
    ShikimoriTheme {
        FilterPanel(query = SearchQuery.Default) {}
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterDialogPreview() {
    ShikimoriTheme {
        FilterDialog()
    }
}

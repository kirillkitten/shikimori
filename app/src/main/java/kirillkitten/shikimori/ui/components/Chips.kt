package kirillkitten.shikimori.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kirillkitten.shikimori.ui.theme.ShikimoriTheme
import kirillkitten.shikimori.ui.theme.outlineColor

/**
 * Filter chip that follows Material design guidelines.
 * Filter chip is a selectable word that is used together with other chips to filter content.
 * Key parameters that must be provided is chip [label] and [onSelectedChange] callback.
 * [isSelected] is optional and it is false by default.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean = false,
    onSelectedChange: (Boolean) -> Unit,
) {
    var selected by rememberSaveable { mutableStateOf(isSelected) }
    Surface(
        onClick = {
            selected = !selected
            onSelectedChange(selected)
        },
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        color = if (!selected) {
            MaterialTheme.colors.surface
        } else {
            MaterialTheme.colors.secondaryVariant
        },
        contentColor = if (!selected) {
            MaterialTheme.colors.onSurface
        } else {
            MaterialTheme.colors.onSecondary
        },
        border = if (!selected) BorderStroke(width = 1.dp, color = outlineColor()) else null
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .height(32.dp)
                .padding(horizontal = 12.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputChip(
    label: String,
    isSelected: Boolean = false,
    onClear: () -> Unit,
    onClick: () -> Unit,
) {
    var selected by rememberSaveable { mutableStateOf(isSelected) }
    Surface(
        onClick = {
            onClick()
        },
        shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
        color = if (!selected) {
            MaterialTheme.colors.surface
        } else {
            MaterialTheme.colors.secondaryVariant
        },
        contentColor = if (!selected) {
            MaterialTheme.colors.onSurface
        } else {
            MaterialTheme.colors.onSecondary
        },
        border = if (!selected) BorderStroke(width = 1.dp, color = outlineColor()) else null
    ) {
        Row(
            modifier = Modifier
                .height(32.dp)
                .padding(start = 12.dp, end = if (!selected) 12.dp else 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                modifier = Modifier.widthIn(max = 80.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2,
            )
            if (selected) {
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        selected = false
                        onClear()
                    },
                    modifier = Modifier.size(18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "", // TODO
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.Bottom)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFilterChip() {
    ShikimoriTheme {
        FilterChip(label = "Ongoing", onSelectedChange = {}, isSelected = false)
    }
}

@Preview
@Composable
private fun PreviewFilterChipSelected() {
    ShikimoriTheme {
        FilterChip(label = "Released", onSelectedChange = {}, isSelected = true)
    }
}

@Preview
@Composable
private fun PreviewInputChip() {
    ShikimoriTheme {
        InputChip(label = "Type", onClick = {}, onClear = {})
    }
}

@Preview
@Composable
private fun PreviewInputChipSelected() {
    ShikimoriTheme {
        InputChip(
            label = "TV, Movie, OVA, ONA, Special",
            isSelected = true,
            onClick = {},
            onClear = {}
        )
    }
}


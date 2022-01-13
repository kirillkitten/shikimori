package kirillkitten.shikimori.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kirillkitten.shikimori.ui.theme.outlineColor

/**
 * Filter chip that follows Material design guidelines.
 * Filter chip is a selectable word that is used together with other chips to filter content.
 * Key parameters that must be provided is chip [label] and [onSelectedChange] callback.
 * [initialSelection] is optional and it is false by default.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChip(
    label: String,
    onSelectedChange: (Boolean) -> Unit,
    initialSelection: Boolean = false,
) {
    var selected by rememberSaveable { mutableStateOf(initialSelection) }
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

@Preview
@Composable
private fun PreviewFilterChip() {
    FilterChip(label = "Ongoing", onSelectedChange = {}, initialSelection = false)
}

@Preview
@Composable
private fun PreviewFilterChipSelected() {
    FilterChip(label = "Released", onSelectedChange = {}, initialSelection = true)
}

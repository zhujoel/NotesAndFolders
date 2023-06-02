package notesandfolders.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.wear.compose.material.*
import com.example.notesandfolders.R
import notesandfolders.presentation.component.FolderContent


@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    swipeDismissibleNavController: NavHostController,
    context: Context
) {
    val listState = rememberScalingLazyListState()
    
    ScalingLazyColumn(
        modifier = modifier,
        state = listState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item { FolderContent(context, modifier, iconModifier, stringResource(R.string.landing_screen_id), swipeDismissibleNavController) }
    }
}

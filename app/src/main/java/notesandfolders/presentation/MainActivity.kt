package notesandfolders.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.wear.compose.material.*
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import notesandfolders.entities.AppDatabase
import notesandfolders.presentation.navigation.FolderScreen
import notesandfolders.presentation.navigation.LandingScreen
import notesandfolders.presentation.navigation.NoteScreen
import notesandfolders.presentation.navigation.Screen
import notesandfolders.presentation.theme.NotesAndFoldersTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    internal lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var context = applicationContext
        setContent {
            /*
            val contentModifier = Modifier.fillMaxWidth()
            val iconModifier = Modifier
                .size(24.dp)
                .wrapContentSize(align = Alignment.Center)
            */
            navController = rememberSwipeDismissableNavController()

            WearApp(context, swipeDismissableNavController = navController)
        }
    }
}

@Composable
fun WearApp(
    context: Context,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    swipeDismissableNavController: NavHostController = rememberSwipeDismissableNavController()
) {
    NotesAndFoldersTheme {
        val listState = rememberScalingLazyListState()

        Scaffold(
            timeText = { TimeText(modifier = Modifier.scrollAway(listState)) },
            vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
            positionIndicator = { PositionIndicator(scalingLazyListState = listState) },
        ) {
            SwipeDismissableNavHost(
                navController = swipeDismissableNavController,
                startDestination = Screen.Landing.route
            ) {
                // Main Window
                composable(
                    route = Screen.Landing.route
                ) {
                    LandingScreen(
                        modifier,
                        iconModifier,
                        swipeDismissableNavController,
                        context)
                }

                /** Routes */
                // Folder Screen
                composable(
                    route = Screen.Folder.route + "/{folderId}",
                    arguments = listOf(
                        navArgument("folderId") {
                            type = NavType.StringType
                            nullable = false
                        }
                    )
                ) {
                    entry ->
                        FolderScreen(context, modifier, iconModifier, UUID.fromString(entry.arguments?.getString("folderId")), swipeDismissableNavController)
                }

                // Note Screen
                composable(
                    route = Screen.Note.route + "/{noteId}",
                    arguments = listOf(
                        navArgument("noteId") {
                            type = NavType.StringType
                            nullable = false
                        }
                    )
                ) {
                    entry ->
                        NoteScreen(context, modifier, UUID.fromString(entry.arguments?.getString("noteId")))
                }
            }
        }
    }
}
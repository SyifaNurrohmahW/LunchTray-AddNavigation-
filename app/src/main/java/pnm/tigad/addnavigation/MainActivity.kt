package pnm.tigad.addnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pnm.tigad.addnavigation.ui.theme.themes.AddNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddNavigationTheme {
                LunchTrayApp()
            }
        }
    }
}
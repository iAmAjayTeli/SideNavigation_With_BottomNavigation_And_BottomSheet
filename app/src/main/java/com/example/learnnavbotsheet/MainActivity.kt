package com.example.learnnavbotsheet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learnnavbotsheet.ui.theme.GreenJc
import com.example.learnnavbotsheet.ui.theme.LearnNavBotSheetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnNavBotSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      LearnNavBotSheet()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnNavBotSheet() {
    val navigatonController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(300.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GreenJc)
                        .height(150.dp)
                ) {
                    Text(text = "Header")
                }
                Divider()
                NavigationDrawerItem(label = { Text(text = "Home", color = GreenJc) },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home",
                            tint = GreenJc
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigatonController.navigate(Screens.Home.screen) {
                            popUpTo(0)
                        }
                    })

                NavigationDrawerItem(label = { Text(text = "Profile", color = GreenJc) },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = GreenJc
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigatonController.navigate(Screens.Profile.screen) {
                            popUpTo(0)
                        }
                    })

                NavigationDrawerItem(label = { Text(text = "Setting", color = GreenJc) },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = GreenJc
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        navigatonController.navigate(Screens.Setting.screen) {
                            popUpTo(0)
                        }
                    })

                NavigationDrawerItem(label = { Text(text = "Logout", color = GreenJc) },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Logout",
                            tint = GreenJc
                        )
                    },
                    onClick = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                        Toast.makeText(context, "logout", Toast.LENGTH_SHORT).show()
                    })
            }
        }) {

        Scaffold(
            topBar = {
                val coroutineScope = rememberCoroutineScope()
                TopAppBar(title = { Text(text = "Whatsapp") },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = GreenJc,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomAppBar(containerColor = GreenJc) {
                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Home
                            navigatonController.navigate(Screens.Home.screen) {
                                popUpTo(0)
                            }

                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                Icon(Icons.Default.Home, contentDescription =null, modifier = Modifier.size(26.dp),
                    tint = if(selected.value==Icons.Default.Home) Color.White else Color.DarkGray)
                    }

                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Search
                            navigatonController.navigate(Screens.Search.screen) {
                                popUpTo(0)
                            }

                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon(Icons.Default.Search, contentDescription =null, modifier = Modifier.size(26.dp),
                            tint = if(selected.value==Icons.Default.Search) Color.White else Color.DarkGray)
                    }
                    
                    Box(modifier = Modifier
                        .weight(1f)
                        .padding(16.dp), contentAlignment = Alignment.Center){
                        FloatingActionButton(onClick = {showBottomSheet=true  }) {
                            Icon(Icons.Default.Add, contentDescription =null )
                        }
                    }

                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Notifications
                            navigatonController.navigate(Screens.Notification.screen) {
                                popUpTo(0)
                            }

                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon(Icons.Default.Notifications, contentDescription =null, modifier = Modifier.size(26.dp),
                            tint = if(selected.value==Icons.Default.Notifications) Color.White else Color.DarkGray)
                    }

                    IconButton(
                        onClick = {
                            selected.value = Icons.Default.Person
                            navigatonController.navigate(Screens.Profile.screen) {
                                popUpTo(0)
                            }

                        },
                        modifier = Modifier.weight(1f)
                    )
                    {
                        Icon(Icons.Default.Person, contentDescription =null, modifier = Modifier.size(26.dp),
                            tint = if(selected.value==Icons.Default.Person) Color.White else Color.DarkGray)
                    }
                }
            }
        )
        {paddingValues ->
           NavHost(navController = navigatonController, modifier = Modifier.padding(paddingValues), startDestination = Screens.Home.screen ){
               composable(Screens.Home.screen){ Home()}
               composable(Screens.Profile.screen){ Profiles() }
               composable(Screens.Notification.screen){ Notifications()}
               composable(Screens.Search.screen){ Search() }
               composable(Screens.Setting.screen){ Settings() }
               composable(Screens.Post.screen){ Post() }
           }

        }

        if(showBottomSheet){
            ModalBottomSheet(onDismissRequest = { showBottomSheet=false }, sheetState = sheetState) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp))
                {
                    BottomSheetItem(icons = Icons.Default.ThumbUp, title ="Create a post" ) {
                        showBottomSheet=false
                        navigatonController.navigate(Screens.Post.screen){
                            popUpTo(0)
                        }
                    }

                    BottomSheetItem(icons = Icons.Default.Star, title ="Add a Story" ) {
                        showBottomSheet=false
                        Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()
                    }

                    BottomSheetItem(icons = Icons.Default.PlayArrow, title ="Create a Reel" ) {
                        showBottomSheet=false
                        Toast.makeText(context, "Reel", Toast.LENGTH_SHORT).show()
                    }

                    BottomSheetItem(icons = Icons.Default.Favorite, title ="Go Live" ) {
                        showBottomSheet=false
                        Toast.makeText(context, "Live", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetItem(icons:ImageVector, title:String, onClick:()->Unit ){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp),
       modifier = Modifier.clickable { onClick() } )
    {
      Icon(icons, contentDescription = null)
        Text(text = title, color = GreenJc, fontSize = 22.sp)
    }
}

@Preview
@Composable
fun Preview(){
    LearnNavBotSheetTheme {
        LearnNavBotSheet()
    }
}
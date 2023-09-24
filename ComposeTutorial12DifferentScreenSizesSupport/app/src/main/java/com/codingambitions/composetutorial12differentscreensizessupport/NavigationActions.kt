package com.codingambitions.composetutorial12differentscreensizessupport

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


//common tabs, rail or drawer navigation logic added after tutorial
class NavigationActions(private val navController: NavHostController) {

    fun navigateTo(screen: Screen) {
        navController.navigate(screen.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items

            //to avoid multiple entries of same route on stack

            // findStartDestination -> Finds the actual start destination of the graph,
            // handling cases where the graph's starting destination is itself a NavGraph(nested navigation)

            // And popUpTo :-  clears the back stack and the state of all
            // destinations between the current destination and the NavOptionsBuilder.popUpTo ID
            // But if we use saveState = true, it will save that state( back stack and the state of all
            // destinations between the current destination and the NavOptionsBuilder.popUpTo ID)
            // before performing it performs popUpTo for clearing backstack entries upto popUpTo ID,
            // and later it restore that backstack if we use restoreState = true
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }


            // Avoid multiple copies of the same destination when
            // reselecting the same item
            // IF we comment out this, even then also we are not getting multiple copies of same route,
            // becoz popUpTo is clearing all copies
            launchSingleTop =
                true //this will work in combination with restoreState, if we disable this,
            // we need to disable restoreState as well

            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}
package com.example.bubbleblizz.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.bubbleblizz.ui.ThemeViewModel
import com.example.bubbleblizz.ui.screen.*
import com.example.bubbleblizz.ui.screen.RegisterScreen
import com.example.bubbleblizz.ui.screen.LoginScreen
import com.example.bubbleblizz.ui.screen.CheckoutPaymentScreen
import com.example.bubbleblizz.ui.screen.CheckoutReviewScreen



object Route {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val MENU = "menu"
    const val DETAILS = "details/{id}"
    const val CART = "cart"
    const val FAVORITES = "favorites"
    const val PROFILE = "profile"
    const val EDIT_PROFILE = "edit_profile"
    const val CHECKOUT_SHIP = "checkout_ship"
    const val CHECKOUT_PAY = "checkout_pay"
    const val CHECKOUT_REVIEW = "checkout_review"
    const val ORDER_DETAILS = "order_details"
}

@Composable
fun AppNav(modifier: Modifier = Modifier, themeVm: ThemeViewModel) {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Route.SPLASH,
        modifier = modifier
    ) {
        composable(Route.SPLASH) {
            SplashScreen(
                onDone = {
                    nav.navigate(Route.LOGIN) {
                        popUpTo(Route.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Route.LOGIN) {
            LoginScreen(
                onLogin = {
                    nav.navigate(Route.HOME) {
                        popUpTo(Route.LOGIN) { inclusive = true }
                    }
                },
                onRegister = { nav.navigate(Route.REGISTER) },
                onBack = { nav.popBackStack() }
            )
        }

        composable(Route.REGISTER) {
            RegisterScreen(
                onRegistered = {
                    nav.navigate(Route.HOME) {
                        popUpTo(Route.REGISTER) { inclusive = true }
                    }
                },
                onBack = { nav.popBackStack() }
            )
        }

        composable(Route.HOME) {
            HomeScreen(
                onMenu = { category ->
                    // Navigate to menu screen with selected category
                    nav.navigate("menu/$category")
                },
                onOrders = { },
                onProfile = { nav.navigate(Route.PROFILE) },
                onFavorites = { nav.navigate(Route.FAVORITES) },
                onCart = { nav.navigate(Route.CART) },
                onBack = { nav.popBackStack() },
                onDetails = { id -> nav.navigate("details/$id") }

            )
        }


        // Menu Screen (with category argument)
        composable(
            route = "menu/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: "Fruit Juice"
            MenuScreen(
                categoryName = category,  // sends category name to MenuScreen
                onProduct = { id -> nav.navigate("details/$id") },
                onCart = { nav.navigate(Route.CART) },
                onDetails = { id -> nav.navigate("details/$id") },
                onBack = { nav.popBackStack() }
            )
        }


        composable(
            Route.DETAILS,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            ProductDetailsScreen(
                id = id,
                onBack = { nav.popBackStack() },
                onGoHome = { nav.navigate(Route.HOME) { popUpTo(Route.HOME) {} } },
                onGoCart = { nav.navigate(Route.CART) },
                onGoFavorites = { nav.navigate(Route.FAVORITES) },
                onGoSettings = { nav.navigate(Route.PROFILE) }
            )
        }

        composable(Route.CART) {
            CartScreen(
                onCheckout = { nav.navigate(Route.CHECKOUT_SHIP) },
                onBack = { nav.popBackStack() },
                onGoHome = { nav.navigate(Route.HOME) { popUpTo(Route.HOME) {} } },
                onGoFavorites = { nav.navigate(Route.FAVORITES) },
                onGoSettings = { nav.navigate(Route.PROFILE) }
            )
        }

        composable(Route.FAVORITES) {
            FavoritesScreen(
                onBack = { nav.popBackStack() },
                onGoHome = { nav.navigate(Route.HOME) { popUpTo(Route.HOME) {} } },
                onGoCart = { nav.navigate(Route.CART) },
                onGoSettings = { nav.navigate(Route.PROFILE) }
            )
        }

        composable(Route.PROFILE) {
            ProfileScreen(
                onBack = { nav.popBackStack() },
                onMyAccount = { nav.navigate(Route.EDIT_PROFILE) },
                onLogout = {
                    nav.navigate(Route.LOGIN) {
                        popUpTo(Route.HOME) { inclusive = true }
                    }
                },
                themeVm = themeVm
            )
        }

        composable(Route.EDIT_PROFILE) {
            EditProfileScreen(onBack = { nav.popBackStack() })
        }

        composable(Route.CHECKOUT_SHIP) {
            CheckoutShippingScreen(
                onBack = { nav.popBackStack() },
                onNext = { nav.navigate(Route.CHECKOUT_PAY) }
            )
        }

        composable(Route.CHECKOUT_PAY) {
            CheckoutPaymentScreen(
                onBack = { nav.popBackStack() },
                onNext = { nav.navigate(Route.CHECKOUT_REVIEW) }
            )
        }

        composable(Route.CHECKOUT_REVIEW) {
            CheckoutReviewScreen(
                onBack = { nav.popBackStack() },
                onFinish = { nav.navigate(Route.ORDER_DETAILS) }
            )
        }

        composable(Route.ORDER_DETAILS) {
            OrderDetailsScreen(
                onBack = { nav.popBackStack() },
                onBackToCart = {
                    nav.navigate(Route.CART) {
                        popUpTo(Route.CART) { inclusive = true }
                    }
                }
            )
        }
    }
}

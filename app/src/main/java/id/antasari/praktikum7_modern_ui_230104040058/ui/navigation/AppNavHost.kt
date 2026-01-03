// app/src/main/java/id/antasari/praktikum7_modern_ui_230104040058/ui/navigation/AppNavHost.kt
package id.antasari.praktikum7_modern_ui_230104040058.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.antasari.praktikum7_modern_ui_230104040058.AccountStorage
import id.antasari.praktikum7_modern_ui_230104040058.CreateAccountScreen
import id.antasari.praktikum7_modern_ui_230104040058.HomeScreen
import id.antasari.praktikum7_modern_ui_230104040058.LoginScreen
import id.antasari.praktikum7_modern_ui_230104040058.SecurityDetailsScreen
import id.antasari.praktikum7_modern_ui_230104040058.SettingsScreen
import id.antasari.praktikum7_modern_ui_230104040058.ui.auth.AuthViewModel

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val SECURITY_DETAILS = "security_details"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    onBiometricClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by authViewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Auto redirect saat status login berubah
    LaunchedEffect(uiState.isSignedIn) {
        if (uiState.isSignedIn) {
            navController.navigate(Routes.HOME) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        } else {
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.LOGIN) { inclusive = true }
            }
        }
    }

    val displayName = uiState.name.ifBlank { "User" }

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN,
        modifier = modifier
    ) {

        // ================= LOGIN =================
        composable(Routes.LOGIN) {
            LoginScreen(
                uiState = uiState,
                onEmailChange = authViewModel::onEmailChange,
                onPasswordChange = authViewModel::onPasswordChange,
                onSignInClick = {
                    authViewModel.signInWithPassword(onSuccess = {})
                },
                onForgotPasswordClick = { /* TODO */ },
                onCreateAccountClick = {
                    navController.navigate(Routes.REGISTER)
                },
                onBiometricClick = {
                    onBiometricClick()
                }
            )
        }

        // ================= REGISTER =================
        composable(Routes.REGISTER) {
            CreateAccountScreen(
                onSignUpClick = { name, email, password ->
                    authViewModel.onNameChange(name)
                    authViewModel.onEmailChange(email)
                    authViewModel.onPasswordChange(password)
                    authViewModel.onConfirmPasswordChange(password)

                    val success = authViewModel.createAccount()
                    if (success) {
                        val current = authViewModel.uiState.value
                        AccountStorage.saveAccount(
                            context = context,
                            name = current.name,
                            email = current.registeredEmail ?: current.email,
                            password = current.registeredPassword ?: current.password,
                            biometricEnabled = current.isBiometricEnabled,
                            isDarkTheme = current.isDarkTheme,
                            appLockEnabled = current.appLockEnabled,
                            loginAlertsEnabled = current.loginAlertsEnabled,
                            newDeviceAlertsEnabled = current.newDeviceAlertsEnabled,
                            publicWifiWarningEnabled = current.publicWifiWarningEnabled
                        )
                        onBiometricClick()
                    }
                },
                onSignInClick = {
                    navController.popBackStack()
                }
            )
        }

        // ================= HOME =================
        composable(Routes.HOME) {
            HomeScreen(
                userName = displayName,
                onLogoutClick = {
                    authViewModel.signOut()
                },
                onOpenSecurityDetailsClick = {
                    navController.navigate(Routes.SECURITY_DETAILS)
                },
                onOpenSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        // ================= SECURITY DETAILS =================
        composable(Routes.SECURITY_DETAILS) {
            SecurityDetailsScreen(
                userName = displayName,
                onBackClick = { navController.popBackStack() }
            )
        }

        // ================= SETTINGS =================
        composable(Routes.SETTINGS) {
            SettingsScreen(
                userName = displayName,
                isBiometricEnabled = uiState.isBiometricEnabled,
                appLockEnabled = uiState.appLockEnabled,
                isDarkTheme = uiState.isDarkTheme,
                loginAlertsEnabled = uiState.loginAlertsEnabled,
                newDeviceAlertsEnabled = uiState.newDeviceAlertsEnabled,
                publicWifiWarningEnabled = uiState.publicWifiWarningEnabled,

                onBiometricToggle = { enabled ->
                    authViewModel.setBiometricEnabled(enabled)
                    val current = authViewModel.uiState.value
                    if (current.registeredEmail != null) {
                        AccountStorage.saveAccount(
                            context,
                            current.name,
                            current.registeredEmail ?: current.email,
                            current.registeredPassword ?: current.password,
                            current.isBiometricEnabled,
                            current.isDarkTheme,
                            current.appLockEnabled,
                            current.loginAlertsEnabled,
                            current.newDeviceAlertsEnabled,
                            current.publicWifiWarningEnabled
                        )
                    }
                    if (enabled) onBiometricClick()
                },

                onAppLockToggle = { enabled ->
                    authViewModel.setAppLockEnabled(enabled)
                },
                onDarkThemeToggle = { enabled ->
                    authViewModel.setDarkTheme(enabled)
                },
                onLoginAlertsToggle = { enabled ->
                    authViewModel.setLoginAlertsEnabled(enabled)
                },
                onNewDeviceAlertsToggle = { enabled ->
                    authViewModel.setNewDeviceAlertsEnabled(enabled)
                },
                onPublicWifiWarningToggle = { enabled ->
                    authViewModel.setPublicWifiWarningEnabled(enabled)
                },

                onDeleteAccountClick = {
                    authViewModel.clearAccount()
                    AccountStorage.clearAccount(context)
                    navController.navigate(Routes.REGISTER) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

object Compose {
    const val composeCompilerVersion = "1.3.2"
    private const val bomVersion = "2022.10.00"
    const val material = "androidx.compose.material:material"
    const val ui = "androidx.compose.ui:ui"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val runtime = "androidx.compose.runtime:runtime"
    const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"

    private const val navigationVersion = "2.5.2"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"

    private const val activityComposeVersion = "1.6.0"
    const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"

    private const val lifecycleVersion = "2.5.1"
    const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"
    const val composeBom = "androidx.compose:compose-bom:$bomVersion"
}
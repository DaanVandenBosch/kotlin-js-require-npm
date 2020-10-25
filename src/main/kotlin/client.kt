import kotlinx.browser.window

fun main() {
    window.onload = { initGoldenLayout() }
}

fun initGoldenLayout() {
    // Requiring these CSS files works with ./gradlew :browserDevelopmentRun --continuous.
    // It doesn't while testing with ./gradlew :browserTest
    js("""require("golden-layout/src/css/goldenlayout-base.css");""")
    js("""require("golden-layout/src/css/goldenlayout-light-theme.css");""")

    val config: GoldenLayout.Config = newJsObject {
        // Create a stack with 3 tabs.
        content = arrayOf(
            newJsObject {
                type = "stack"
                content = arrayOf(
                    newJsObject<GoldenLayout.ComponentConfig> {
                        type = "component"
                        title = "Tab 1"
                        componentName = "tab"
                    },
                    newJsObject<GoldenLayout.ComponentConfig> {
                        type = "component"
                        title = "Tab 2"
                        componentName = "tab"
                    },
                    newJsObject<GoldenLayout.ComponentConfig> {
                        type = "component"
                        title = "Tab 3"
                        componentName = "tab"
                    },
                )
            }
        )
    }

    val goldenLayout = GoldenLayout(config)

    // Will be called for each tab we created above.
    goldenLayout.registerComponent("tab") { container: GoldenLayout.Container ->
        container.getElement().append("Contents of tab.")
    }

    goldenLayout.init()
}

fun <T> newJsObject(block: T.() -> Unit): T =
    js("{}").unsafeCast<T>().apply(block)

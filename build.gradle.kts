plugins {
    kotlin("js") version "1.5.21"
}

group = "com.daanvandenbosch"
version = "1.0-SNAPSHOT"

tasks.wrapper {
    gradleVersion = "7.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(npm("golden-layout", "1.5.9"))
    testImplementation(kotlin("test-js"))
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
            binaries.executable()
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
}

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

enum class AppTarget(val configure: KotlinMultiplatformExtension.() -> Unit) {
    Android(configure = {
        androidTarget {
            compilations.all {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }
        }
    }),
    IosX64(configure = { iosX64() }),
    IosSimulator(configure = { iosSimulatorArm64() }),
    IosArm64(configure = { iosArm64() }),
    Ios(configure = { allIos() })
}

private fun KotlinMultiplatformExtension.allIos() {
    AppTarget.IosX64.configure(this)
    AppTarget.IosSimulator.configure(this)
    AppTarget.IosArm64.configure(this)
}
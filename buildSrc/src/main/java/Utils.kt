import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension


val Project.multiplatformExtension: KotlinMultiplatformExtension
    get() = kotlinExtension as KotlinMultiplatformExtension

fun Project.androidLibrary(configure: LibraryExtension.() -> Unit) {
    configure<LibraryExtension>(configure)
}
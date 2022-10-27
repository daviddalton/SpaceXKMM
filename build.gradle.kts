buildscript {
    val compose_version by extra("1.1.0-beta01")
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.2.1").apply(false)
    id("com.android.library").version("7.2.1").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
//    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

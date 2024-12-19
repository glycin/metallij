import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.2.0"
}

group = "com.glycin"
version = "0.0.1"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

intellijPlatform  {
    pluginConfiguration {
        id = "metallij"
        name = "METALLIJ"
        version = "0.0.1"
        description = "The most metal plugin to have ever existed..."

        ideaVersion {
            sinceBuild = "232"
        }

        vendor {
            name = "Glycin"
            url = "https://github.com/glycin"
        }
    }


    publishing {}

    signing{}
}

dependencies {
    intellijPlatform{
        intellijIdeaCommunity("2024.2.3")
        bundledPlugin("com.intellij.java")
        pluginVerifier()
        zipSigner()
        testFramework(TestFrameworkType.Platform)
    }

    testImplementation("junit:junit:4.13.2")
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "23"
        targetCompatibility = "23"
    }
}

kotlin{
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_23)
    }
}

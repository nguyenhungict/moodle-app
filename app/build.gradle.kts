plugins {
    id("com.android.application")
    // nếu sau này dùng Kotlin thì bật thêm:
    // id("org.jetbrains.kotlin.android")
}

android {
    namespace = "vn.edu.usth.moodleapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "vn.edu.usth.moodleapp"
        minSdk = 22
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }

    buildToolsVersion = "34.0.0"
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    implementation("com.google.android.material:material:1.9.0") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.3")
    implementation("androidx.navigation:navigation-ui:2.7.3")
    implementation("androidx.activity:activity:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Firebase Dependencies
    implementation(platform("com.google.firebase:firebase-bom:33.1.2")) // Sử dụng phiên bản BoM mới nhất
    implementation("com.google.firebase:firebase-auth")
    // implementation("com.google.firebase:firebase-firestore")
    // implementation("com.google.firebase:firebase-database")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
}

// ép tất cả dependency Kotlin về 1 version duy nhất
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.jetbrains.kotlin") {
            useVersion("1.8.22")
        }
    }
}

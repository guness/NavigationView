a NavigationView which supports also Footer as well as Header. 
It is based on design.NavigationView.
Footer and Header are steady and do not scroll.

**how to install**

*root build.gradle*

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

*app/build.gradle*

    dependencies {
        ...
        implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:$kotlin_version"
        implementation 'com.android.support:design:27.0.2'
        implementation 'com.github.guness:NavigationView:{currentVersion}@aar'
    }        
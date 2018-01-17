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

*example usage*

        <com.guness.widget.NavigationView
            android:id="@+id/nav_view"
            style="@style/AppTheme.PopupOverlay.Dark"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:background="@drawable/background_side"
            android:fitsSystemWindows="true"
            app:footerLayout="@layout/nav_footer_main"
            app:headerLayout="@layout/nav_header_main"
            app:itemIconTint="@color/nav_icon_color"
            app:itemTextColor="@color/white"
            app:menu="@menu/drawer_main" />
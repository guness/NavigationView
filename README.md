a NavigationView which supports also Footer as well as Header. 
It is based on design.NavigationView.
Footer and Header are steady and do not scroll.
Also it supports video background via R.raw.* resource, or via setting uri on runtime.

#Requirements
The library requires Android **API Level 16+**.

#How to install

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
        implementation 'com.github.guness:NavigationView:{currentVersion}'
    }

#Example usage

        <com.guness.widget.NavigationView
            android:id="@+id/nav_view"
            style="@style/AppTheme.PopupOverlay.Dark"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:background="@drawable/background_side"
            app:backgroundVideo="@raw/background_video"
            android:fitsSystemWindows="true"
            app:footerLayout="@layout/nav_footer_main"
            app:headerLayout="@layout/nav_header_main"
            app:itemIconTint="@color/nav_icon_color"
            app:itemTextColor="@color/white"
            app:menu="@menu/drawer_main" />
            

Note `android:background` and `app:backgroundVideo` may not work well together, use wisely.
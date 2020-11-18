package org.apache.cordova.inappbrowser;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

public class InAppBrowserToolbarUtil {

    public static FrameLayout createCustomToolbarView(
            Activity activity,
            ViewGroup parent,
            String title,
            View.OnClickListener onClose
    ) {
        Resources res = activity.getResources();
        int toolbarLayoutRes = res.getIdentifier(
                "in_app_browser_toolbar",
                "layout",
                activity.getPackageName());
        FrameLayout customToolbar = (FrameLayout) LayoutInflater.from(parent.getContext())
                .inflate(toolbarLayoutRes, parent, false);

        View closeButton = customToolbar.getChildAt(0);
        if (closeButton != null) closeButton.setOnClickListener(onClose);
        TextView titleView = (TextView) customToolbar.getChildAt(1);
        if (titleView != null) titleView.setText(title);

        return customToolbar;
    }
}

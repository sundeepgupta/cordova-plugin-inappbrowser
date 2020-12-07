package org.apache.cordova.inappbrowser;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class InAppBrowserToolbarUtil {

    public static final String TAG = "IAB_UTIL";

    public static FrameLayout createCustomToolbarView(
            Activity activity,
            ViewGroup parent,
            String title,
            int backgroundColor,
            int foregroundColor,
            View.OnClickListener onClose
    ) {
        Resources res = activity.getResources();
        int toolbarLayoutRes = res.getIdentifier(
                "in_app_browser_toolbar",
                "layout",
                activity.getPackageName());

        FrameLayout customToolbar = (FrameLayout) LayoutInflater.from(parent.getContext())
                .inflate(toolbarLayoutRes, parent, false);
        customToolbar.setBackgroundColor(backgroundColor);

        ImageView closeButton = (ImageView) customToolbar.getChildAt(0);
        if (null != closeButton) {
            closeButton.setOnClickListener(onClose);
            closeButton.setColorFilter(foregroundColor);
        }

        TextView titleView = (TextView) customToolbar.getChildAt(1);
        if (null != titleView) {
            titleView.setText(title);
            titleView.setTextColor(foregroundColor);
            titleView.setTypeface(getBrandTypeface(activity.getApplicationContext()));
        }

        return customToolbar;
    }

    private static Typeface getBrandTypeface(Context context) {
        Typeface typeface = getTypeface(context, "fonts/brand.ttf");
        if (null != typeface) return typeface;

        typeface = getTypeface(context, "fonts/brand.otf");
        if (null != typeface) return typeface;

        Log.d(TAG, "No brand font found. Using system font.");
        return Typeface.DEFAULT_BOLD;
    }

    private static Typeface getTypeface(
            Context context,
            String fileName
    ) {
        try {
            return Typeface.createFromAsset(context.getAssets(), fileName);
        } catch (Throwable t) {
            // Ignored
        }
        return null;
    }
}

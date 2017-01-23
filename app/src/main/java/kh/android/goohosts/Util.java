package kh.android.goohosts;

import android.app.Activity;
import android.view.WindowManager;

import com.android.setupwizardlib.SetupWizardLayout;

/**
 * Created by liangyuteng0927 on 17-1-23.
 * Email: liangyuteng12345@gmail.com
 */

class Util {
    static void initActivityAndWizardLayout (SetupWizardLayout layout, Activity activity) {
        layout.setIllustration(activity.getDrawable(R.drawable.illustration_generic));
        layout.setBackgroundTile(R.drawable.illustration_tile);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}

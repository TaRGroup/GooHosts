package kh.android.goohosts;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.android.setupwizardlib.SetupWizardLayout;
import com.android.setupwizardlib.view.NavigationBar;

/**
 * Created by liangyuteng0927 on 17-1-20.
 * Email: liangyuteng12345@gmail.com
 */

public class MainActivity extends Activity implements NavigationBar.NavigationBarListener, View.OnClickListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetupWizardLayout mSetupWizardLayout = (SetupWizardLayout)findViewById(R.id.wizard);
        mSetupWizardLayout.setHeaderText(R.string.title_welcome);
        mSetupWizardLayout.getNavigationBar().setNavigationBarListener(this);
        Util.initActivityAndWizardLayout(mSetupWizardLayout, this);
        findViewById(R.id.button_hosts_url).setOnClickListener(this);
        findViewById(R.id.button_source_url).setOnClickListener(this);
    }

    @Override
    public void onNavigateBack() {
        onBackPressed();
    }

    @Override
    public void onNavigateNext() {
        startActivity(new Intent(this, LicenceActivity.class));
    }

    private void startUrl (String url) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (ActivityNotFoundException ignore) {}
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_hosts_url :
                startUrl("https://github.com/racaljk/hosts");
                break;
            case R.id.button_source_url :
                startUrl("https://github.com/TaRGroup/GooHosts");
                break;
        }
    }
}

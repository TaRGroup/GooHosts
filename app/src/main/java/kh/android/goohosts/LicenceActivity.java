package kh.android.goohosts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.android.setupwizardlib.SetupWizardLayout;
import com.android.setupwizardlib.view.NavigationBar;

/**
 * Created by liangyuteng0927 on 17-1-23.
 * Email: liangyuteng12345@gmail.com
 */

public class LicenceActivity extends Activity implements NavigationBar.NavigationBarListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licence);
        final SetupWizardLayout layout = (SetupWizardLayout)findViewById(R.id.wizard);
        layout.setHeaderText(R.string.title_licence);
        layout.getNavigationBar().setNavigationBarListener(this);
        layout.getNavigationBar().getNextButton().setVisibility(View.GONE);
        Util.initActivityAndWizardLayout(layout, this);
        CheckBox checkBox = (CheckBox)findViewById(R.id.check_agree_licence);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    layout.getNavigationBar().getNextButton().setVisibility(View.VISIBLE);
                else
                    layout.getNavigationBar().getNextButton().setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onNavigateBack() {
        finish();
    }

    @Override
    public void onNavigateNext() {
        finish();
        startActivity(new Intent(this, WorkingActivity.class));
    }
}

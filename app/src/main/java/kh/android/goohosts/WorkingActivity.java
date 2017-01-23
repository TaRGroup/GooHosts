package kh.android.goohosts;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.setupwizardlib.SetupWizardLayout;
import com.android.setupwizardlib.view.NavigationBar;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.FileOutputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liangyuteng0927 on 17-1-23.
 * Email: liangyuteng12345@gmail.com
 */

public class WorkingActivity extends Activity implements NavigationBar.NavigationBarListener{
    private SetupWizardLayout mLayout;
    private WorkingTask mTask;
    private TextView mTextStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
        mLayout = (SetupWizardLayout)findViewById(R.id.wizard);
        mLayout.getNavigationBar().setNavigationBarListener(this);
        mLayout.setHeaderText(R.string.title_apply);
        mTextStatus = (TextView)findViewById(R.id.status);
        mLayout.getNavigationBar().getBackButton().setVisibility(View.GONE);
        Util.initActivityAndWizardLayout(mLayout, this);
        mTask = new WorkingTask();
        mTask.execute();
    }

    @Override
    public void onNavigateBack() {
        onBackPressed();
    }

    @Override
    public void onNavigateNext() {
        finish();
    }

    @Override
    public void onBackPressed () {
        if (mTask != null && !mTask.isCancelled()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
        if (mTask != null)
            mTask.cancel(true);
    }

    private class WorkingTask extends AsyncTask<Void, Object, Object> {
        @Override
        protected void onProgressUpdate(Object... values) {
        }

        @Override
        protected Object doInBackground(Void... params) {
            try {
                RootTools.getShell(true);
            } catch (Exception e) {
                return new Exception(getString(R.string.err_no_root) + "\n"
                 + e.getMessage());
            }
            try {
                Request request = new Request.Builder()
                        .url("https://raw.githubusercontent.com/racaljk/hosts/master/hosts")
                        .build();
                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();
                File download = new File(getCacheDir().getAbsolutePath() + "/cache");
                download.delete();
                download.createNewFile();
                FileOutputStream stream = new FileOutputStream(download);
                stream.write(response.body().bytes());
                stream.close();
                RootTools.copyFile(download.getAbsolutePath(), "/system/etc/hosts", true, false);
                download.delete();
                return null;
            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPreExecute () {
            mLayout.getNavigationBar().setVisibility(View.GONE);
            mLayout.showProgressBar();
            mLayout.setHeaderText(R.string.title_apply);
            mTextStatus.setText("");
        }
        @Override
        protected void onPostExecute (Object o) {
            mLayout.getNavigationBar().setVisibility(View.VISIBLE);
            mLayout.hideProgressBar();
            if (o != null) {
                if (o instanceof Exception) {
                    mLayout.setHeaderText(R.string.title_err);
                    mTextStatus.setText(((Exception)o).getMessage());
                }
            } else {
                mLayout.setHeaderText(R.string.title_success);
                mTextStatus.setText(R.string.text_success);
            }
        }
    }
}

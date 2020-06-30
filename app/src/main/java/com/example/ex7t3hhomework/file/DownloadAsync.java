package com.example.ex7t3hhomework.file;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.ex7t3hhomework.R;

public class DownloadAsync extends AsyncTask<String , Void, String> {
    public Context context;
    public Dialog dialog;

    public DownloadAsync(Context context) {
        this.context = context;
        dialog = new Dialog(context, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.dialog_progress_loading);
        dialog.setCancelable(false);
    }

    @Override
    protected String doInBackground(String... strings) {
        FileManager manager = new FileManager();
        return manager.download(strings[0]);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

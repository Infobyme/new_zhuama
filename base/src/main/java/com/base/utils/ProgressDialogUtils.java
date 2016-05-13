package com.base.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.base.R;

/**
 * Created by tongyang on 16/5/13.
 */
public class ProgressDialogUtils {


    private static ProgressDialogUtils ps;
    private ProgressDialog progressDialog=null;

    public static ProgressDialogUtils getInstance() {
        if (ps == null) {
            ps = new ProgressDialogUtils();
        }
        return ps;
    }


    public  void show(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getResources().getString(R.string.app_loading));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();
    }

    public void diss()
    {
        if (progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

}

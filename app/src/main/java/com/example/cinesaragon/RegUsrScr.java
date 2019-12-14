package com.example.cinesaragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.cinesaragon.domain.GetSessions;
import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.FullInfoSession;

import java.util.List;

import javax.inject.Inject;

public class RegUsrScr extends BaseActivity {

    @Inject
    GetSessions getSessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_usr_scr);
        getSessions.get(0L, new ResultCallback<List<FullInfoSession>, Exception>(this) {
            @Override
            public void doOnResult(List<FullInfoSession> result) {

            }

            @Override
            public void doOnError(Exception error) {

            }
        });
    }

    public static void openRegisterScreen(Activity activity) {
        final Intent intent = new Intent(activity, RegUsrScr.class);
        activity.startActivity(intent);
    }
}

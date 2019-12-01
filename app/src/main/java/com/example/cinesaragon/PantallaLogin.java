package com.example.cinesaragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinesaragon.domain.LoginUser;
import com.example.cinesaragon.domain.RegisterUser;
import com.example.cinesaragon.domain.helpers.ResultCallback;

import javax.inject.Inject;

public class PantallaLogin extends BaseActivity {

    @Inject
    RegisterUser registerUser;
    @Inject
    LoginUser loginUser;

    EditText username;
    EditText password;
    Button login;
    View signIn;

    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);
        initializeViews();
    }

    private void initializeViews() {
        this.isLogin = getIntent().getExtras().getBoolean(EXTRA_LOGIN);

        username = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtContrasenya);
        login = findViewById(R.id.btnLogin);

        login.setText(isLogin ? R.string.btnLogin : R.string.btnRegistrar);
        login.setOnClickListener(view -> {
            if (isLogin) {
                doLogin();
            } else {
                doRegister();
            }
        });
        signIn = findViewById(R.id.btnAlta);
        signIn.setVisibility(isLogin ? View.VISIBLE : View.GONE);
        signIn.setOnClickListener(view -> {
            openRegister(this);
        });
    }

    private void doRegister() {
        registerUser.execute(this,
                username.getText().toString(),
                password.getText().toString(),
                new ResultCallback<Void, Exception>() {
                    @Override
                    public void onResult(Void result) {
                        UserScr.goToProfile(PantallaLogin.this);
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(PantallaLogin.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void doLogin() {
        loginUser.execute(this,
                username.getText().toString(),
                password.getText().toString(),
                new ResultCallback<Void, Exception>() {
                    @Override
                    public void onResult(Void result) {
                        UserScr.goToProfile(PantallaLogin.this);
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(PantallaLogin.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void openLogin(Activity activity) {
        activity.startActivity(createIntent(activity, true));
    }

    public static void openRegister(Activity activity) {
        activity.startActivity(createIntent(activity, false));
    }

    private static Intent createIntent(Activity activity, boolean mode) {
        final Intent intent = new Intent(activity, PantallaLogin.class);
        intent.putExtra(EXTRA_LOGIN, mode);
        return intent;
    }

    private static final String EXTRA_LOGIN = "extra_login";


}

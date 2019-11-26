package com.example.cinesaragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public class PantallaLogin extends BaseActivity {

    @Inject
    FirebaseAuth authentication;

    EditText username;
    EditText password;
    Button login;
    View signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_login);
        username = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtContrasenya);
        login = findViewById(R.id.btnLogin);

        final boolean isLogin = getIntent().getExtras().getBoolean(EXTRA_LOGIN);

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
        onResult(authentication.createUserWithEmailAndPassword(
                username.getText().toString(),
                password.getText().toString()));
    }

    private void doLogin() {
        onResult(authentication.signInWithEmailAndPassword(
                username.getText().toString(),
                password.getText().toString()));
    }

    @NotNull
    private Task<AuthResult> onResult(Task<AuthResult> authResultTask) {
        return authResultTask
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Go to Cartelera?
                    } else {
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

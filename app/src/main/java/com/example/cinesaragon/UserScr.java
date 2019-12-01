package com.example.cinesaragon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinesaragon.domain.GetCurrentUser;
import com.example.cinesaragon.domain.ModifyCurrentUser;
import com.example.cinesaragon.domain.helpers.ResultCallback;
import com.example.cinesaragon.model.User;

import javax.inject.Inject;

public class UserScr extends BaseActivity {

    @Inject
    GetCurrentUser getCurrentUser;
    @Inject
    ModifyCurrentUser modifyCurrentUser;

    EditText name;
    EditText lastName;
    EditText phone;
    EditText address;
    EditText email;
    EditText card;
    EditText paypal;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_scr);
        initializeViews();
        requestUser();
    }

    private void initializeViews() {
        this.name = findViewById(R.id.edtNombre);
        this.lastName = findViewById(R.id.edtApellidos);
        this.phone = findViewById(R.id.edtTelefono);
        this.address = findViewById(R.id.edtDireccion);
        this.email = findViewById(R.id.edtEmail);
        this.card = findViewById(R.id.edtNumeroTarjeta);
        this.paypal = findViewById(R.id.edtPayPal);
        this.save = findViewById(R.id.btnGuardarPerfil);
        this.save.setOnClickListener(view -> saveUser());
    }

    private void setInitialContent(User user) {
        this.name.setText(user.getName());
        this.lastName.setText(user.getName());
        this.phone.setText(user.getPhone());
        this.address.setText(user.getAddress());
        this.email.setText(user.getEmail());
        this.card.setText(user.getCard());
        this.paypal.setText(user.getPaypal());
    }

    private void requestUser() {
        getCurrentUser.retrieve(new ResultCallback<User, Exception>() {
            @Override
            public void onResult(User result) {
                setInitialContent(result);
            }

            @Override
            public void onError(Exception error) {

            }
        });
    }

    private void saveUser() {
        modifyCurrentUser.modify(this,
                new User.Builder(this.email.getText().toString())
                        .setAddress(this.address.getText().toString())
                        .setCard(this.card.getText().toString())
                        .setLastName(this.lastName.getText().toString())
                        .setName(this.name.getText().toString())
                        .setPaypal(this.paypal.getText().toString())
                        .setPhone(this.phone.getText().toString())
                        .build(),
                new ResultCallback<Void, Exception>() {
                    @Override
                    public void onResult(Void result) {
                        Toast.makeText(UserScr.this, "Modifications saved successfully.",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception error) {
                        Toast.makeText(UserScr.this, "Could not save",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void goToProfile(Activity activity) {
        final Intent intent = new Intent(activity, UserScr.class);
        activity.startActivity(intent);
    }
}

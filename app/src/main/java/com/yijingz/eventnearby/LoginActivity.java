package com.yijingz.eventnearby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private static final int REQUEST_SIGNUP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDataBase = FirebaseDatabase.getInstance().getReference();
        mUsernameEditText = findViewById(R.id.editTextLogin);
        mPasswordEditText = findViewById(R.id.editTextPassword);
        mLoginButton = findViewById(R.id.login);
        mRegisterLink = findViewById(R.id.register);
        mLoginAttempts = 0;

        mRegisterLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivityForResult(intent,REQUEST_SIGNUP);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = mUsernameEditText.getText().toString();
                final String password = Utils.md5Encryption(mPasswordEditText.getText().toString());

                mDataBase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange (DataSnapshot snapshot) {
                        if (snapshot.hasChild(username) &&
                                password.equals(snapshot.child(username).child("password").getValue())) {
                            Log.i("admin", "You have successfully logged in.");
                            onLoginSuccess(username);
                        } else {
                            if (mLoginAttempts > 10) {
                                Toast.makeText(getBaseContext(), "You have tried too many times. Please restart the app.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), "Your username and password does not match. Please try again.",
                                        Toast.LENGTH_SHORT).show();
                                mLoginAttempts++;
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError e) {

                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                Bundle mbundle = data.getExtras();
                String username = mbundle.getString("username");
                onLoginSuccess(username);
            }
        }
    }

    protected void onLoginSuccess(String username) {
        Intent intent = new Intent(LoginActivity.this, EventActivity.class);
        Utils.username = username;
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private DatabaseReference mDataBase;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private TextView mRegisterLink;
    private Integer mLoginAttempts;
}

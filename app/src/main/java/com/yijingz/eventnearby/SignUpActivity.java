package com.yijingz.eventnearby;

import android.content.Intent;
import android.os.Handler;
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

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDataBase = FirebaseDatabase.getInstance().getReference();
        mUsernameEditText = findViewById(R.id.editTextSignup);
        mPasswordEditText = findViewById(R.id.editTextPasswordAttempt);
        mSubmitButton = findViewById(R.id.submit);

        mSubmitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String username = mUsernameEditText.getText().toString();
                final String password = mPasswordEditText.getText().toString();
                final String encrpytedPassword = Utils.md5Encryption(mPasswordEditText.getText().toString());
                final User user = new User(username,encrpytedPassword, System.currentTimeMillis());

                mDataBase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(username)) {
                            Toast.makeText(getBaseContext(),"The username that you requested has been taken, please try again.",
                                    Toast.LENGTH_SHORT).show();
                        } else if (isValid(username,password)){
                            mDataBase.child("users").child(user.getUsername()).setValue(user);
                            Toast.makeText(getBaseContext(), "Successfully registered. In 3 seconds, you will be logged in automatically.", Toast.LENGTH_SHORT).show();
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    onSignUpSuccess(username);
                                }
                            }, 3000);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            private void onSignUpSuccess(String username) {
                Intent intent = getIntent();
                intent.putExtra("username",username);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private boolean isValid(String username, String password) {
        if (username.isEmpty() || username.length() < 1) {
            Toast.makeText(getBaseContext(), "Username must be at least 1 character long.",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty() || password.length() < 5 ) {
            Toast.makeText(getBaseContext(),
                    "Password must be at least 5 character long.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private DatabaseReference mDataBase;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private Button mSubmitButton;
}

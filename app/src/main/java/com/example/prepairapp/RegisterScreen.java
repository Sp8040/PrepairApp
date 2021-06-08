package com.example.prepairapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends AppCompatActivity {

    Button signUp;
    EditText firstName, secondName, email, password, repeatPassword;
    TextView goToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        firstName = findViewById(R.id.FirstName);
        secondName = findViewById(R.id.SecondName);
        email = findViewById(R.id.EmailSignUp);
        password = findViewById(R.id.PasswordSignUp);
        repeatPassword = findViewById(R.id.RepeatPassword);
        goToSignIn = findViewById(R.id.goToSignIn);
        signUp = findViewById(R.id.SignUpButton);

        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(firstName.getText().toString()) || TextUtils.isEmpty(secondName.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(RegisterScreen.this, "Fields are empty", Toast.LENGTH_LONG).show();
                }
                else{
                    if(password.getText().toString().equals(repeatPassword.getText().toString())){
                        RegisterRequest registerRequest = new RegisterRequest();
                        registerRequest.setFirstName(firstName.getText().toString());
                        registerRequest.setLastName(secondName.getText().toString());
                        registerRequest.setEmail(email.getText().toString());
                        registerRequest.setPassword(password.getText().toString());
                        registerUser(registerRequest);
                    }
                    else{
                        Toast.makeText(RegisterScreen.this, "Confirm password!", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }

    public void  registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterScreen.this, "Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
                }
                else{
                    Toast.makeText(RegisterScreen.this, "Response isn't successful! Try again later.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterScreen.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
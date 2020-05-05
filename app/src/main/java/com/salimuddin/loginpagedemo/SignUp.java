package com.salimuddin.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText youName, emailId, userName, userPassword;
    private Button signUpbtn;
    UserDetails userDetails;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        youName = findViewById(R.id.NameEditTextd);
        emailId = findViewById(R.id.EmailEditTextID);
        userName = findViewById(R.id.UserNameEditTextId);
        userPassword = findViewById(R.id.PassEditTextId);
        signUpbtn = findViewById(R.id.SignUpBtn);

        userDetails = new UserDetails();
        myDatabaseHelper = new MyDatabaseHelper(this);

        signUpbtn.setOnClickListener(this);

    }

    public void smallLoginp(View view) {
        Intent slp = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(slp);
    }

    public void goback(View view) {
        Intent sb = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(sb);
    }

    @Override
    public void onClick(View v) {

        String name = youName.getText().toString();
        String email = emailId.getText().toString();
        String username = userName.getText().toString();
        String userpassword = userPassword.getText().toString();


        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(userpassword);

        long rowId = myDatabaseHelper.insetData(userDetails);

        if (rowId>0){
            Toast.makeText(getApplicationContext(), "Successfully Created Your Account", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

        youName.setText("");
        emailId.setText("");
        userName.setText("");
        userPassword.setText("");

    }
}

package com.salimuddin.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //Adding Valuables
    private EditText youName, emailId, userName, userPassword;
    private Button signUpbtn;
    UserDetails userDetails;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initialize  Valuables
        youName = findViewById(R.id.NameEditTextd);
        emailId = findViewById(R.id.EmailEditTextID);
        userName = findViewById(R.id.UserNameEditTextId);
        userPassword = findViewById(R.id.PassEditTextId);
        signUpbtn = findViewById(R.id.SignUpBtn);

        //Creating Object of UserDetails Class
        userDetails = new UserDetails();

        //Creating Object of MyDatabaseHelper Class
        myDatabaseHelper = new MyDatabaseHelper(this);

        //Set Listener on Buttons
        signUpbtn.setOnClickListener(this);

    }

    public void smallLoginp(View view) {

        //Top Login Butoon
        Intent slp = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(slp);
    }

    public void goback(View view) {
        //Top Back button
        Intent sb = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(sb);
    }

    @Override
    public void onClick(View v) {

        //EditText ot String
        String name = youName.getText().toString();
        String email = emailId.getText().toString();
        String username = userName.getText().toString();
        String userpassword = userPassword.getText().toString();


        //Passing String to UserDetails class
        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(userpassword);

        //Return Value of MyDatabaseHelper class . insertData method
        long rowId = myDatabaseHelper.insetData(userDetails);

        if (rowId>0){

            //Success Massage by Toast
            Toast.makeText(getApplicationContext(), "Successfully Created Your Account", Toast.LENGTH_SHORT).show();
        }else {
            //Error Massage by Toast
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

        //Clear EditText Data
        youName.setText("");
        emailId.setText("");
        userName.setText("");
        userPassword.setText("");

    }
}

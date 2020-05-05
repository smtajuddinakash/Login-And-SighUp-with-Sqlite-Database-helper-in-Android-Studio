package com.salimuddin.loginpagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText UserName, PassWord;
    private Button LoginBtn, SignUppageBtn;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        UserName = findViewById(R.id.userLoginId);
        PassWord = findViewById(R.id.passLoginID);
        LoginBtn = findViewById(R.id.logInBtn);
        SignUppageBtn = findViewById(R.id.signUpPageBtn);

        LoginBtn.setOnClickListener(this);
        SignUppageBtn.setOnClickListener(this);




    }

    public void smallSignupp(View view) {
        Intent ssup = new Intent(getApplicationContext(), SignUp.class);
        startActivity(ssup);
    }

    @Override
    public void onClick(View v) {

        String username = UserName.getText().toString();
        String password = PassWord.getText().toString();

        if (v.getId()==R.id.logInBtn){

            Boolean result = myDatabaseHelper.findPassword(username, password);
            if (result==true){
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Username and Password didn't match", Toast.LENGTH_SHORT).show();
            }

        }
        else if (v.getId()==R.id.signUpPageBtn){
            Intent signuppage = new Intent(getApplicationContext(), SignUp.class);
            startActivity(signuppage);
        }


    }
}

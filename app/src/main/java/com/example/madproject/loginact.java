package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginact extends AppCompatActivity{


    private EditText logingEmail;
    private EditText logingPass;
    private TextView signup;
    private Button bt,logingbtn;
    private ProgressBar logbar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);

         mAuth = FirebaseAuth.getInstance();

        logingEmail =(EditText) findViewById(R.id.editText5);
        logingPass =(EditText) findViewById(R.id.editText6);
        signup = (TextView)findViewById(R.id.textView35);
        bt = (Button)findViewById(R.id.button17) ;
        logingbtn=(Button)findViewById(R.id.button9);
        logbar=(ProgressBar)findViewById(R.id.progressBarlog) ;
        /*signup.setOnClickListener(this);
        bt.setOnClickListener(this);*/

        logingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginEmailText =logingEmail.getText().toString();
                String loginPassText=logingPass.getText().toString();

                if(!TextUtils.isEmpty(loginEmailText) && !TextUtils.isEmpty(loginPassText)){
                    logbar.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmailText,loginPassText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                                sendToMain();
                           }
                           else{

                               String e = task.getException().getMessage();
                               Toast.makeText(loginact.this,"Error :" + e, Toast.LENGTH_LONG).show() ;

                           }
                            logbar.setVisibility(View.INVISIBLE);
                        }
                    });



                }

            }
        });

    }






    /*@Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()){
            case R.id.textView35:i=new Intent(this,signup.class);startActivity(i);break;
            case R.id.button17:i=new Intent(this,profile.class);startActivity(i);break;
            default:break;


        }
    }*/



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
           /* Intent mainIntent= new Intent(loginact.this,MainActivity.class);
            startActivity(mainIntent);
            finish();*/
           sendToMain();
        }
    }

    private void sendToMain() {
        Intent mainIntent= new Intent(loginact.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}

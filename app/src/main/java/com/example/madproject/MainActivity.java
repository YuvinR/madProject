package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener {


    private CardView work,holiday,timetable,community;
    private Button login,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        work=(CardView)findViewById(R.id.cardId1);
        holiday=(CardView)findViewById(R.id.cardId2);
        timetable=(CardView)findViewById(R.id.cardId3);
        community=(CardView)findViewById(R.id.cardId4);
        login=(Button)findViewById(R.id.button3);
        logout=(Button)findViewById(R.id.button18);
        work.setOnClickListener(this);
        holiday.setOnClickListener(this);
        timetable.setOnClickListener(this);
        community.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch(view.getId())
        {
            case R.id.cardId1 : i=new Intent(this,workoutHomepage.class);startActivity(i);break;

            case R.id.cardId2 : i=new Intent(this,Holiday_UI1.class);startActivity(i);break;

            case R.id.cardId3 : i=new Intent(this,timetable.class);startActivity(i);break;

            case R.id.cardId4 : i=new Intent(this,Community.class);startActivity(i);break;

            case R.id.button3 : i=new Intent(this,loginact.class);startActivity(i);break;

            case R.id.button18: FirebaseAuth.getInstance().signOut();
                                finish();
                                i=new Intent(this,loginact.class);
                                startActivity(i);

            default:break;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            /*Intent intent = new Intent(MainActivity.this,loginact.class);
            startActivity(intent);
            finish();*/
            logout.setVisibility(View.INVISIBLE);
            login.setVisibility(View.VISIBLE);
        } else{
            logout.setVisibility(View.VISIBLE);
            login.setVisibility(View.INVISIBLE);
            // No user is signed in
        }
    }
}

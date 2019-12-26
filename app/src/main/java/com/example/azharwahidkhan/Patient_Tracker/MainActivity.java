package com.example.azharwahidkhan.Patient_Tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseauth;
    private FirebaseAuth.AuthStateListener statelistener;
    public static final int RC_SIGN_IN = 1;

    // Intent i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  setSupportActionBar(toolbar);

        firebaseauth = FirebaseAuth.getInstance();


        //auth state listener
        statelistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser(); //firebaseAuth takes sign_in or sign_out params.

                if(user != null)
                {
                    //sign-in
                    // Toast.makeText(getApplicationContext(), "You're now signed in. Rent It !", Toast.LENGTH_LONG).show();
                }
                else
                {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }

            }
        };



        //my code
        TextView tv1 = (TextView) findViewById(R.id.md);
        tv1.setOnClickListener(new View.OnClickListener()
                               {
                                   @Override
                                   public void onClick(View view) {
                                       Intent i1 = new Intent(getApplicationContext(),Details.class);
                                       startActivity(i1);
                                   }

                               }

        );

        TextView tv2 = (TextView) findViewById(R.id.dentist);
        tv2.setOnClickListener(new View.OnClickListener()
                               {
                                   @Override
                                   public void onClick(View view) {
                                       Intent i2 = new Intent(getApplicationContext(),Details_2.class);
                                       startActivity(i2);
                                   }

                               }

        );


        //end

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
       */
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "You're now signed in", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                //  .setIcon(android.R.drawable.)
                .setTitle("Patient Tracker")
                .setMessage("Are you sure you want to EXIT ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AuthUI.getInstance().signOut(this);
            Toast.makeText(this, "Visit Again", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();
        firebaseauth.addAuthStateListener(statelistener);


    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseauth.removeAuthStateListener(statelistener);
    }
}

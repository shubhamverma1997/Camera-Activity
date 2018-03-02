package com.example.shubham.nextvac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN=9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        final GoogleSignInClient mGoogleSignInClient=GoogleSignIn.getClient(this,gso);

        SignInButton mSignInButton=findViewById(R.id.sign_in_button);
        mSignInButton.setSize(SignInButton.SIZE_STANDARD);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent=mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent,RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //if this is not null , update the ui accordingly
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInRequest(task);
        }
    }

    private void handleSignInRequest(Task<GoogleSignInAccount> completedTask)
    {
        try {
            GoogleSignInAccount account=completedTask.getResult(ApiException.class);
            //Update the UI
            updateUI();
        }
        catch (ApiException e)
        {
         //Not Successfull
            Log.w("TAG","Sign in Failed : "+e.getStatusCode());
            //updateUI();
            //getEmail()
            //getId()
        }
    }
    public void updateUI()
    {
        SignInButton mSignInButton=findViewById(R.id.sign_in_button);
        mSignInButton.setVisibility(View.GONE);
        Intent intent=new Intent(getBaseContext(),CameraActivity.class);
        startActivity(intent);
    }
}

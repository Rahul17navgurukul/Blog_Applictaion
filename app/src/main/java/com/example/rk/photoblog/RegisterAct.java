package com.example.rk.photoblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterAct extends AppCompatActivity {
    private EditText Regusername;
    private EditText RegPass;
    private EditText RegConfirm;
    private Button signin_btn;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        Regusername = findViewById(R.id.RegUsername);
        RegPass =findViewById(R.id.RegPass);
        RegConfirm = findViewById(R.id.RegConfirm);

        progressDialog = new ProgressDialog(this);
        signin_btn = findViewById(R.id.sign_btn);

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartSigning();
            }
        });



    }

    private void StartSigning() {

        String username = Regusername.getText().toString();
        String password = RegPass.getText().toString();
        String confirmpassword = RegConfirm.getText().toString();

        progressDialog.setMessage("Please wait we making your account");
        progressDialog.setTitle("Singnig");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


//        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) & TextUtils.isEmpty(confirmpassword)){

            if (password.equals(confirmpassword)){

                mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
//
                            Intent setup_page = new Intent(RegisterAct.this,Setup_Page.class);
                            startActivity(setup_page);


                        }
                        else {

                            String erromsg = task.getException().getMessage();
                            Toast.makeText(RegisterAct.this,"Error" + erromsg,Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();

                    }
                });


            }
            else {

                Toast.makeText(RegisterAct.this,"Password and Confirm Doesn't match",
                        Toast.LENGTH_LONG).show();
                progressDialog.dismiss();



            }
        }

//    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            SendToMain();
        }
    }

    private void SendToMain() {
        Intent main = new Intent(RegisterAct.this,MainActivity.class);
        startActivity(main);
    }
}

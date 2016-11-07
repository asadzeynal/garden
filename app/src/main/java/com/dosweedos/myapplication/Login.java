package com.dosweedos.myapplication;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;

        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.text.TextUtils;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

        import android.webkit.WebView;
        import android.webkit.WebViewClient;

        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;

        import java.io.FileInputStream;
        import java.io.FileOutputStream;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button buttonLogin,buttonCancel,buttonRegister;
    private EditText editTextUsername,editTextPassword;
    TextView tx1;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonLogin=(Button)findViewById(R.id.login);
        buttonCancel=(Button)findViewById(R.id.cancel);
        buttonRegister=(Button)findViewById(R.id.register);
        editTextUsername=(EditText)findViewById(R.id.username);
        editTextPassword=(EditText)findViewById(R.id.password);
//        tx1=(TextView)findViewById(R.id.textView3);
//        tx1.setVisibility(View.GONE);

        progressDialog = new ProgressDialog(this);

        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);

    }
    private void userLogin(){
        String email = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please, enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please, enter password", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Signing in, please wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogin){
            userLogin();
        }
        if(view == buttonRegister){
            finish();
            startActivity(new Intent(this, Registration.class));
        }
    }
}
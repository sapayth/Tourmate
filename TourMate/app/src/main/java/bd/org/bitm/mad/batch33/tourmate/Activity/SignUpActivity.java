package bd.org.bitm.mad.batch33.tourmate.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bd.org.bitm.mad.batch33.tourmate.R;

public class SignUpActivity extends AppCompatActivity {

    private TextView statusTV;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private EditText emailET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        statusTV = findViewById(R.id.statusTV);

        auth = FirebaseAuth.getInstance();
    }


    public void registerUser(View view) {
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                user = auth.getCurrentUser();
                statusTV.setText("Registered Successfully");

                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                statusTV.setText(e.getMessage());
            }
        });

    }
}

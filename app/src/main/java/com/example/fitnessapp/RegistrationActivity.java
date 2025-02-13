package com.example.fitnessapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword, editTextIme, editTextPrezime, editTextVisina, editTextTezina, editTextCiljevi, editTextAktivnost;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextIme = findViewById(R.id.editTextIme);
        editTextPrezime = findViewById(R.id.editTextPrezime);
        editTextVisina = findViewById(R.id.editTextVisina);
        editTextTezina = findViewById(R.id.editTextTezina);
        editTextCiljevi = findViewById(R.id.editTextCiljevi);
        editTextAktivnost = findViewById(R.id.editTextAktivnost);

        Button buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegisterClick();
            }
        });

    }

    public void onRegisterClick() {
        // Get values from EditText fields
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String ime = editTextIme.getText().toString();
        String prezime = editTextPrezime.getText().toString();
        String visinaString = editTextVisina.getText().toString();
        String tezinaString = editTextTezina.getText().toString();
        String ciljevi = editTextCiljevi.getText().toString();
        String aktivnost = editTextAktivnost.getText().toString();

        // Validate input fields
        if (email.isEmpty() || password.isEmpty() || ime.isEmpty() || prezime.isEmpty() ||
                visinaString.isEmpty() || tezinaString.isEmpty() || ciljevi.isEmpty() || aktivnost.isEmpty()) {
            Toast.makeText(RegistrationActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert height and weight to double
        double visina = Double.parseDouble(visinaString);
        double tezina = Double.parseDouble(tezinaString);

        // Call the method to register the user
        registerUser(email, password, ime, prezime, visina, tezina, ciljevi, aktivnost);
    }

    public void saveUserToDatabase(User user){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database.child(user.getId()).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "User data saved successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegistrationActivity.this, "Error saving user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void registerUser(String email, String password, String ime, String prezime, double visina, double težina, String ciljevi, String aktivnost) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid();
                                User newUser = new User(userId, ime, prezime, visina, težina, ciljevi, aktivnost);
                                saveUserToDatabase(newUser);
                            }
                        }else {
                            Toast.makeText(RegistrationActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}

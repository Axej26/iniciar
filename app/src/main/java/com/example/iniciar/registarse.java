package com.example.iniciar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class registarse extends AppCompatActivity {

    private static final String TAG = "RegistarseActivity";

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText cotrasenaConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registarse);

        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contraena);
        cotrasenaConfirmacion = findViewById(R.id.contrasenaconfir);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    public void registrarUsuario(View view) {
        String email = correo.getText().toString();
        String password = contrasena.getText().toString();
        String confirmPassword = cotrasenaConfirmacion.getText().toString();

        if (password.equals(confirmPassword)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(registarse.this, "Usuario creado ", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();

                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(registarse.this, "Error en el registro: " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
            Intent i= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        // Aquí puedes implementar las acciones que deseas realizar después de que el usuario se registre correctamente.
        // Por ejemplo, puedes redirigir al usuario a la pantalla principal de tu aplicación.
    }
}

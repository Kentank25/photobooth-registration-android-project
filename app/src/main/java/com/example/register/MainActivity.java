package com.example.register;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etUsername = findViewById(R.id.etUsername);

        // --- TAMBAHKAN INI ---
        EditText etPassword = findViewById(R.id.etPassword); // Pastikan ID sesuai di XML
        EditText etConfirmPassword = findViewById(R.id.etConfirmPassword); // Pastikan ID sesuai di XML
        // ----------------------

        Spinner spinnerCity = findViewById(R.id.spinnerTier);
        Button btnCreateAccount = findViewById(R.id.btnRegisterMember);

        String[] tiers = {"Silver Member", "Gold Member", "Platinum Member"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, tiers);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);

        btnCreateAccount.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String username = etUsername.getText().toString();

            // --- AMBIL VALUE PASSWORD ---
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            // ----------------------------

            // LOGIKA VALIDASI
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(MainActivity.this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            }
            // CEK APAKAH PASSWORD COCOK
            else if (!password.equals(confirmPassword)) {
                // Beri peringatan langsung di field confirm password
                etConfirmPassword.setError("Password tidak cocok!");
                etConfirmPassword.requestFocus();
            }
            else {
                // Jika semua validasi lolos
                Toast.makeText(MainActivity.this, "Registration Successful for " + username, Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
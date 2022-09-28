package bd.edu.sub.subian.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import bd.edu.sub.subian.databinding.ActivityPhoneNumberBinding;


/*
*
* Developed by Aryan Kabir
* BSC in CSE, State University of Bangladesh
* Date : 08/07/2021, BD

================
Profile Sortlink:
================
* Github Link: https://github.com/n3od4rk3r
* Fb Link: https://fb.com/n3od4rk3r
* Website: https://n3o-d4rk3r.github.io/
================
*
*/

public class PhoneNumberActivity extends AppCompatActivity {

    ActivityPhoneNumberBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();


        if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(PhoneNumberActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        //getSupportActionBar().hide();

        binding.phoneBox.requestFocus();

        binding.continueBtn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.phoneBox.getText().toString())) {
                // when mobile number text field is empty
                // displaying a toast message.
                Toast.makeText(PhoneNumberActivity.this, "Please enter your phone number! Ex: +8801*********", Toast.LENGTH_SHORT).show();

            }
            else {
                Intent intent = new Intent(PhoneNumberActivity.this, OTPActivity.class);
                intent.putExtra("phoneNumber", binding.phoneBox.getText().toString());
                startActivity(intent);
            }
        });

/*        binding.continueBtn.setOnClickListener(v -> {
            Intent intent = new Intent(PhoneNumberActivity.this, OTPActivity.class);
            intent.putExtra("phoneNumber", binding.phoneBox.getText().toString());
            startActivity(intent);
        });*/

    }
}
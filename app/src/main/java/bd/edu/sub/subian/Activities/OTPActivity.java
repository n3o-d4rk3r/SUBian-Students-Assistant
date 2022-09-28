package bd.edu.sub.subian.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import bd.edu.sub.subian.databinding.ActivityOTPBinding;



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
public class OTPActivity extends AppCompatActivity {

    ActivityOTPBinding binding;
    FirebaseAuth auth;
    String verificationId;
    ProgressDialog dialog;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOTPBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new ProgressDialog(this);
        dialog.setMessage("Sending OTP...");
        dialog.setCancelable(false);
        dialog.show();

        auth = FirebaseAuth.getInstance();

        //Objects.requireNonNull(getSupportActionBar()).hide();



        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        binding.phoneLbl.setText("Verify " + phoneNumber);

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(OTPActivity.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyId, forceResendingToken);
                        dialog.dismiss();
                        verificationId = verifyId;

                        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                        binding.otpView.requestFocus();
                    }
                }).build();

        PhoneAuthProvider.verifyPhoneNumber(options);

        binding.otpView.setOtpCompletionListener(otp -> {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);

            auth.signInWithCredential(credential).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(OTPActivity.this, SetupProfileActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    Toast.makeText(OTPActivity.this, "Hey SUBians!\nSorry for that, please try again later", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
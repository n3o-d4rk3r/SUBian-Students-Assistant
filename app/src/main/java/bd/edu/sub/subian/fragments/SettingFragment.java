package bd.edu.sub.subian.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.FileInputStream;
import java.util.ArrayList;

import bd.edu.sub.subian.Adapters.MessagesAdapter;
import bd.edu.sub.subian.Models.Message;
import bd.edu.sub.subian.R;
import bd.edu.sub.subian.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment implements View.OnClickListener{

    private FragmentSettingBinding binding;

    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        TextView profileBtn = (TextView) view.findViewById(R.id.profileId);
        profileBtn.setOnClickListener(this);

        return view;


    }
    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.profileId:
                fragment = new ProfileFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
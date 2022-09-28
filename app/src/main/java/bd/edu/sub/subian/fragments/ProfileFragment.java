package bd.edu.sub.subian.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Objects;

import bd.edu.sub.subian.Activities.ChatActivity;
import bd.edu.sub.subian.Models.User;
import bd.edu.sub.subian.R;
import bd.edu.sub.subian.databinding.FragmentProfileBinding;
import de.hdodenhof.circleimageview.CircleImageView;

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
public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;


    FirebaseDatabase database;
    FirebaseStorage storage;
    User user;


    TextView name;
    CircleImageView profile;




    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //binding = FragmentProfileBinding.inflate(getLayoutInflater());
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();














        View rootView = binding.getRoot();


/*
        binding.name.setText(name);
        Glide.with(ProfileFragment.this)
                .load(profile)
                .placeholder(R.drawable.avatar)
                .into(binding.profile);
*/



        return rootView;
    }

   /*
   * **********************
   * User Show in Fragment
   * **********************
   **/




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
package bd.edu.sub.subian.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import bd.edu.sub.subian.Adapters.TopStatusAdapter;
import bd.edu.sub.subian.Adapters.UsersAdapter;
import bd.edu.sub.subian.Models.Status;
import bd.edu.sub.subian.Models.User;
import bd.edu.sub.subian.Models.UserStatus;
import bd.edu.sub.subian.R;
import bd.edu.sub.subian.databinding.ActivityChatFragBinding;
import bd.edu.sub.subian.databinding.ActivityMainBinding;
import bd.edu.sub.subian.databinding.FragmentCommunityBinding;

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
public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudyFragment newInstance(String param1, String param2) {
        StudyFragment fragment = new StudyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }
}
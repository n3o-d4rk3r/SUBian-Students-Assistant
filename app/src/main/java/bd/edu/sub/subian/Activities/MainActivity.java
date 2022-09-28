package bd.edu.sub.subian.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import bd.edu.sub.subian.Adapters.TopStatusAdapter;
import bd.edu.sub.subian.Models.Status;
import bd.edu.sub.subian.Models.UserStatus;
import bd.edu.sub.subian.R;
import bd.edu.sub.subian.Models.User;
import bd.edu.sub.subian.Adapters.UsersAdapter;
import bd.edu.sub.subian.databinding.ActivityChatFragBinding;
import bd.edu.sub.subian.fragments.ChatFragment;
import bd.edu.sub.subian.fragments.CommunityFragment;
import bd.edu.sub.subian.fragments.PostTypeFragment;
import bd.edu.sub.subian.fragments.ScrollableTabsFragment;
import bd.edu.sub.subian.fragments.SettingFragment;
import bd.edu.sub.subian.fragments.StudyFragment;
import bd.edu.sub.subian.listener.OnFragmentStateChangeListener;
import bd.edu.sub.subian.module.BottomNavBehavior;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView btn = findViewById(R.id.chatId);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ChatFragActivity.class));
            }
        });



        //loading the default fragment
        loadFragment(new CommunityFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView bottom_nav_view = findViewById(R.id.bottomNavigationView);
        bottom_nav_view.setOnNavigationItemSelectedListener(this);

/*        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottom_nav_view.getLayoutParams();
        layoutParams.setBehavior(new BottomNavBehavior());*/

    }

    /**
     * Displays Fragment to post icon_text, icon_picture or video
     */
    /**
     * Displays Fragment to post icon_text, icon_picture or video
     */
    public void onAddTabClicked() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag(PostTypeFragment.class.getName()) == null) {
            PostTypeFragment postTypeFragment = PostTypeFragment.newInstance(new OnFragmentStateChangeListener() {
                @Override
                public void onDetach() {
                }

                @Override
                public void onPause() {
                    //setAddNewView(true);
                }

                @Override
                public void onOther(String postType) {
                    //openPostFragment(postType);
                    //selectTabIndex(0);
                }
            });
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.bottom_up, R.anim.bottom_down, R.anim.bottom_up, R.anim.bottom_down)
                    .add(R.id.frameLayout, postTypeFragment, PostTypeFragment.class.getName())
                    .addToBackStack(null)
                    .commit();

            //setAddNewView(false);

        } else {
            supportFragmentManager.popBackStackImmediate();
        }

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {

            case R.id.nav_add:
                onAddTabClicked();
                break;

            case R.id.nav_home:
                fragment = new CommunityFragment();
                break;

            case R.id.nav_study:
                fragment = new ScrollableTabsFragment();
                break;

            case R.id.nav_chats:
                fragment = new ChatFragment();
                break;

            case R.id.nav_profile:
                fragment = new SettingFragment();
                break;
        }
        return loadFragment(fragment);
    }




    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
            return true;
        }
        return false;
    }













/*

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null) {
            if(data.getData() != null) {
                //dialog.show();
                FirebaseStorage storage = FirebaseStorage.getInstance();
                Date date = new Date();
                StorageReference reference = storage.getReference().child("status").child(date.getTime() + "");

                reference.putFile(data.getData()).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    UserStatus userStatus = new UserStatus();
                                    userStatus.setName(user.getName());
                                    userStatus.setProfileImage(user.getProfileImage());
                                    userStatus.setLastUpdated(date.getTime());

                                    HashMap<String, Object> obj = new HashMap<>();
                                    obj.put("name", userStatus.getName());
                                    obj.put("profileImage", userStatus.getProfileImage());
                                    obj.put("lastUpdated", userStatus.getLastUpdated());

                                    String imageUrl = uri.toString();
                                    Status status = new Status(imageUrl, userStatus.getLastUpdated());

                                    database.getReference()
                                            .child("stories")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .updateChildren(obj);

                                    database.getReference().child("stories")
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .child("statuses")
                                            .push()
                                            .setValue(status);

                                    //dialog.dismiss();
                                }
                            });
                        }
                    }
                });
            }
        }
    }
*/

/*    @Override
    protected void onResume() {
        super.onResume();
        String currentId = FirebaseAuth.getInstance().getUid();
        database.getReference().child("presence").child(currentId).setValue("Online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        String currentId = FirebaseAuth.getInstance().getUid();
        database.getReference().child("presence").child(currentId).setValue("Offline");
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.group:
                startActivity(new Intent(MainActivity.this, ChatFragActivity.class));
                break;
/*            case R.id.settings:
                Toast.makeText(this, "Settings Clicked.", Toast.LENGTH_SHORT).show();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
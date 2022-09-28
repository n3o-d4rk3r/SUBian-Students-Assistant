package bd.edu.sub.subian.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bd.edu.sub.subian.R;
import bd.edu.sub.subian.listener.OnFragmentStateChangeListener;
import bd.edu.sub.subian.utils.SpringAnimationHelper;

/**
 * A {@link Fragment} with options to post icon_text, icon_picture or video
 */
public class PostTypeFragment extends Fragment implements View.OnClickListener {

    private OnFragmentStateChangeListener onFragmentStateChangeListener;

    /**
     * returns new instance of {@link PostTypeFragment}
     *
     * @param onFragmentStateChangeListener A callback for different states of the {@link Fragment}
     * @return {@link PostTypeFragment}
     */
    public static PostTypeFragment newInstance(OnFragmentStateChangeListener onFragmentStateChangeListener) {
        PostTypeFragment postTypeFragment = new PostTypeFragment();
        postTypeFragment.onFragmentStateChangeListener = onFragmentStateChangeListener;
        return postTypeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_type, container, false);
        view.findViewById(R.id.frag_add_text_pic_txt).setOnClickListener(this);
        view.findViewById(R.id.frag_add_text_pic_pic).setOnClickListener(this);
        view.findViewById(R.id.frag_add_text_pic_video).setOnClickListener(this);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        onFragmentStateChangeListener.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_add_text_pic_empty_view:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.frag_add_text_pic_video:
                SpringAnimationHelper.performAnimation(v);
                onFragmentStateChangeListener.onOther("video");
                break;
            case R.id.frag_add_text_pic_pic:
                SpringAnimationHelper.performAnimation(v);
                onFragmentStateChangeListener.onOther("image");
                break;
            case R.id.frag_add_text_pic_txt:
                SpringAnimationHelper.performAnimation(v);
                onFragmentStateChangeListener.onOther("text");
                break;
        }
    }
}

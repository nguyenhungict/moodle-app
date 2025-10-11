package vn.edu.usth.moodleapp.Nav_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.R;

public class PreferenceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.frag_preference, container, false);

        // Find the back button by its ID
        ImageButton backButton = view.findViewById(R.id.btn_back);

        // Handle the back button click
        backButton.setOnClickListener(v -> {
            // This will go back to the previous fragment
            requireActivity().onBackPressed();
        });

        return view;
    }
}


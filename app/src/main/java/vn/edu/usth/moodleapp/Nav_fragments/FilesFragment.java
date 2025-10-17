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

public class FilesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_files, container, false);


        ImageButton backButton = view.findViewById(R.id.btn_back);


        backButton.setOnClickListener(v -> {

            requireActivity().onBackPressed();
        });

        return view;
    }
}

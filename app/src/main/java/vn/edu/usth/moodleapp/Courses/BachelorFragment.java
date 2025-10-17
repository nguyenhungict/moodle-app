package vn.edu.usth.moodleapp.Courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.moodleapp.R;

public class BachelorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses_categories_bachelor, container, false);


        Button ictButton = view.findViewById(R.id.ict);


        ictButton.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();

            transaction.replace(R.id.fragment_container, new IctFragment());
            transaction.addToBackStack(null); // để có thể nhấn back quay lại
            transaction.commit();
        });

        Button backBtn = view.findViewById(R.id.back);
        backBtn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }
}

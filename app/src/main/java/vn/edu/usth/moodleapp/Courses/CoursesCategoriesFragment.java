package vn.edu.usth.moodleapp.Courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.SystemFragment;

public class CoursesCategoriesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Gắn XML vào Fragment
        View view = inflater.inflate(R.layout.fragment_courses_categories, container, false);

        // Nút Back
        Button btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new SystemFragment())
                    .commit();
        });

        // Nút Bachelor
        Button bachelorBtn = view.findViewById(R.id.bachelor);
        bachelorBtn.setOnClickListener(v -> {
            // Khi click sẽ mở sang BachelorFragment
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new BachelorFragment())
                    .addToBackStack(null) // để có thể bấm Back quay lại
                    .commit();
        });

        return view;
    }
}

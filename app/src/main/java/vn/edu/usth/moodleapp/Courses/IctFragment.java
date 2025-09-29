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

public class IctFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses_categories_bachelor_ict, container, false);

        // Lấy nút Third Year
        Button thirdYearBtn = view.findViewById(R.id.third_year);

        // Gắn sự kiện click
        thirdYearBtn.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ThirdYearFragment())
                    .addToBackStack(null) // để bấm Back quay lại ICT
                    .commit();
        });

        return view;
    }
}

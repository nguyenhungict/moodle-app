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

public class ThirdYearFragment extends Fragment {

    public ThirdYearFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses_categories_bachelor_ict_thirdyear, container, false);


        Button backBtn = view.findViewById(R.id.btn_back);
        backBtn.setOnClickListener(v -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        });


        Button mobileBtn = view.findViewById(R.id.course_mobile);
        mobileBtn.setOnClickListener(v -> {

        });


        Button webBtn = view.findViewById(R.id.course_web);
        webBtn.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new WebProgrammingFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}

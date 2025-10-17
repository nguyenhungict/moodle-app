package vn.edu.usth.moodleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.Courses.CoursesCategoriesFragment;

public class SystemFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_system, container, false);


        Button btnCourseList = root.findViewById(R.id.btn_course_list);

        btnCourseList.setOnClickListener(v -> {
            if (getActivity() != null) {

                getActivity().findViewById(R.id.view_pager).setVisibility(View.GONE);
                getActivity().findViewById(R.id.tab_layout).setVisibility(View.GONE);


                getActivity().findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);


                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CoursesCategoriesFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return root;
    }
}

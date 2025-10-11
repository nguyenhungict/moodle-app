package vn.edu.usth.moodleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.MainActivity;
import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.ThirdyearCourses.ArtificialIntelligent;
import vn.edu.usth.moodleapp.ThirdyearCourses.ComputerNetwork;
import vn.edu.usth.moodleapp.ThirdyearCourses.DatabaseManagement;
import vn.edu.usth.moodleapp.ThirdyearCourses.MobileDevelopment;
import vn.edu.usth.moodleapp.ThirdyearCourses.WebProgramming;

public class ThirdYearCourses extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses_categories_bachelor_ict_thirdyear, container, false);

        Button mobileButton = view.findViewById(R.id.course_mobile);
        Button webButton = view.findViewById(R.id.course_web);
        Button aiButton = view.findViewById(R.id.course_ai);
        Button networkButton = view.findViewById(R.id.course_networks);
        Button dbButton = view.findViewById(R.id.course_db);

        mobileButton.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(new MobileDevelopment());
        });

        webButton.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(new WebProgramming());
        });

        aiButton.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(new ArtificialIntelligent());
        });

        networkButton.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(new ComputerNetwork());
        });

        dbButton.setOnClickListener(v -> {
            ((MainActivity) requireActivity()).openFragment(new DatabaseManagement());
        });

        return view;
    }

}

//package vn.edu.usth.moodleapp;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import vn.edu.usth.moodleapp.ThirdyearCourses.MobileDevelopment;
//
//public class ThirdYearCourses extends Fragment {
//
//    public ThirdYearCourses() {
//        super(R.layout.fragment_courses_categories_bachelor_ict_thirdyear); // link your XML layout
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        // Find your buttons
//        Button mobileButton = view.findViewById(R.id.course_mobile);
//
//        // When clicking on "Mobile Development"
//        mobileButton.setOnClickListener(v -> {
//            Fragment mobileFragment = new MobileDevelopment();
//            requireActivity().findViewById(R.id.view_pager).setVisibility(View.GONE);
//            requireActivity().findViewById(R.id.tab_layout).setVisibility(View.GONE);
//            requireActivity().findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
//
//            // Replace current fragment with MobileDevelopmentFragment
//            requireActivity().getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, mobileFragment)
//                    .addToBackStack(null)
//                    .commit();
//        });
//    }
//}

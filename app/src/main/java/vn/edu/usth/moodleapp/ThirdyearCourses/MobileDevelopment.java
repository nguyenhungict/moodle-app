package vn.edu.usth.moodleapp.ThirdyearCourses;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.TeacherDetail.KieuQuocViet;

public class MobileDevelopment extends Fragment {

    public MobileDevelopment() {
        super(R.layout.fragment_ict_thirdyear_mobile); // link your XML layout
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Back button in header
        ImageButton backButton = view.findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
            requireActivity().findViewById(R.id.fragment_container).setVisibility(View.GONE);
            requireActivity().findViewById(R.id.view_pager).setVisibility(View.VISIBLE);
            requireActivity().findViewById(R.id.tab_layout).setVisibility(View.VISIBLE);
        });

        // Teacher info section
        LinearLayout teacherButton = view.findViewById(R.id.btn_teacher_info);

        // Open KieuQuocVietFragment when teacher name clicked
        teacherButton.setOnClickListener(v -> {
            Fragment teacherFragment = new KieuQuocViet();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, teacherFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }

}

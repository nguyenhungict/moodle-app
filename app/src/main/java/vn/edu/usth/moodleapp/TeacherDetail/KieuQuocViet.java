package vn.edu.usth.moodleapp.TeacherDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.R;

public class KieuQuocViet extends Fragment {

    public KieuQuocViet() {
        super(R.layout.activity_teacher1);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Find back button
        ImageButton backButton = view.findViewById(R.id.btn_back);

        // Back to MobileDevelopmentFragment
        backButton.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().popBackStack()
        );
    }
}

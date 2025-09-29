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
        // Bắt buộc phải có constructor rỗng
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Gắn layout vào fragment
        View view = inflater.inflate(R.layout.fragment_courses_categories_bachelor_ict_thirdyear, container, false);

        // Nút back
        Button backBtn = view.findViewById(R.id.back);
        backBtn.setOnClickListener(v -> {
            // Quay lại fragment trước đó
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack();
        });

        // Ví dụ: xử lý click một môn học
        Button course1 = view.findViewById(R.id.mobile_button4);
        course1.setOnClickListener(v -> {
            // TODO: mở ra fragment hoặc activity hiển thị chi tiết môn "Image Processing"
        });

        return view;
    }
}

package vn.edu.usth.moodleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private EditText search;
    private ImageView btnDownload;
    private ImageButton btnSort, btnGrid;
    private Button btnProgress;

    // Danh sách ID các button course
    private final int[] courseButtonIds = {
            R.id.btn_cal2,
            R.id.btn_ITI,
            R.id.btn_BP,
            R.id.btn_CA,
            R.id.btn_ITA,
            R.id.btn_DM,
            R.id.btn_NM,
            R.id.btn_FP2,
            R.id.btn_FOD,
            R.id.btn_ML
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Ánh xạ các view khác
        search = view.findViewById(R.id.search);
        btnDownload = view.findViewById(R.id.btn_dowload);
        btnSort = view.findViewById(R.id.btn_sort);
        btnGrid = view.findViewById(R.id.btn_grid);
        btnProgress = view.findViewById(R.id.btn_progress);

        // Ánh xạ và set sự kiện click cho tất cả course button
        for (int id : courseButtonIds) {
            Button courseBtn = view.findViewById(id);
            courseBtn.setOnClickListener(v -> {
                String courseName = courseBtn.getText().toString();
                openCourseDetail(courseName);
            });
        }

        return view;
    }

    private void openCourseDetail(String courseName) {
        // Xử lý khi user bấm vào course
        // Ví dụ: mở Activity khác, hoặc show Toast
        // Toast.makeText(getContext(), "Clicked: " + courseName, Toast.LENGTH_SHORT).show();
    }
}
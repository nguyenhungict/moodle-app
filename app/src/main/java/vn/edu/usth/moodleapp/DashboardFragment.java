package vn.edu.usth.moodleapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {

    private EditText search;
    private ImageView btnDownload;
    private ImageButton btnSort, btnGrid;
    private Button btnProgress;

    // IDS list
    private final int[] courseButtonIds = {
            R.id.btn_mobile,
            R.id.btn_web,
            R.id.btn_AI,
            R.id.btn_CN,
            R.id.btn_DataMag,
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


        search = view.findViewById(R.id.search);
        btnDownload = view.findViewById(R.id.btn_dowload);
        btnSort = view.findViewById(R.id.btn_sort);
        btnGrid = view.findViewById(R.id.btn_grid);
        btnProgress = view.findViewById(R.id.btn_progress);

        btnProgress.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), v);
            popupMenu.getMenuInflater().inflate(R.menu.filter_status, popupMenu.getMenu());
            popupMenu.show();
        });


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
    }
}
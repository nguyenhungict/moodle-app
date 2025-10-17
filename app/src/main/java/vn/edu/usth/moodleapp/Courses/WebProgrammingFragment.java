package vn.edu.usth.moodleapp.Courses;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.network.Course;
import vn.edu.usth.moodleapp.network.CourseContent;
import vn.edu.usth.moodleapp.network.MoodleVolleyClient;

public class WebProgrammingFragment extends Fragment {

    private static final String TAG = "WebProgrammingFragment";
    private static final String TOKEN = "986624f3530e6493486ae5ec3956aed3";
    private static final long USER_ID = 1;
    
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView tvTitle;
    private CourseContentAdapter adapter;
    private List<CourseContent> courseContents;
    private MoodleVolleyClient volleyClient;
    private long webProgrammingCourseId = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_programming, container, false);
        
        initViews(view);
        setupRecyclerView();
        volleyClient = new MoodleVolleyClient(getContext());
        

        getUserCourses();
        
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_course_content);
        progressBar = view.findViewById(R.id.progress_bar);
        tvTitle = view.findViewById(R.id.tv_course_title);
        courseContents = new ArrayList<>();
    }

    private void setupRecyclerView() {
        adapter = new CourseContentAdapter(courseContents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void getUserCourses() {
        showLoading(true);
        
        volleyClient.getUserCourses(TOKEN, USER_ID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Courses response: " + response);
                try {
                    Log.d(TAG, "Raw response: " + response);
                    
                    // Kiểm tra response có phải là array không
                    if (response.trim().startsWith("[")) {
                        Type listType = new TypeToken<List<Course>>(){}.getType();
                        List<Course> courses = new Gson().fromJson(response, listType);
                        
                        Log.d(TAG, "Parsed " + courses.size() + " courses");
                        
                        // Tìm khóa học "Web Programming"
                        for (Course course : courses) {
                            Log.d(TAG, "Course: " + course.fullname + " (ID: " + course.id + ")");
                            if (course.fullname != null && course.fullname.toLowerCase().contains("web programming")) {
                                webProgrammingCourseId = course.id;
                                tvTitle.setText(course.fullname);
                                Log.d(TAG, "Found Web Programming course ID: " + webProgrammingCourseId);
                                break;
                            }
                        }
                        
                        if (webProgrammingCourseId != -1) {
                            getCourseContents();
                        } else {

                            Log.d(TAG, "Trying with fixed course ID...");
                            webProgrammingCourseId = 3; // Thử với course ID = 2
                            tvTitle.setText("Web Programming (ID: " + webProgrammingCourseId + ")");
                            getCourseContents();
                        }
                    } else {

                        Log.e(TAG, "Response is not an array: " + response);
                        showError("Server trả về dữ liệu không đúng format: " + response);
                    }
                    
                } catch (Exception e) {
                    Log.e(TAG, "Error parsing courses: " + e.getMessage());
                    e.printStackTrace();
                    showError("Lỗi phân tích dữ liệu khóa học: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting courses: " + error.getMessage());
                showError("Lỗi kết nối: " + error.getMessage());
            }
        });
    }

    private void getCourseContents() {
        Log.d(TAG, "Getting course contents for course ID: " + webProgrammingCourseId);
        
        volleyClient.getCourseContents(TOKEN, webProgrammingCourseId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Course contents response: " + response);
                try {

                    if (response.trim().startsWith("[")) {
                        Type listType = new TypeToken<List<CourseContent>>(){}.getType();
                        List<CourseContent> contents = new Gson().fromJson(response, listType);
                        
                        courseContents.clear();
                        courseContents.addAll(contents);
                        adapter.notifyDataSetChanged();
                        
                        showLoading(false);
                        Log.d(TAG, "Loaded " + contents.size() + " course sections");
                        
                        if (contents.isEmpty()) {
                            showError("Khóa học không có nội dung nào");
                        }
                    } else {
                        Log.e(TAG, "Course contents response is not an array: " + response);
                        showError("Server trả về nội dung không đúng format: " + response);
                    }
                    
                } catch (Exception e) {
                    Log.e(TAG, "Error parsing course contents: " + e.getMessage());
                    e.printStackTrace();
                    showError("Lỗi phân tích nội dung khóa học: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error getting course contents: " + error.getMessage());
                showError("Lỗi lấy nội dung khóa học: " + error.getMessage());
            }
        });
    }

    private void showLoading(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    private void showError(String message) {
        showLoading(false);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}

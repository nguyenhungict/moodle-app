package vn.edu.usth.moodleapp.NavBottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.R;

public class BlogsFragment extends Fragment {

    private LinearLayout blogContainer;
    private EditText inputPost, inputUsername;
    private Button btnSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_bottom_blogs, container, false);

        blogContainer = view.findViewById(R.id.blogContainer);
        inputPost = view.findViewById(R.id.inputPost);
        inputUsername = view.findViewById(R.id.inputUsername);
        btnSend = view.findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String username = inputUsername.getText().toString().trim();
            String content = inputPost.getText().toString().trim();

            if (!content.isEmpty()) {
                if (username.isEmpty()) {
                    username = "Anonymous"; // default username
                }
                addPost(username, content);
                inputPost.setText("");
            }
        });

        return view;
    }

    private void addPost(String username, String content) {
        // Create layout for 1 post
        LinearLayout postLayout = new LinearLayout(getContext());
        postLayout.setOrientation(LinearLayout.VERTICAL);
        postLayout.setPadding(20, 20, 20, 20);
        postLayout.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

        // Row chứa avatar + username
        LinearLayout headerLayout = new LinearLayout(getContext());
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView avatar = new ImageView(getContext());
        avatar.setImageResource(R.drawable.baseline_person_24); // avatar mặc định
        LinearLayout.LayoutParams avatarParams = new LinearLayout.LayoutParams(100, 100);
        avatar.setLayoutParams(avatarParams);

        TextView usernameView = new TextView(getContext());
        usernameView.setText(username);
        usernameView.setTextSize(16);
        usernameView.setPadding(20, 0, 0, 0);

        headerLayout.addView(avatar);
        headerLayout.addView(usernameView);

        // Nội dung post
        TextView postView = new TextView(getContext());
        postView.setText(content);
        postView.setTextSize(16);
        postView.setPadding(0, 10, 0, 0);

        // Gắn vào layout post
        postLayout.addView(headerLayout);
        postLayout.addView(postView);

        // Thêm vào container
        blogContainer.addView(postLayout, 0);
    }
}
package vn.edu.usth.moodleapp.NavBottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import vn.edu.usth.moodleapp.R;

public class CalendarFragment extends Fragment {

    private WebView calendarWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_bottom_calendar, container, false);

        calendarWebView = view.findViewById(R.id.calendarWebView);
        WebSettings webSettings = calendarWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Cho phép chạy JS trong WebView
        calendarWebView.setWebViewClient(new WebViewClient());

        // 👉 Thay link này bằng link embed calendar của bạn
        String calendarUrl = "https://calendar.google.com/calendar/embed?src=ict.usthedu%40gmail.com&ctz=Asia%2FHo_Chi_Minh";
        calendarWebView.loadUrl(calendarUrl);

        return view;
    }
}

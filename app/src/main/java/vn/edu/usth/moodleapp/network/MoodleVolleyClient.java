package vn.edu.usth.moodleapp.network;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoodleVolleyClient {
    private static final String BASE_URL = "http://10.0.2.2:8080/";
    private RequestQueue requestQueue;
    private Context context;
    private Gson gson;

    public MoodleVolleyClient(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
        this.gson = new Gson();
    }

    // 1. LOGIN - Lấy token
    public void login(String username, String password,
                      Response.Listener<String> listener,
                      Response.ErrorListener errorListener) {
        String url = BASE_URL + "login/token.php?username=" + username +
                "&password=" + password + "&service=moodle_mobile_app";

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        requestQueue.add(request);
    }

    // 2. LẤY DANH SÁCH COURSES CỦA USER
    public void getUserCourses(String token, long userId,
                               Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        String url = BASE_URL + "webservice/rest/server.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("wstoken", token);
                params.put("wsfunction", "core_enrol_get_users_courses");
                params.put("moodlewsrestformat", "json");
                params.put("userid", String.valueOf(userId));
                return params;
            }
        };
        requestQueue.add(request);
    }

    // 3. LẤY NỘI DUNG COURSE (Modules, Files) - CHỨC NĂNG CHÍNH
    public void getCourseContents(String token, long courseId,
                                  Response.Listener<String> listener,
                                  Response.ErrorListener errorListener) {
        String url = BASE_URL + "webservice/rest/server.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("wstoken", token);
                params.put("wsfunction", "core_course_get_contents");
                params.put("moodlewsrestformat", "json");
                params.put("courseid", String.valueOf(courseId));
                return params;
            }
        };
        requestQueue.add(request);
    }

    // 4. TẠO USER MỚI
    public void createUser(String token, String username, String password,
                           String firstname, String lastname, String email,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {
        String url = BASE_URL + "webservice/rest/server.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("wstoken", token);
                params.put("wsfunction", "core_user_create_users");
                params.put("moodlewsrestformat", "json");
                params.put("users[0][username]", username);
                params.put("users[0][password]", password);
                params.put("users[0][firstname]", firstname);
                params.put("users[0][lastname]", lastname);
                params.put("users[0][email]", email);
                return params;
            }
        };
        requestQueue.add(request);
    }

    // 5. LẤY THÔNG TIN SITE
    public void getSiteInfo(String token,
                            Response.Listener<String> listener,
                            Response.ErrorListener errorListener) {
        String url = BASE_URL + "webservice/rest/server.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("wstoken", token);
                params.put("wsfunction", "core_webservice_get_site_info");
                params.put("moodlewsrestformat", "json");
                return params;
            }
        };
        requestQueue.add(request);
    }

    // Helper methods để parse JSON
    public TokenResponse parseTokenResponse(String jsonResponse) {
        return gson.fromJson(jsonResponse, TokenResponse.class);
    }

//    public List<Course> parseCoursesResponse(String jsonResponse) {
//        Type listType = new TypeToken<List<Course>>(){}.getType();
//        return gson.fromJson(jsonResponse, listType);
//    }
//
//    public List<CourseContent> parseCourseContentsResponse(String jsonResponse) {
//        Type listType = new TypeToken<List<CourseContent>>(){}.getType();
//        return gson.fromJson(jsonResponse, listType);
//    }

//    public SiteInfo parseSiteInfoResponse(String jsonResponse) {
//        return gson.fromJson(jsonResponse, SiteInfo.class);
//    }
}

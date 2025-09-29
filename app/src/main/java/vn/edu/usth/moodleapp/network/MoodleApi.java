    package vn.edu.usth.moodleapp.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface MoodleApi {

    /** Lấy token khi user login */
    @GET("login/token.php")
    Call<TokenResponse> getToken(
            @Query("username") String username,
            @Query("password") String password,
            @Query("service") String service
    );

    /** Tạo user mới trực tiếp (core_user_create_users) */
    @FormUrlEncoded
    @POST("webservice/rest/server.php")
    Call<okhttp3.ResponseBody> createUser(
            @Field("wstoken") String token,
            @Field("wsfunction") String wsfunction,      // "core_user_create_users"
            @Field("moodlewsrestformat") String format,  // "json"
            @Field("users[0][username]") String username,
            @Field("users[0][password]") String password,
            @Field("users[0][firstname]") String firstname,
            @Field("users[0][lastname]") String lastname,
            @Field("users[0][email]") String email
    );


    /** Lấy thông tin site (tuỳ chọn nếu cần) */
    @FormUrlEncoded
    @POST("webservice/rest/server.php")
    Call<SiteInfo> getSiteInfo(
            @Field("wstoken") String token,
            @Field("wsfunction") String wsfunction,      // "core_webservice_get_site_info"
            @Field("moodlewsrestformat") String format   // "json"
    );

    /** Lấy danh sách khóa học của user (tuỳ chọn nếu cần) */
    @FormUrlEncoded
    @POST("webservice/rest/server.php")
    Call<List<Course>> getUserCourses(
            @Field("wstoken") String token,
            @Field("wsfunction") String wsfunction,      // "core_enrol_get_users_courses"
            @Field("userid") long userId,
            @Field("moodlewsrestformat") String format
    );
}

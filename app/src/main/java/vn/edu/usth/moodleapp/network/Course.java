package vn.edu.usth.moodleapp.network;

public class Course {
    public long id;
    public String fullname;
    public String shortname;

    @Override
    public String toString() {
        return fullname + " (id=" + id + ")";
    }
}

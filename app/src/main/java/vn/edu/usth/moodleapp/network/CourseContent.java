package vn.edu.usth.moodleapp.network;

import java.util.List;

public class CourseContent {
    public int id;
    public String name;
    public int visible;
    public String summary;
    public int section;
    public String hiddenbynumsections;
    public boolean uservisible;
    public List<Module> modules;
    
    public static class Module {
        public int id;
        public String url;
        public String name;
        public int instance;
        public int visible;
        public boolean uservisible;
        public int visibleoncoursepage;
        public String modicon;
        public String modname;
        public String modplural;
        public String availability;
        public int indent;
        public String onclick;
        public String afterlink;
        public String customdata;
        public boolean noviewlink;
        public String completion;
        public String completiondata;
        public String downloadcontent;
        public String description;
        public List<Content> contents;
        
        public static class Content {
            public String type;
            public String filename;
            public String filepath;
            public int filesize;
            public String fileurl;
            public String timecreated;
            public String timemodified;
            public int sortorder;
            public String mimetype;
            public boolean isexternalfile;
            public String repositorytype;
        }
    }
}


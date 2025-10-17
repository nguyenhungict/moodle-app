package vn.edu.usth.moodleapp.Courses;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.moodleapp.R;
import vn.edu.usth.moodleapp.network.CourseContent;

public class CourseContentAdapter extends RecyclerView.Adapter<CourseContentAdapter.SectionViewHolder> {

    private List<CourseContent> courseContents;

    public CourseContentAdapter(List<CourseContent> courseContents) {
        this.courseContents = courseContents;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course_section, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        CourseContent section = courseContents.get(position);
        holder.bind(section);
    }

    @Override
    public int getItemCount() {
        return courseContents.size();
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSectionName;
        private TextView tvSectionSummary;
        private RecyclerView recyclerModules;
        private ModuleAdapter moduleAdapter;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSectionName = itemView.findViewById(R.id.tv_section_name);
            tvSectionSummary = itemView.findViewById(R.id.tv_section_summary);
            recyclerModules = itemView.findViewById(R.id.recycler_modules);
            
            moduleAdapter = new ModuleAdapter();
            recyclerModules.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            recyclerModules.setAdapter(moduleAdapter);
        }

        public void bind(CourseContent section) {
            tvSectionName.setText(section.name);
            if (section.summary != null && !section.summary.isEmpty()) {
                tvSectionSummary.setText(section.summary);
                tvSectionSummary.setVisibility(View.VISIBLE);
            } else {
                tvSectionSummary.setVisibility(View.GONE);
            }
            
            moduleAdapter.updateModules(section.modules);
        }
    }

    static class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
        private List<CourseContent.Module> modules;

        public ModuleAdapter() {
            this.modules = new java.util.ArrayList<>();
        }

        public void updateModules(List<CourseContent.Module> modules) {
            this.modules = modules != null ? modules : new java.util.ArrayList<>();
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_course_module, parent, false);
            return new ModuleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
            CourseContent.Module module = modules.get(position);
            holder.bind(module);
        }

        @Override
        public int getItemCount() {
            return modules.size();
        }

        static class ModuleViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivModuleIcon;
            private TextView tvModuleName;
            private TextView tvModuleType;

            public ModuleViewHolder(@NonNull View itemView) {
                super(itemView);
                ivModuleIcon = itemView.findViewById(R.id.iv_module_icon);
                tvModuleName = itemView.findViewById(R.id.tv_module_name);
                tvModuleType = itemView.findViewById(R.id.tv_module_type);
            }

        public void bind(CourseContent.Module module) {
            tvModuleName.setText(module.name);
            tvModuleType.setText(module.modplural);
            
            // Set icon based on module type
            int iconRes = getModuleIcon(module.modname);
            ivModuleIcon.setImageResource(iconRes);
            
            // Set click listener to open file/content
            itemView.setOnClickListener(v -> openModule(module));
        }
        
        private void openModule(CourseContent.Module module) {
            String urlToShow = null;
            String fileName = "Unknown";
            
            if (module.contents != null && !module.contents.isEmpty()) {
                // Có file content - lấy file đầu tiên
                CourseContent.Module.Content content = module.contents.get(0);
                if (content.fileurl != null && !content.fileurl.isEmpty()) {
                    urlToShow = content.fileurl;
                    fileName = content.filename != null ? content.filename : "File";
                }
            } else if (module.url != null && !module.url.isEmpty()) {
                // Có URL
                urlToShow = module.url;
                fileName = module.name != null ? module.name : "Module";
            }
            
            if (urlToShow != null) {
                showFileUrl(urlToShow, fileName);
            } else {
                Toast.makeText(itemView.getContext(), "Không có URL để hiển thị", Toast.LENGTH_SHORT).show();
            }
        }
        
        private void showFileUrl(String fileUrl, String fileName) {
            // Thêm token vào URL
            String urlWithToken = fileUrl;
            if (!fileUrl.contains("token=")) {
                urlWithToken = fileUrl + (fileUrl.contains("?") ? "&" : "?") + "token=986624f3530e6493486ae5ec3956aed3";
            }
            
            // Mở file trong browser/web
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urlWithToken));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                itemView.getContext().startActivity(intent);
                
                Toast.makeText(itemView.getContext(), "Đang mở file: " + fileName, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(itemView.getContext(), "Không thể mở file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            
            // Log để debug
            android.util.Log.d("FileURL", "Opening file: " + fileName + " - URL: " + urlWithToken);
        }

            private int getModuleIcon(String modname) {
                if (modname == null) return R.drawable.ic_file;
                
                switch (modname.toLowerCase()) {
                    case "forum":
                        return R.drawable.ic_forum;
                    case "resource":
                    case "file":
                        return R.drawable.ic_file;
                    case "url":
                        return R.drawable.ic_link;
                    case "page":
                        return R.drawable.ic_page;
                    case "book":
                        return R.drawable.ic_book;
                    case "quiz":
                        return R.drawable.ic_quiz;
                    case "assignment":
                        return R.drawable.ic_assignment;
                    default:
                        return R.drawable.ic_file;
                }
            }
        }
    }
}

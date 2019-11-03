package me.astashenkov.SecreteKeeperMain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.astashenkov.basicdiary.R;

public class DiaryAdapter extends ArrayAdapter<DiaryAdapter.Diary> {
    private Context diaryContext;
    private List<Diary> diaryList = new ArrayList<>();

    public DiaryAdapter(@NonNull Context context, @NonNull ArrayList<Diary> list) {
        super(context, 0, list);
        this.diaryContext = context;
        this.diaryList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(diaryContext).inflate(R.layout.list_item,parent,false);

        Diary currentDiary = diaryList.get(position);

        TextView title = (TextView) listItem.findViewById(R.id.textView_title);
        title.setText(currentDiary.getTitle());

        TextView text = (TextView) listItem.findViewById(R.id.textView_text);
        String description = currentDiary.getDescription();
        if (description.length() > 20) description = description.substring(0, 20) + "...";
        text.setText(description);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd\nHH:mm:ss");
        TextView dateCreated = (TextView) listItem.findViewById(R.id.textView_date_created);
        dateCreated.setText(currentDiary.getDateCreated());

        TextView dateModified = (TextView) listItem.findViewById(R.id.textView_date_modified);
        dateModified.setText(currentDiary.getDateModified());


        return listItem;
    }

    public static class Diary implements Serializable {
        public static final String DIARY = "Diary";
        public static final String ID = "_id";
        public static final String TITLE = "Title";
        public static final String DESCRIPTION = "Description";
        public static final String CREATED = "Created";
        public static final String MODIFIED = "Modified";
        public static final String CREATE_DIARY = "create table " + DIARY +
                " (" + ID + " integer primary key autoincrement, " + TITLE +
                " text null, " + DESCRIPTION + " text null, " + CREATED +
                " datetime default CURRENT_TIMESTAMP, " + MODIFIED +
                " datetime default CURRENT_TIMESTAMP); ";

        private int id;
        private String dateCreated;
        private String title;
        private String description;
        private String dateModified;

        public Diary() {};

        public Diary(int id, String title, String description, String dateCreated, String dateModified) {
            this.id = id;
            this.dateCreated = dateCreated;
            this.title = title;
            this.description = description;
            this.dateModified = dateModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }
    }
}
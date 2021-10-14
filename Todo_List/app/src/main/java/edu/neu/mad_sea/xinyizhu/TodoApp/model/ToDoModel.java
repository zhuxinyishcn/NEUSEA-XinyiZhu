package edu.neu.mad_sea.xinyizhu.TodoApp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class ToDoModel implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "status")
    private int status;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "detail")
    private String detail;


    protected ToDoModel(Parcel in) {
        id = in.readInt();
        status = in.readInt();
        title = in.readString();
        detail = in.readString();
    }

    public static final Creator<ToDoModel> CREATOR = new Creator<ToDoModel>() {
        @Override
        public ToDoModel createFromParcel(Parcel in) {
            return new ToDoModel(in);
        }

        @Override
        public ToDoModel[] newArray(int size) {
            return new ToDoModel[size];
        }
    };

    public String getTitle() {
        StringBuilder sb = new StringBuilder(title);
        sb.append("\n");
        sb.append(detail);
        return sb.toString();
    }

    public ToDoModel(int id, int status, String title, String detail) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.detail = detail;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(status);
        parcel.writeString(title);
        parcel.writeString(detail);
    }
}

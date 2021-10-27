package edu.neu.mad_sea.xinyizhu.TodoApp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo")
public class ToDoModel implements Parcelable {

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
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int id;
  @ColumnInfo(name = "status")
  private int status;
  @ColumnInfo(name = "title")
  private String title;
  @ColumnInfo(name = "detail")
  private String detail;
  @ColumnInfo(name = "dueTime")
  private String dueTime;


  protected ToDoModel(Parcel in) {
    id = in.readInt();
    status = in.readInt();
    title = in.readString();
    detail = in.readString();

  }

  public ToDoModel(int status, String title, String detail, String dueTime) {
    this.status = status;
    this.title = title;
    this.detail = detail;
    this.dueTime = dueTime;
  }

  public String getTitle() {
    StringBuilder sb = new StringBuilder(title);
    sb.append("\n");
    if (detail.length() > 3) {
      sb.append(detail.substring(0, 3));
    } else {
      sb.append(detail);
    }
    sb.append("...");
    return sb.toString();
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDueTime() {
    return dueTime;
  }

  public void setDueTime(String dueTime) {
    this.dueTime = dueTime;
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
    parcel.writeString(dueTime);
  }

  @Override
  public String toString() {
    return "ToDoModel{" +
        "id=" + id +
        ", status=" + status +
        ", title='" + title + '\'' +
        ", detail='" + detail + '\'' +
        ", dueTime='" + dueTime + '\'' +
        '}';
  }
}

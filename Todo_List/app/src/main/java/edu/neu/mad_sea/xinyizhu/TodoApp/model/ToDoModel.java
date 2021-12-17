package edu.neu.mad_sea.xinyizhu.TodoApp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * The type To do model.
 */
@Entity(tableName = "todo")
public class ToDoModel implements Parcelable {

  /**
   * The constant CREATOR.
   */
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


  /**
   * Instantiates a new To do model.
   *
   * @param in the in
   */
  protected ToDoModel(Parcel in) {
    id = in.readInt();
    status = in.readInt();
    title = in.readString();
    detail = in.readString();
  }

  /**
   * Instantiates a new To do model.
   *
   * @param status  the status
   * @param title   the title
   * @param detail  the detail
   * @param dueTime the due time
   */
  public ToDoModel(int status, String title, String detail, String dueTime) {
    this.status = status;
    this.title = title;
    this.detail = detail;
    this.dueTime = dueTime;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets due time.
   *
   * @return the due time
   */
  public String getDueTime() {
    if (dueTime.length() < 10) {
      return dueTime;
    }
    return dueTime.substring(0, 10);
  }

  /**
   * Sets due time.
   *
   * @param dueTime the due time
   */
  public void setDueTime(String dueTime) {
    this.dueTime = dueTime;
  }

  /**
   * Gets detail.
   *
   * @return the detail
   */
  public String getDetail() {
    if (detail.length() > 20) {
      return detail.substring(0, 20);
    }
    return detail;
  }

  /**
   * Sets detail.
   *
   * @param detail the detail
   */
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(int status) {
    this.status = status;
  }


  /**
   * Describe contents int.
   *
   * @return the int
   */
  @Override
  public int describeContents() {
    return 0;
  }

  /**
   * Write to parcel.
   *
   * @param parcel the parcel
   * @param i      the
   */
  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeInt(status);
    parcel.writeString(title);
    parcel.writeString(detail);
    parcel.writeString(dueTime);
  }

  /**
   * To string string.
   *
   * @return the string
   */
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

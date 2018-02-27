package com.globallogic.mtinterviewapp.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nicolasfernandez on 2/23/18.
 */

public class Laptop implements Parcelable {

  @SerializedName("title")
  private String mTitle;
  @SerializedName("description")
  private String mDescription;
  @SerializedName("image")
  private String mImage;

  public Laptop(String title, String description, String image) {
    this.mTitle = title;
    this.mDescription = description;
    this.mImage = image;
  }

  protected Laptop(Parcel in) {
    mTitle = in.readString();
    mDescription = in.readString();
    mImage = in.readString();
  }

  public static final Creator<Laptop> CREATOR = new Creator<Laptop>() {
    @Override
    public Laptop createFromParcel(Parcel in) {
      return new Laptop(in);
    }

    @Override
    public Laptop[] newArray(int size) {
      return new Laptop[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(mTitle);
    dest.writeString(mDescription);
    dest.writeString(mImage);
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  public String getDescription() {
    return mDescription;
  }

  public void setDescription(String description) {
    this.mDescription = description;
  }

  public String getImage() {
    return mImage;
  }

  public void setImage(String image) {
    this.mImage = image;
  }
}

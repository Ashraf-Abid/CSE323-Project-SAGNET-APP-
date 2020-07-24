package com.octoriz.abids.saarc.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "name_table",foreignKeys = @ForeignKey(entity = Country.class,
        parentColumns = "country_id",
        childColumns = "country_id",
        onDelete = ForeignKey.NO_ACTION))
public class Name {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    @ColumnInfo(name = "country_id")
    private int country_id;

    //room will auto insert the id
    public Name(int country_id,String name) {
        this.name = name;
        this.country_id = country_id;
    }

    //only one setter for setting id
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountry_id() {
        return country_id;
    }
}

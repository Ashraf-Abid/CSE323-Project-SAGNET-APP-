package com.octoriz.abids.saarc.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "country_table", indices = {@Index("country_id")})
public class Country {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "country_id")
    private int country_id;
    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public String getCountryName() {
        return countryName;
    }
}

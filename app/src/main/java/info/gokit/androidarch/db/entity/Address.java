package info.gokit.androidarch.db.entity;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by llitfkitfk on 11/13/17.
 */

public class Address {
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}

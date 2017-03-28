package com.zksyp.informationgotten.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.zksyp.informationgotten.base.AbsFragment;
import com.zksyp.informationgotten.base.AbsPagerFragment;

import java.lang.reflect.Constructor;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/28
 * Time:上午10:23
 * Desc:
 */

public class TabInfo implements Parcelable {
    private int id;
    private int icon;
    private String name = null;
    private String mStrData = null;
    public boolean hasTips = false;
    public boolean notifyChange = false;
    public AbsPagerFragment mFragment = null;
    public Boolean hasUnRead = false;
    @SuppressWarnings("rawtypes")
    public Class fragmentClass = null;


    @SuppressWarnings("rawtypes")
    public TabInfo(int id, String name, Class clazz) {
        this(id, name, 0, clazz);
    }

    public TabInfo(int id, String name, AbsPagerFragment f) {
        this.name = name;
        this.id = id;
        this.mFragment = f;
    }

    public TabInfo(int id, String name, AbsPagerFragment f, Boolean hasUnRead) {
        this.name = name;
        this.id = id;
        this.mFragment = f;
        this.hasUnRead = hasUnRead;
    }

    public TabInfo(String name, AbsFragment f) {
        this.name = name;
        this.id = f.getPageIndex();
        this.mFragment = f;
    }


    @SuppressWarnings("rawtypes")
    public TabInfo(int id, String name, boolean hasTips, Class clazz) {
        this(id, name, 0, clazz);
        this.hasTips = hasTips;
    }

    @SuppressWarnings("rawtypes")
    public TabInfo(int id, String name, int iconid, Class clazz) {
        super();

        this.name = name;
        this.id = id;
        icon = iconid;
        fragmentClass = clazz;
    }

    public TabInfo(Parcel p) {
        this.id = p.readInt();
        this.name = p.readString();
        this.icon = p.readInt();
        this.notifyChange = p.readInt() == 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int iconid) {
        icon = iconid;
    }

    public int getIcon() {
        return icon;
    }

    public TabInfo setStrData(String data) {
        this.mStrData = data;
        return this;
    }

    public String getmStrData() {
        return this.mStrData;
    }

    public AbsPagerFragment newInstance() {
        if (mFragment == null) {
            Constructor constructor;
            try {
                constructor = fragmentClass.getConstructor();
                mFragment = (AbsPagerFragment) constructor.newInstance();
                if (!TextUtils.isEmpty(getmStrData())) {
                    mFragment.setStringData(getmStrData());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mFragment;
    }

    public static final Parcelable.Creator<TabInfo> CREATOR = new Parcelable.Creator<TabInfo>() {
        public TabInfo createFromParcel(Parcel p) {
            return new TabInfo(p);
        }

        public TabInfo[] newArray(int size) {
            return new TabInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel p, int flags) {
        p.writeInt(id);
        p.writeString(name);
        p.writeInt(icon);
        p.writeInt(notifyChange ? 1 : 0);
    }

    @Override
    public String toString() {
        return "{id = " + id + ", name = " + name + ",fragment = " + mFragment + "}";
    }
}

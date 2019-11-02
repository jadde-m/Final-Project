package com.example.myapplication.dummy;

import android.icu.text.CaseMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UpperContent extends DummyContent{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyContent.DummyItem> ITEMS = new ArrayList<DummyContent.DummyItem>();
    public static final List<UpperContent.UpperItem> UPITEMS = new ArrayList<UpperContent.UpperItem>();

    public final String address = "d://jx3//wiki//";
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    /**upperitem 上层文件
     * 含id，不显示，content，显示
     * 一组dummyitem
     */

    public static class UpperItem {
        /*
        UpperItem结构：
        id
        name：大类成就名
        number：大类包含成就数
        dummylist：dummylist表
        flag：是否包含upperlist
        uplist：upperitem表
         */
        public final String upid;
        public final String upcontent;
        public final Boolean flag;
        public List<UpperItem> uplist = new ArrayList<UpperItem>();
        public List<DummyItem> dummylist = new ArrayList<DummyItem>();
        /*
        upperitem初始化方法 如果flag值为真，对uplist进行初始化
         */
        public UpperItem(String upid, String upcontent, List<DummyItem> dummylist,List<UpperItem> uplist,boolean flag) {
            this.upid = upid;
            this.upcontent = upcontent;
            this.dummylist = dummylist;
            this.flag = flag;
            if(flag){
                this.uplist = uplist;
            }
        }

    }
}

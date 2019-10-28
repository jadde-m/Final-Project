package com.example.myapplication.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UpperContent extends DummyContent{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyContent.DummyItem> ITEMS = new ArrayList<DummyContent.DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    /**upperitem 上层文件
     * 含id，不显示，content，显示
     * 一组dummyitem
     */

    public static class UpperItem {
        public final String upid;
        public final String upcontent;
        public List<DummyItem> upitemlist = new ArrayList<DummyItem>();
        public UpperItem(String upid, String upcontent, List<DummyItem> upitemlist) {
            this.upid = upid;
            this.upcontent = upcontent;
            this.upitemlist = upitemlist;
        }

    }
}

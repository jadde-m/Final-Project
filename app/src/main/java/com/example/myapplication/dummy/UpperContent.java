package com.example.myapplication.dummy;

import android.content.Context;
import android.icu.text.CaseMap;

import com.example.myapplication.ChengJiu;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UpperContent extends DummyContent{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<UpperContent.UpperItem> ITEMS = new ArrayList<UpperContent.UpperItem>();
    public static final List<UpperContent.UpperItem> UPITEMS = new ArrayList<UpperContent.UpperItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, UpperItem> ITEM_MAP = new HashMap<String, UpperItem>();

    /*
        初始化upperitem 输入变量context flag:表记是否还有下级uppercontent， path
     */
    /*
        一级upperitem初始化 包括dummyitem列表 upperitem列表，各自包含一哈希表
     */
    public static final String path = "//wiki";
    static void InitFirstUpperItem(Context context) throws IOException {
        String[] listFiles = context.getAssets().list(path);
        for(String listFile : listFiles){// /wiki/1
            /*
            过滤txt文件
             */
            if(listFile.contains("txt")) continue;
            UpperContent secui = new UpperContent();
            secui.InitSecondUpperItem(context,path+"//"+listFile); //处理下级目录中文件夹部分
            DummyContent dc = new DummyContent();
            dc.InitDummyItem(context,path+"//"+listFile);
            /*
            读取当前文件info 包括名字 数值
             */
            InputStream is = context.getAssets().open(path+"//"+listFile+"//info.txt");
            int lenght = is.available();
            byte[]  buffer = new byte[lenght];
            is.read(buffer);
            String cont = new String(buffer, "utf8");
            UpperItem ui = new UpperItem(listFile,cont,dc.ITEMS,secui.ITEMS,true);
            ITEMS.add(ui);
            ITEM_MAP.put(ui.upid,ui);
        }
    }
    /*
    二级列表初始化 path不带// 继续寻找path目录下文件夹 进入文件夹对文件夹建立dummycontent项
     */
    static void InitSecondUpperItem(Context context,String path) throws IOException {
        String[] listFiles = context.getAssets().list(path);
        for(String listFile : listFiles){// /wiki/1/2
            /*
            过滤txt文件 去掉/wiki/1/code.txt
             */
            if(listFile.contains("txt")) continue;
            /*
            文件夹目录构建具体dummycontent类
             */
            DummyContent dc=new DummyContent();
            dc.InitDummyItem(context,path+"//"+listFile);
            /*
            读取当前文件info 包括名字 数值
             */
            InputStream is = context.getAssets().open(path+"//"+listFile+"//info.txt");
            int lenght = is.available();
            byte[]  buffer = new byte[lenght];
            is.read(buffer);
            String cont = new String(buffer, "utf8");
            UpperItem ui = new UpperItem(listFile,cont,dc.ITEMS,false);
            ITEMS.add(ui);
            ITEM_MAP.put(ui.upid,ui);
        }
    }


    /**upperitem 上层文件
     * 含id，不显示，content，显示
     * 一组dummyitem
     */

    public static class UpperItem {
        /*
        UpperItem结构：
        id
        name：大类成就名+成就数
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
        public UpperItem(String upid, String upcontent, List<DummyItem> dummylist,boolean flag) {
            this.upid = upid;
            this.upcontent = upcontent;
            this.dummylist = dummylist;
            this.flag = flag;

        }
    }
}

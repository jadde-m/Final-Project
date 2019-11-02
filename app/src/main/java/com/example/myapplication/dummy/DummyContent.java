package com.example.myapplication.dummy;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 基本成就项
 */
public class DummyContent {

    /**
     * dummy item表
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * dummy item哈希表，用于初始化dummyitem
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;
    private static String detailpath = "//detail//";
    static void InitDummyItem(Context context,String path){
        /*
        初始化方法：传入context 文件路径path
         */
        try {
            String fileNames[] = context.getAssets().list(path);
            /*
            遍历文件，判断path下文件
             */
            for (String fileName : fileNames){
                if(Pattern.compile("[0-9]*.*txt").matcher(fileName).matches()){
                    String id = fileName.replace(".txt","");
                    String fileInfos[]=context.getAssets().list(detailpath+id);
                    String name=null,content=null,point=null;
                    for (String fileInfo : fileInfos){
                        if(fileInfo.contains("point")){
                            point=fileInfo.replace("point ","");
                        }else if(fileInfo.contains("name")){
                            InputStream is = context.getAssets().open(detailpath+id+"//"+fileInfo);
                            int lenght = is.available();
                            byte[]  buffer = new byte[lenght];
                            is.read(buffer);
                            name = new String(buffer, "utf8");
                        }else if(fileInfo.contains("info")){
                            InputStream is = context.getAssets().open(detailpath+id+"//"+fileInfo);
                            int lenght = is.available();
                            byte[]  buffer = new byte[lenght];
                            is.read(buffer);
                            content = new String(buffer, "utf8");
                        }
                    }
                    addItem(new DummyItem(id,name,content,point));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * dummyitem初始化.
     */
    public static class DummyItem {
        public final String id;
        public final String name;
        public final String details;
        public final String point;

        public DummyItem(String id, String content, String details,String point) {
            this.id = id;
            this.name = content;
            this.details = details;
            this.point = point;
        }
        @Override
        public String toString() {
            return name;
        }
    }

}

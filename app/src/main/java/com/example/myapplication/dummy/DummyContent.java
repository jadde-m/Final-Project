package com.example.myapplication.dummy;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
    public List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * dummy item哈希表，用于初始化dummyitem
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;
    private static String detailpath = "detail/";
    public void InitDummyItem(Context context, String path){
        /*
        初始化方法：传入context 文件路径path
         */
        //ITEMS.clear();
        try {
            String fileids[] = getString(context,path+"/dummyitemidlist.txt").split("\n");
            /*
            遍历文件，判断path下文件
             */
            for (String fileid : fileids){
                String fileInfos[]=context.getAssets().list(detailpath+fileid);
                String name=null,content=null,point=null;
                for (String fileInfo : fileInfos){
                    if(fileInfo.contains("name")){
                        name = getString(context,detailpath+fileid+"/"+fileInfo);
                    }else if(fileInfo.contains("info")){
                        content = getString(context,detailpath+fileid+"/"+fileInfo);
                    }else if(fileInfo.contains(".txt")){
                        point=fileInfo.replace(".txt","");
                    }

                    Log.i("dummyiteminfo",detailpath + "/" +fileid+fileInfo + "\n");
                }
                addItem(new DummyItem(fileid,name,content,point));
                //Log.i("dummyitem",detailpath + "/" +fileid+name+content+point + "\n");
                }
            } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static String getString(Context context, String path) throws IOException {
        InputStream is = context.getAssets().open(path);
        int lenght = is.available();
        byte[]  buffer = new byte[lenght];
        is.read(buffer);
        return (new String(buffer, "utf8"));
    }


    private void addItem(DummyItem item) {
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

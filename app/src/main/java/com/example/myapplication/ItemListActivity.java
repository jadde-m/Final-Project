package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.dummy.UpperContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.dummy.DummyContent;

import java.io.IOException;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity /*implements Runnable*/{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    static View uprecyclerView;
    static View dummyrecyclerView;
   // Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Log.i("start","for starts");
/**
 * 浮动按钮功能
 */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        /*
        设置itemlist项
         */


        /*
        !!!!!!!!!!!!设置listitem项
         */
        /*View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);*/
        /*
        ！！！！！！ 设置upitemlist项
         */
        uprecyclerView = findViewById(R.id.upitem_list);
        assert uprecyclerView != null;
        dummyrecyclerView= findViewById(R.id.item_list);
        assert uprecyclerView != null;
        UpperContent uc = new UpperContent();
        try {
            uc.InitFirstUpperItem(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        ((RecyclerView) uprecyclerView).setAdapter(new UpItemRecyclerViewAdapter(this,uc.ITEMS));
       /* Thread t = new Thread(this);
        t.start();
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };*/
    }

/*
    @Override
    public void run() {
        // Message msg = handler.obtainMessage(2);
        //setupUpRecyclerView((RecyclerView) uprecyclerView);
        try {

            View uprecyclerView = findViewById(R.id.upitem_list);
            assert uprecyclerView != null;

            UpperContent uc = new UpperContent();
            uc.InitFirstUpperItem(this);
            ((RecyclerView) uprecyclerView).setAdapter(new UpItemRecyclerViewAdapter(uc.UPITEMS));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //msg.obj =

    }
*/

    /*
    dummyitemlist项初始化
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //recyclerView.setAdapter(new DummyItemRecyclerViewAdapter(this, DummyContent.ITEMS));
    }

    public void returnmethod(View view) {
        uprecyclerView = findViewById(R.id.upitem_list);
        assert uprecyclerView != null;
        UpperContent uc = new UpperContent();
        try {
            uc.InitFirstUpperItem(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        ((RecyclerView) uprecyclerView).setAdapter(new UpItemRecyclerViewAdapter(this,uc.ITEMS));
    }


    public static class DummyItemRecyclerViewAdapter
            extends RecyclerView.Adapter<DummyItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<DummyContent.DummyItem> mValues;
        /*
        初始化函数
         */
        DummyItemRecyclerViewAdapter(ItemListActivity parent,
                                     List<DummyContent.DummyItem> items) {
            mValues = items;
            mParentActivity = parent;
        }
        /*
        初始化点击方法
         */
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            /*
            点击相应函数
             */
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);
                context.startActivity(intent);

            }
        };


        @Override
        /*
            初始化子项布局
         */
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.dummytitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        /*
            初始化子项数据
         */
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
           // holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).details);
            holder.mPointView.setText(mValues.get(position).point);
            holder.mnameView.setText(mValues.get(position).name);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mnameView;
            final TextView mContentView;
            final TextView mPointView;
            ViewHolder(View view) {
                super(view);
                mnameView = (TextView) view.findViewById(R.id.dummyitemname);
                mContentView = (TextView) view.findViewById(R.id.dummycontent);
                mPointView = (TextView) view.findViewById(R.id.point);
            }
        }
    }

/*
    upitemlist项初始化
 */
    private void setupUpRecyclerView(@NonNull RecyclerView recyclerView) throws IOException {

        //recyclerView.setAdapter(new UpItemRecyclerViewAdapter(this, uc.UPITEMS));
    }
    public static class UpItemRecyclerViewAdapter
            extends RecyclerView.Adapter<UpItemRecyclerViewAdapter.ViewHolder> {

        //private final ItemListActivity mParentActivity;
        private final List<UpperContent.UpperItem> mValues;
        private final ItemListActivity mParentAct;

        /*
        初始化函数
         */
        UpItemRecyclerViewAdapter(ItemListActivity itemListActivity, List<UpperContent.UpperItem> upitems) {
            mValues = upitems;
            mParentAct = itemListActivity;
        }
        /*
        点击事件：点击显示一组upperitem+dummyitem
         */
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            /*
            点击相应函数
             */
            public void onClick(View view) {
                UpperContent.UpperItem item = (UpperContent.UpperItem)view.getTag();
                if (item.flag) {
                    ((RecyclerView) uprecyclerView).setAdapter(new UpItemRecyclerViewAdapter(mParentAct, item.uplist));
                    ((RecyclerView) dummyrecyclerView).setAdapter(new DummyItemRecyclerViewAdapter(mParentAct,item.dummylist));
                } else {
                    ((RecyclerView) dummyrecyclerView).setAdapter(new DummyItemRecyclerViewAdapter(mParentAct,item.dummylist));
                    /*Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    //intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);*/
                }
            }
        };



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.upperitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).upcontent);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);


        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.upitemcontent);
            }
        }
    }

}

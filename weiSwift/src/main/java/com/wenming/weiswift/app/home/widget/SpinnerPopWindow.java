package com.wenming.weiswift.app.home.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.entity.ExpertCategoryEnity;
import com.wenming.weiswift.utils.DensityUtil;

import java.util.List;


/**
 * 自定义PopupWindow  主要用来显示ListView
 */
public class SpinnerPopWindow<T> extends PopupWindow {
    private LayoutInflater inflater;
    private ListView mListView;
    private List<T> list;
    private List<String>sortList;
    private MyAdapter  mAdapter;
    private Context mContext;
    private boolean mIsRight;
    public SpinnerPopWindow(Context context, List<T>list, AdapterView.OnItemClickListener clickListener,Boolean isRight) {
        super(context);
        inflater=LayoutInflater.from(context);
        mContext=context;
        this.list=list;
        mIsRight=isRight;
        init(clickListener);
    }

    private void init(AdapterView.OnItemClickListener clickListener){
        View view = inflater.inflate(R.layout.spinner_window_layout, null);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(DensityUtil.dp2px(mContext,240));
        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setAdapter(mAdapter=new MyAdapter());
        mListView.setOnItemClickListener(clickListener);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=inflater.inflate(R.layout.spinner_item_layout, null);
                holder.tvName=(TextView) convertView.findViewById(R.id.tv_name);
                if(mIsRight){
                    holder.tvName.setGravity(Gravity.CENTER|Gravity.RIGHT);
                }
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(getItem(position).toString());
            return convertView;
        }
    }

    private class ViewHolder{
        private TextView tvName;
    }
}

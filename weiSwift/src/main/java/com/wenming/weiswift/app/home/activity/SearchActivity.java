package com.wenming.weiswift.app.home.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.common.GreedDao.GreenDaoHelper;
import com.wenming.weiswift.app.common.GreedDao.UserDao;
import com.wenming.weiswift.app.common.GreedDao.model.User;
import com.wenming.weiswift.app.common.base.BaseAppCompatActivity;
import com.wenming.weiswift.app.home.adapter.SearchViewGreenDaoAdapter;
import com.wenming.weiswift.utils.DensityUtil;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import static android.R.attr.delay;

/**
 * Created by qhn on 2017/7/31.
 */

public class SearchActivity extends BaseAppCompatActivity {

    private SearchView searchView;
    private Button btn_search;
    UserDao userDao;
    GreenDaoHelper helper;
    String name = "";
    List<User> list;
    SearchViewGreenDaoAdapter adapter;
    Button searchGreendaoDelete;
    ListView searchGreendaoLv;
    private RelativeLayout searchGreendaoRl;
    private EditText textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);
        initView();
        initDbHelp();
        initDate();
    }

    private void initDate() {
        //搜索历史列表
        updateList();
        //搜索文本监听

    }
    private void updateList() {
        //查询所有
        list = userDao.queryBuilder().list();
        //这里用于判断是否有数据
        if (list.size()==0){
            searchGreendaoRl.setVisibility(View.VISIBLE);
            searchGreendaoDelete.setVisibility(View.GONE);
        }else {
            searchGreendaoRl.setVisibility(View.GONE);
            searchGreendaoDelete.setVisibility(View.VISIBLE);
        }
        //list倒序排列
        Collections.reverse(list);
        adapter = new SearchViewGreenDaoAdapter(mContext, list);
        searchGreendaoLv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void setText(String s){
        textView.setText(s);
        textView.setSelection(s.length());
    }

    private void initDbHelp() {
        helper = new GreenDaoHelper(this);
        userDao = helper.initDao().getUserDao();
    }


    private void initView() {
        final Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchGreendaoRl = (RelativeLayout) findViewById(R.id.search_greendao_rl);
        searchGreendaoDelete = (Button) findViewById(R.id.search_greendao_delete);
        searchGreendaoLv = (ListView) findViewById(R.id.search_greendao_lv);
        // 去掉下划线
        if (searchView != null) {
            try {        //--拿到字节码
                Class<?> argClass = searchView.getClass();
                //--指定某个私有属性,mSearchPlate是搜索框父布局的名字
                Field ownField = argClass.getDeclaredField("mSearchPlate");
                //--暴力反射,只有暴力反射才能拿到私有属性
                ownField.setAccessible(true);
                View mView = (View) ownField.get(searchView);
                //--设置背景
                mView.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
            btn_search = (Button) findViewById(R.id.btn_search);
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("key", name);
                    startActivity(intent);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            insertDB();
                        }
                    }, 1000);

                }
           });
            searchGreendaoDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delectAllDB();
                }
            });
            //设置字体
            int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
            //获取到TextView的控件
            textView = (EditText) searchView.findViewById(id);
            //设置字体大小为14sp
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            android.widget.LinearLayout.LayoutParams layoutParams = (android.widget.LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.bottomMargin = DensityUtil.dp2px(SearchActivity.this,-2.6f);
            textView.setLayoutParams(layoutParams);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(
            ) {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    name = query;
                    intent.putExtra("key", query);
                    startActivity(intent);
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            insertDB();
                        }
                    }, 1000);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    name = newText;
                    Log.e("newText---------", newText);
                    if (name.equals("")) {
//                    searchGreendaoLv.setFilterText(name);
                    } else {
//                    insertDB();
//                    searchGreendaoLv.clearTextFilter();
                    }

                    return false;
                }
            });
        }
    }
    private void delectAllDB() {
        try {
            userDao.deleteAll();
            list.clear();
            adapter.notifyDataSetChanged();
            searchGreendaoRl.setVisibility(View.VISIBLE);
            searchGreendaoDelete.setVisibility(View.GONE);
        } catch (Exception e) {
            Log.e("exception-----delete", "message:" + e.getMessage() + "");
        }
    }

    private void insertDB() {
        try {
            if (list.size() < 8) {
                //删除已经存在重复的搜索历史
                List<User> list2 = userDao.queryBuilder()
                        .where(UserDao.Properties.Name.eq(name)).build().list();
                userDao.deleteInTx(list2);
                //添加
                if (!name.equals(""))
                    userDao.insert(new User(null, name));
                Toast.makeText(mContext, "插入数据成功:" + name, Toast.LENGTH_SHORT).show();
            } else {
                //删除第一条数据，用于替换最后一条搜索
                userDao.delete(userDao.queryBuilder().list().get(0));
                //删除已经存在重复的搜索历史
                List<User> list3 = userDao.queryBuilder()
                        .where(UserDao.Properties.Name.eq(name)).build().list();
                userDao.deleteInTx(list3);
                //添加
                if (!name.equals(""))
                    userDao.insert(new User(null, name));
            }
            updateList();
        } catch (Exception e) {
            Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
        }

    }

}

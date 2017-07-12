package com.wenming.weiswift.widget.endlessrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wenming.weiswift.R;
import com.wenming.weiswift.app.home.activity.ExpertActivity;
import com.wenming.weiswift.app.home.activity.QuestionActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.log.LoggerDefault;

import static com.wenming.library.upload.UploadService.TAG;

/**
 * Created by cundong on 2015/10/9.
 * <p/>
 * RecyclerView.Adapter with Header and Footer
 */
public class HeaderAndFooterRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER_VIEW = Integer.MIN_VALUE + 1;

    /**
     * RecyclerView使用的，真正的Adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mInnerAdapter;

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            int headerViewsCountCount = getHeaderViewsCount();
            notifyItemRangeChanged(fromPosition + headerViewsCountCount, toPosition + headerViewsCountCount + itemCount);
        }
    };

    public HeaderAndFooterRecyclerViewAdapter() {
    }

    public HeaderAndFooterRecyclerViewAdapter(RecyclerView.Adapter innerAdapter) {
        setAdapter(innerAdapter);
    }

    /**
     * 设置adapter
     *
     * @param adapter
     */
    public void setAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {

        if (adapter != null) {
            if (!(adapter instanceof RecyclerView.Adapter))
                throw new RuntimeException("your adapter must be a RecyclerView.Adapter");
        }

        if (mInnerAdapter != null) {
            notifyItemRangeRemoved(getHeaderViewsCount(), mInnerAdapter.getItemCount());
            mInnerAdapter.unregisterAdapterDataObserver(mDataObserver);
        }

        this.mInnerAdapter = adapter;
        mInnerAdapter.registerAdapterDataObserver(mDataObserver);
        notifyItemRangeInserted(getHeaderViewsCount(), mInnerAdapter.getItemCount());
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    public void addHeaderView(View header) {

        if (header == null) {
            throw new RuntimeException("header is null");
        }

        mHeaderViews.add(header);
        this.notifyDataSetChanged();
    }

    public void addFooterView(View footer) {

        if (footer == null) {
            throw new RuntimeException("footer is null");
        }

        mFooterViews.add(footer);
        this.notifyDataSetChanged();
    }

    /**
     * 返回第一个FoView
     *
     * @return
     */
    public View getFooterView() {
        return getFooterViewsCount() > 0 ? mFooterViews.get(0) : null;
    }

    /**
     * 返回第一个HeaderView
     *
     * @return
     */
    public View getHeaderView() {
        return getHeaderViewsCount() > 0 ? mHeaderViews.get(0) : null;
    }

    public void removeHeaderView(View view) {
        mHeaderViews.remove(view);
        this.notifyDataSetChanged();
    }

    public void removeFooterView(View view) {
        mFooterViews.remove(view);
        this.notifyDataSetChanged();
    }

    public int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews.size();
    }

    public boolean isHeader(int position) {
        return getHeaderViewsCount() > 0 && position == 0;
    }

    public boolean isFooter(int position) {
        int lastPosition = getItemCount() - 1;
        return getFooterViewsCount() > 0 && position == lastPosition;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int headerViewsCountCount = getHeaderViewsCount();
        if (viewType < TYPE_HEADER_VIEW + headerViewsCountCount) {
            Log.d(TAG, "onCreateViewHolder: 头部");
            if(mHeaderViews!=null){
            return new ViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW),parent.getContext());
            }
            return new ViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW));
        } else if (viewType >= TYPE_FOOTER_VIEW && viewType < Integer.MAX_VALUE / 2) {
            Log.d(TAG, "onCreateViewHolder: 尾部部");
            return new ViewHolder(mFooterViews.get(viewType - TYPE_FOOTER_VIEW));
        } else {
            Log.d(TAG, "onCreateViewHolder: 中间");
            return mInnerAdapter.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int headerViewsCountCount = getHeaderViewsCount();
        if (position >= headerViewsCountCount && position < headerViewsCountCount + mInnerAdapter.getItemCount()) {
            mInnerAdapter.onBindViewHolder(holder, position - headerViewsCountCount);
            Log.d("ADFADF", "onBindViewHolder: 中间");
        } else {
            Log.d("ADFADF", "onBindViewHolder: 头");
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                Log.d("ADFADF", "onBindViewHolder: 尾");
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderViewsCount() + getFooterViewsCount() + mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        int innerCount = mInnerAdapter.getItemCount();
        int headerViewsCountCount = getHeaderViewsCount();
        if (position < headerViewsCountCount) {
            return TYPE_HEADER_VIEW + position;
        } else if (headerViewsCountCount <= position && position < headerViewsCountCount + innerCount) {

            int innerItemViewType = mInnerAdapter.getItemViewType(position - headerViewsCountCount);
            if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return innerItemViewType + Integer.MAX_VALUE / 2;
        } else {
            return TYPE_FOOTER_VIEW + position - headerViewsCountCount - innerCount;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_expert;
        LinearLayout ll_question;
        public ViewHolder(View itemView){
            super(itemView);
        }
        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            ll_expert = (LinearLayout) itemView.findViewById(R.id.ll_expert);
            if (ll_expert != null) {
                ll_expert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, ExpertActivity.class));
                    }
                });
            }
            ll_question = (LinearLayout) itemView.findViewById(R.id.ll_question);
            if (ll_question!=null) {
                ll_question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, QuestionActivity.class));
                    }
                });
            }
        }
    }
//    public  class HeaderViewHolder extends RecyclerView.ViewHolder {
//         LinearLayout ll_expert;
//         LinearLayout ll_question;
//
//        public HeaderViewHolder(View itemView, final Context context) {
//            super(itemView);
//            ll_expert = (LinearLayout) itemView.findViewById(R.id.ll_expert);
//            if (ll_expert != null) {
//                ll_expert.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, ExpertActivity.class));
//                    }
//                });
//            }
//            ll_question = (LinearLayout) itemView.findViewById(R.id.ll_question);
//            if (ll_question!=null) {
//                ll_question.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        context.startActivity(new Intent(context, QuestionActivity.class));
//                    }
//                });
//            }
//        }
//    }
}

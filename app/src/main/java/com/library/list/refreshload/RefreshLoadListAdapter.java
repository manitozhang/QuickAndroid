package com.library.list.refreshload;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.common.http.bean.ExampleListBean;
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter;
import com.common.library.bravh_rvadapter.RecyclerViewHolder;
import com.library.R;

import java.util.List;

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/18
 * <p>
 * @Desc:
 */
public class RefreshLoadListAdapter extends BaseRecyclerAdapter<ExampleListBean, RecyclerViewHolder> {
    public RefreshLoadListAdapter(@Nullable List<ExampleListBean> data) {
        super(R.layout.item_refresh_layout, data);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, ExampleListBean item) {
        //注册子View的点击事件,必须,否则在Act点击事件不生效
        holder.addOnClickListener(R.id.btn_click_me);
        TextView tvTitle = holder.getTextView(R.id.tv_title);
        TextView tvContent = holder.getTextView(R.id.tv_content);
        TextView tvTime = holder.getTextView(R.id.tv_time);
        tvTitle.setText(item.getTitle());
        tvContent.setText(item.getContent());
        tvTime.setText(item.getTime());
    }
}

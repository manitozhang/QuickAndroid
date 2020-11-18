package com.library.list.singlechoice;

import android.widget.ImageView;
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
public class SingleChoiceListAdapter extends BaseRecyclerAdapter<ExampleListBean, RecyclerViewHolder> {
    /**
     * 定义选中的下标, 默认-1
     */
    private int selPos = -1;

    public void setSelPos(int selPos) {
        this.selPos = selPos;
    }

    public int getSelPos() {
        return selPos;
    }

    public SingleChoiceListAdapter(@Nullable List<ExampleListBean> data) {
        super(R.layout.item_single_choice, data);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, ExampleListBean item) {
        //注册子View的点击事件,必须,否则在Act点击事件不生效
        holder.addOnClickListener(R.id.iv_select);
        ImageView ivSelect = holder.getImageView(R.id.iv_select);
        //因为在布局中定义了一个选中与非选中的drawable,select_list_item.xml
        //如果传进来的选中下标等于当前下标,就给设置选中状态
        ivSelect.setSelected(selPos == holder.getAdapterPosition());
        TextView tvTitle = holder.getTextView(R.id.tv_title);
        TextView tvContent = holder.getTextView(R.id.tv_content);
        TextView tvTime = holder.getTextView(R.id.tv_time);
        tvTitle.setText(item.getTitle());
        tvContent.setText(item.getContent());
        tvTime.setText(item.getTime());
    }
}

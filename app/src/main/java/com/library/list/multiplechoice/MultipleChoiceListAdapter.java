package com.library.list.multiplechoice;

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
 * @Desc: 多选列表适配器
 */
public class MultipleChoiceListAdapter extends BaseRecyclerAdapter<ExampleListBean, RecyclerViewHolder> {

    public MultipleChoiceListAdapter(@Nullable List<ExampleListBean> data) {
        super(R.layout.item_single_choice, data);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, ExampleListBean item) {
        //注册子View的点击事件,必须,否则在Act点击事件不生效
        holder.addOnClickListener(R.id.iv_select);
        ImageView ivSelect = holder.getImageView(R.id.iv_select);
        //因为在布局中定义了一个选中与非选中的drawable,select_list_item.xml
        //如果实体类里面的标识符为选中状态,就给他选中
        ivSelect.setSelected(item.isChecked());
        TextView tvTitle = holder.getTextView(R.id.tv_title);
        TextView tvContent = holder.getTextView(R.id.tv_content);
        TextView tvTime = holder.getTextView(R.id.tv_time);
        tvTitle.setText(item.getTitle());
        tvContent.setText(item.getContent());
        tvTime.setText(item.getTime());
    }
}

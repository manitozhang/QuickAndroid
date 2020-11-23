package com.library.list.singlechoice

import com.common.http.bean.ExampleListBean
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter
import com.common.library.bravh_rvadapter.RecyclerViewHolder
import com.library.R

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/18
 *
 *
 * @Desc:
 */
class SingleChoiceListAdapter(data: List<ExampleListBean?>?) : BaseRecyclerAdapter<ExampleListBean, RecyclerViewHolder>(R.layout.item_single_choice, data) {
    /**
     * 定义选中的下标, 默认-1
     */
    var selPos = -1

    override fun convert(holder: RecyclerViewHolder, item: ExampleListBean) { //注册子View的点击事件,必须,否则在Act点击事件不生效
        holder.addOnClickListener(R.id.iv_select)
        val ivSelect = holder.getImageView(R.id.iv_select)
        //因为在布局中定义了一个选中与非选中的drawable,select_list_item.xml
        //如果传进来的选中下标等于当前下标,就给设置选中状态
        ivSelect.isSelected = selPos == holder.adapterPosition
        val tvTitle = holder.getTextView(R.id.tv_title)
        val tvContent = holder.getTextView(R.id.tv_content)
        val tvTime = holder.getTextView(R.id.tv_time)
        tvTitle.text = item.title
        tvContent.text = item.content
        tvTime.text = item.time
    }
}
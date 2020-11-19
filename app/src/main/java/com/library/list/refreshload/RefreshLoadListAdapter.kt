package com.library.list.refreshload

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
class RefreshLoadListAdapter(data: List<ExampleListBean>) : BaseRecyclerAdapter<ExampleListBean, RecyclerViewHolder>(R.layout.item_refresh_layout, data) {
    override fun convert(holder: RecyclerViewHolder, item: ExampleListBean) { //注册子View的点击事件,必须,否则在Act点击事件不生效
        holder.addOnClickListener(R.id.btn_click_me)
        val tvTitle = holder.getTextView(R.id.tv_title)
        val tvContent = holder.getTextView(R.id.tv_content)
        val tvTime = holder.getTextView(R.id.tv_time)
        tvTitle.text = item.title
        tvContent.text = item.content
        tvTime.text = item.time
    }
}
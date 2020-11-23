package com.library.list.multiplechoice

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.common.base.BaseActivity
import com.common.http.XRetrofit.api
import com.common.http.base.BaseObserver
import com.common.http.base.RxJavaHelper.observeOnMainThread
import com.common.http.bean.ExampleListBean
import com.common.http.helper.Mobile
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter.OnItemChildClickListener
import com.common.weight.CommonRecyclerView
import com.library.R
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_multiple_choice_list.*
import java.util.*

/**
 * 多选列表
 * 类似于列表收藏,点赞,关注都属于多选
 */
class MultipleChoiceListActivity : BaseActivity() {
    private var multipleChoiceListAdapter: MultipleChoiceListAdapter? = null
    private val page = 1
    private val pageNum = 20
    override val layout: Int
        get() = R.layout.activity_multiple_choice_list

    override fun initViewIds() {
    }

    override fun initView() {
        initMultipleRecycler()
    }

    private fun initMultipleRecycler() {
        val layoutManager = LinearLayoutManager(this)
        rv_multiple_choice!!.layoutManager = layoutManager
        multipleChoiceListAdapter = MultipleChoiceListAdapter(emptyList())
        rv_multiple_choice!!.adapter = multipleChoiceListAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        getList(page, pageNum)
    }

    override fun initListener() {
        super.initListener()
        multipleChoiceListAdapter!!.onItemChildClickListener = OnItemChildClickListener { adapter: BaseRecyclerAdapter<*, *>?, view: View?, position: Int ->
            val bean = multipleChoiceListAdapter!!.data[position]
            bean.isChecked = !bean.isChecked
            multipleChoiceListAdapter!!.notifyItemChanged(position)
        }
    }

    /**
     * 获取集合
     *
     * @param page:    页数
     * @param pageNum: 条目数
     */
    private fun getList(page: Int, pageNum: Int) {
        api
                ?.getList(Mobile.getList(page, pageNum))
                ?.compose(observeOnMainThread())
                ?.subscribe()
//                ?.subscribe(object : BaseObserver<List<ExampleListBean?>>() {
//                    override fun onSuccess(response: List<ExampleListBean?>) {
//                        if (response == null) return
//                        multipleChoiceListAdapter!!.setNewData(response)
//                    }
//                })
    }

    /**
     * 获取选中下标
     *
     * @param view
     */
    fun btnGetMutliplePos(view: View?) {
        val selectPosList: MutableList<Int> = ArrayList()
        for (i in multipleChoiceListAdapter!!.data.indices) {
            if (multipleChoiceListAdapter!!.data[i].isChecked) selectPosList.add(i)
        }
        ToastUtils.showShort("选中的下标为---$selectPosList")
    }

    /**
     * 选中全部
     *
     * @param view
     */
    fun btnChooseAll(view: View?) { //遍历数据
        for (data in multipleChoiceListAdapter!!.data) { //全部设置为选中
            data.isChecked = true
        }
        multipleChoiceListAdapter!!.notifyDataSetChanged()
    }

    /**
     * 全不选
     *
     * @param view
     */
    fun btnUnChooseAll(view: View?) { //遍历数据
        for (data in multipleChoiceListAdapter!!.data) { //全部设置为非选中
            data.isChecked = false
        }
        multipleChoiceListAdapter!!.notifyDataSetChanged()
    }

    /**
     * 反选
     *
     * @param view
     */
    fun btnChooseReverse(view: View?) { //遍历数据
        for (data in multipleChoiceListAdapter!!.data) { //将状态改为反状态
            data.isChecked = !data.isChecked
        }
        multipleChoiceListAdapter!!.notifyDataSetChanged()
    }
}

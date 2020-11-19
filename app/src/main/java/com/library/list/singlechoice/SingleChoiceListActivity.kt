package com.library.list.singlechoice

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
import kotlinx.android.synthetic.main.activity_single_choice_list.*

/**
 * 单选列表Activity
 */
class SingleChoiceListActivity : BaseActivity() {
    private var singleChoiceListAdapter: SingleChoiceListAdapter? = null
    private val page = 1
    private val pageNum = 20
    override val layout: Int
        get() = R.layout.activity_single_choice_list

    override fun initViewIds() {
    }

    override fun initView() {
        initSingleChoiceRecycler()
    }

    private fun initSingleChoiceRecycler() {
        val layoutManager = LinearLayoutManager(this)
        rv_single_choice!!.layoutManager = layoutManager
        singleChoiceListAdapter = SingleChoiceListAdapter(emptyList())
        rv_single_choice!!.adapter = singleChoiceListAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        getList(page, pageNum)
    }

    override fun initListener() {
        super.initListener()
        singleChoiceListAdapter!!.onItemChildClickListener = OnItemChildClickListener { adapter: BaseRecyclerAdapter<*, *>?, view: View, position: Int ->
            if (view.id == R.id.iv_select) { //点击按钮,将当前点击的下标传进去,刷新适配器
                singleChoiceListAdapter!!.selPos = position
                singleChoiceListAdapter!!.notifyDataSetChanged()
            }
        }
    }

    /**
     * 获取集合
     *
     * @param page:    页数
     * @param pageNum: 条目数
     */
    private fun getList(page: Int, pageNum: Int) {
        api?.getList(Mobile.getList(page, pageNum))
                ?.compose(observeOnMainThread())
                ?.subscribe(object : BaseObserver<List<ExampleListBean>>() {
                    override fun onSuccess(response: List<ExampleListBean>) {
                        singleChoiceListAdapter!!.setNewData(response)
                    }
                })
    }

    /**
     * 获取选中的下标
     *
     * @param view
     */
    fun btnGetSingleData(view: View?) {
        if (singleChoiceListAdapter!!.selPos == -1) {
            ToastUtils.showShort("你还未选中数据")
            return
        }
        ToastUtils.showShort("选中的标题是---"
                + singleChoiceListAdapter!!.data[singleChoiceListAdapter!!.selPos].title)
    }
}
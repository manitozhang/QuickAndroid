package com.library.list.refreshload

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.common.base.BaseActivity
import com.common.http.XRetrofit.api
import com.common.http.base.BaseObserver
import com.common.http.base.RxJavaHelper.observeOnMainThread
import com.common.http.bean.ExampleListBean
import com.common.http.helper.Mobile.getList
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter.OnItemChildClickListener
import com.common.weight.CommonRecyclerView
import com.common.weight.CommonSmartRefreshLayout
import com.library.R
import com.scwang.smart.refresh.layout.api.RefreshLayout

/**
 * 刷新加载集合
 *
 * @Link: https://github.com/scwang90/SmartRefreshLayout
 */
class RefreshLoadListActivity : BaseActivity() {
    private var page = 1
    /**
     * 每次固定请求10条
     */
    private val PAGE_NUM = 10
    private var refreshLayout: CommonSmartRefreshLayout? = null
    private var rvRefresh: CommonRecyclerView? = null
    private var refreshLoadListAdapter: RefreshLoadListAdapter? = null
    override val layout: Int
        get() = R.layout.activity_refresh_load_list

    override fun initViewIds() {
        refreshLayout = findViewById(R.id.refresh_layout)
        rvRefresh = findViewById(R.id.rv_refresh)
    }

    override fun initView() { //到底部是否自动加载更多,不显示上拉的效果.默认为true
        refreshLayout!!.setEnableAutoLoadMore(false)
        initRefreshRecycler()
    }

    private fun initRefreshRecycler() {
        val layoutManager = LinearLayoutManager(this)
        rvRefresh!!.layoutManager = layoutManager
        refreshLoadListAdapter = RefreshLoadListAdapter(emptyList())
        rvRefresh!!.adapter = refreshLoadListAdapter
    }

    override fun initData(savedInstanceState: Bundle?) {
        getList(page)
    }

    override fun initListener() {
        super.initListener()
        refreshLayout!!.setOnRefreshListener { refreshLayout: RefreshLayout ->
            //刷新,页数是1
            page = 1
            getList(page)
            refreshLayout.finishRefresh()
        }
        refreshLayout!!.setOnLoadMoreListener { refreshLayout: RefreshLayout ->
            //加载,页数往上+1
            page++
            getList(page)
            refreshLayout.finishLoadMore()
        }
        refreshLoadListAdapter!!.onItemClickListener = BaseRecyclerAdapter.OnItemClickListener { adapter: BaseRecyclerAdapter<*, *>?, view: View?, position: Int -> ToastUtils.showShort("点击条目下标---$position") }
        refreshLoadListAdapter!!.onItemChildClickListener = OnItemChildClickListener { adapter: BaseRecyclerAdapter<*, *>?, view: View, position: Int ->
            if (view.id == R.id.btn_click_me) {
                ToastUtils.showShort("点击按钮下标---$position")
            }
        }
    }

    /**
     * 获取集合
     *
     * @param page: 页数
     */
    private fun getList(page: Int) {
        api?.getList(getList(page, PAGE_NUM))
                ?.compose(observeOnMainThread())
                ?.subscribe(object : BaseObserver<List<ExampleListBean>>() {
                    override fun onSuccess(response: List<ExampleListBean>) {
                        if (response == null) return
                        if (page == 1) { //page==1,为刷新,直接作为新数据set进来
                            refreshLoadListAdapter!!.setNewData(response)
                        } else { //不为空就往后加
                            refreshLoadListAdapter!!.addData(response)
                        }
                    }
                })
    }
}
package com.common.weight

import android.content.Context
import android.util.AttributeSet
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/18
 *
 *
 * @Desc: 重写SmartRefreshLayout 定义一些公共的参数或属性,避免在每一个需要的业务代码中定义
 * @Link: https://github.com/scwang90/SmartRefreshLayout
 * //下面示例中的值等于默认值
 * RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
 * refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
 * refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
 * refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）
 *
 *
 * refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
 * refreshLayout.setHeaderHeightPx(100);//同上-像素为单位 （V1.1.0删除）
 * refreshLayout.setFooterHeight(100);//Footer标准高度（显示上拉高度>=标准高度 触发加载）
 * refreshLayout.setFooterHeightPx(100);//同上-像素为单位 （V1.1.0删除）
 *
 *
 * refreshLayout.setFooterHeaderInsetStart(0);//设置 Header 起始位置偏移量 1.0.5
 * refreshLayout.setFooterHeaderInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
 * refreshLayout.setFooterFooterInsetStart(0);//设置 Footer 起始位置偏移量 1.0.5
 * refreshLayout.setFooterFooterInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
 *
 *
 * refreshLayout.setHeaderMaxDragRate(2);//最大显示下拉高度/Header标准高度
 * refreshLayout.setFooterMaxDragRate(2);//最大显示下拉高度/Footer标准高度
 * refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
 * refreshLayout.setFooterTriggerRate(1);//触发加载距离 与 FooterHeight 的比率1.0.4
 *
 *
 * refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
 * refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
 * refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
 * refreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
 * refreshLayout.setEnableNestedScroll(false);//是否启用嵌套滚动
 * refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
 * refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
 * refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
 * refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
 * refreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
 * refreshLayout.setEnableFooterFollowWhenLoadFinished(false);//是否在全部加载结束之后Footer跟随内容1.0.4
 * refreshLayout.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4
 *
 *
 * refreshLayout.setEnableScrollContentWhenRefreshed(true);//是否在刷新完成时滚动列表显示新的内容 1.0.5
 * refreshLayout.srlEnableClipHeaderWhenFixedBehind(true);//是否剪裁Header当时样式为FixedBehind时1.0.5
 * refreshLayout.srlEnableClipFooterWhenFixedBehind(true);//是否剪裁Footer当时样式为FixedBehind时1.0.5
 *
 *
 * refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
 * refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
 *
 *
 * refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener());//设置多功能监听器
 * refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider());//设置滚动边界判断
 * refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter());//自定义滚动边界
 *
 *
 * refreshLayout.setRefreshHeader(new ClassicsHeader(context));//设置Header
 * refreshLayout.setRefreshFooter(new ClassicsFooter(context));//设置Footer
 * refreshLayout.setRefreshContent(new View(context));//设置刷新Content（用于非xml布局代替addView）1.0.4
 *
 *
 * refreshLayout.autoRefresh();//自动刷新
 * refreshLayout.autoLoadMore();//自动加载
 * refreshLayout.autoRefreshAnimationOnly();//自动刷新，只显示动画不执行刷新
 * refreshLayout.autoLoadMoreAnimationOnly();//自动加载，只显示动画不执行加载
 * refreshLayout.autoRefresh(400);//延迟400毫秒后自动刷新
 * refreshLayout.autoLoadMore(400);//延迟400毫秒后自动加载
 * refreshLayout.finishRefresh();//结束刷新
 * refreshLayout.finishLoadMore();//结束加载
 * refreshLayout.finishRefresh(3000);//延迟3000毫秒后结束刷新
 * refreshLayout.finishLoadMore(3000);//延迟3000毫秒后结束加载
 * refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
 * refreshLayout.finishLoadMore(false);//结束加载（加载失败）
 * refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
 * refreshLayout.closeHeaderOrFooter();//关闭正在打开状态的 Header 或者 Footer（1.1.0）
 * refreshLayout.resetNoMoreData();//恢复没有更多数据的原始状态 1.0.4（1.1.0删除）
 * refreshLayout.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5
 */
class CommonSmartRefreshLayout : SmartRefreshLayout {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setRefreshHeader(ClassicsHeader(context))
        setRefreshFooter(ClassicsFooter(context))
    }
}
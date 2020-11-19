package com.common.http.bean

/**
 * @Author: 张鹏飞
 * @Email: 1271396448@qq.com
 * @Date: 2020/11/18
 *
 *
 * @Desc: 集合例子Bean
 */
class ExampleListBean {
    var id = 0
    var title: String? = null
    var content: String? = null
    var time: String? = null
    /**
     * 多选用到,如果选中就设为true,未选中就设置为false
     */
    var isChecked = false

}
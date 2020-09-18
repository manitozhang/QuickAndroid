package com.common.http.bean;

/**
 * @Author: 张鹏飞
 * @Date: 2020/9/3
 * @Email: 1271396448@qq.com
 * <p>
 * @Desc:
 */
public class ExampleBean {

    private long id;
    private long userId;
    private String title;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

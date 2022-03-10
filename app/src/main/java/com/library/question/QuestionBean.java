package com.library.question;

import android.util.Log;

import com.common.library.bravh_rvadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * @Author: Created by seven.zhang
 * @Date: 2022/3/10 11:03
 * @Desc:
 */
public class QuestionBean {
    /**
     * 单选
     */
    public static int TYPE_SINGLE_CHOICE = 1;
    /**
     * 多选
     */
    public static int TYPE_MULTI_CHOICE = 2;
    /**
     * 填空
     */
    public static int TYPE_FILL_BLANKS = 3;

    private List<QuestionData> data;

    public List<QuestionData> getData() {
        return data;
    }

    static class QuestionData implements MultiItemEntity{
        private int id;
        private String title;
        private String rightAnswer;
        private List<AnswerData> answer;
        private String typeDesc;
        /**
         * 用于单选,单选的下标
         */
        private int checkedId = -1;

        public int getCheckedId() {
            return checkedId;
        }

        public void setCheckedId(int checkedId) {
            this.checkedId = checkedId;
        }

        /**
         * 用于填空,输入的填空内容
         */
        private String inputAnswer;

        public String getInputAnswer() {
            return inputAnswer;
        }

        public void setInputAnswer(String inputAnswer) {
            this.inputAnswer = inputAnswer;
        }

        /**
         * 问题类型
         * 1: 单选
         * 2: 多选
         * 3: 填空
         */
        private int type;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getRightAnswer() {
            return rightAnswer;
        }

        public List<AnswerData> getAnswer() {
            return answer;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public int getType() {
            return type;
        }

        @Override
        public int getItemType() {
            return type;
        }

        static class AnswerData{
            /**
             * 用于多选,是否选择
             */
            private boolean isChecked = false;
            private int id;
            private String answer;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public int getId() {
                return id;
            }

            public String getAnswer() {
                return answer;
            }
        }
    }

}

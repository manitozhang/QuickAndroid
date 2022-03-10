package com.library.question;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.Utils;
import com.common.library.bravh_rvadapter.BaseMultiItemRecyclerAdapter;
import com.common.library.bravh_rvadapter.BaseRecyclerAdapter;
import com.common.library.bravh_rvadapter.MultipleItemRvAdapter;
import com.common.library.bravh_rvadapter.RecyclerViewHolder;
import com.common.weight.CommonRecyclerView;
import com.library.R;

import java.util.List;

/**
 * @Author: Created by seven.zhang
 * @Date: 2022/3/9 18:52
 * @Desc:
 */
public class QuestionAdapter extends BaseMultiItemRecyclerAdapter<QuestionBean.QuestionData, RecyclerViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public QuestionAdapter(List<QuestionBean.QuestionData> data) {
        super(data);
        addItemType(QuestionBean.TYPE_SINGLE_CHOICE, R.layout.item_question_type_1);
        addItemType(QuestionBean.TYPE_MULTI_CHOICE, R.layout.item_question_type_2);
        addItemType(QuestionBean.TYPE_FILL_BLANKS, R.layout.item_question_type_3);
    }

    @Override
    protected void convert(RecyclerViewHolder holder, QuestionBean.QuestionData item) {
        TextView tvTitle = holder.getTextView(R.id.tv_title);
        tvTitle.setText(item.getTitle() + " （" + item.getTypeDesc() + "）");
        if (item.getItemType() == QuestionBean.TYPE_SINGLE_CHOICE || item.getItemType() == QuestionBean.TYPE_MULTI_CHOICE) {
            //单选或者多选
            CommonRecyclerView rvAnswer = holder.findViewById(R.id.rv_answer);
            initAnswerRecycler(rvAnswer, item, item.getItemType(), item.getAnswer());
        } else {
            //填空
            EditText etAnswer = holder.findViewById(R.id.et_answer);
            etAnswer.setText(item.getInputAnswer());
            etAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    item.setInputAnswer(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    /**
     * 初始化答案列表适配器
     *
     * @param rvAnswer
     * @param type
     * @param answerList
     */
    private void initAnswerRecycler(CommonRecyclerView rvAnswer,QuestionBean.QuestionData item, int type, List<QuestionBean.QuestionData.AnswerData> answerList) {
        rvAnswer.setLayoutManager(new GridLayoutManager(Utils.getApp(), 2));
        AnswerAdapter answerAdapter = new AnswerAdapter(item, type, answerList);
        rvAnswer.setAdapter(answerAdapter);
        answerAdapter.setmOnItemClickListener((type1, answerId) -> {
            if (type1 == QuestionBean.TYPE_SINGLE_CHOICE) {
                item.setCheckedId(answerId);
                answerAdapter.notifyDataSetChanged();
            }
        });

    }

}

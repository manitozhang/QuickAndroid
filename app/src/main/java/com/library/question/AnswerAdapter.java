package com.library.question;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.common.library.bravh_rvadapter.BaseRecyclerAdapter;
import com.common.library.bravh_rvadapter.RecyclerViewHolder;
import com.library.R;

import java.util.List;

/**
 * @Author: Created by seven.zhang
 * @Date: 2022/3/10 15:43
 * @Desc:
 */
public class AnswerAdapter extends BaseRecyclerAdapter<QuestionBean.QuestionData.AnswerData, RecyclerViewHolder> {
    private OnItemClickListener onItemClickListener;
    /**
     * 类型
     * {@link QuestionBean }
     * 1: 单选
     * 2: 多选
     */
    private int type;
    /**
     * 单选的下标
     */
    private QuestionBean.QuestionData questionItem;

    public int getType() {
        return type;
    }

    public AnswerAdapter(QuestionBean.QuestionData questionItem, int type, @Nullable List<QuestionBean.QuestionData.AnswerData> data) {
        super(R.layout.item_question_answer, data);
        this.questionItem = questionItem;
        this.type = type;
    }

    @Override
    protected void convert(RecyclerViewHolder holder, QuestionBean.QuestionData.AnswerData item) {
        ImageView ivSelect = holder.getImageView(R.id.iv_select);
        TextView tvAnswer = holder.getTextView(R.id.tv_answer);
        tvAnswer.setText(item.getAnswer());
        ivSelect.setSelected(type == QuestionBean.TYPE_MULTI_CHOICE ? item.isChecked() : item.getId() == questionItem.getCheckedId());
        holder.itemView.setOnClickListener(v -> {
            if (type == QuestionBean.TYPE_SINGLE_CHOICE) {
                //单选
                onItemClickListener.onItemClick(type, item.getId());
            } else {
                //多选
                item.setChecked(!item.isChecked());
                ivSelect.setSelected(item.isChecked());
            }
        });
    }

    interface OnItemClickListener {
        void onItemClick(int type, int answerId);
    }

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

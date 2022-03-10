package com.library.question;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.common.base.BaseActivity;
import com.common.weight.CommonRecyclerView;
import com.library.R;

import java.util.ArrayList;

/**
 * 答题页面
 */
public class QuestionActivity extends BaseActivity {

    private CommonRecyclerView rvQuestion;
    private String TAG = "QuestionActivity";
    private QuestionAdapter questionAdapter;
    private Button btnShowAnswer;
    private TextView tvAllAnswer;

    @Override
    public int getLayout() {
        return R.layout.activity_question;
    }

    @Override
    public void initViewIds() {
        rvQuestion = findViewById(R.id.rv_question);
        btnShowAnswer = findViewById(R.id.btn_show_answer);
        tvAllAnswer = findViewById(R.id.tv_all_answer);
    }

    @Override
    public void initView() {
        initQuestionRecycler();
    }

    /**
     * 初始化问题列表
     */
    private void initQuestionRecycler() {
        questionAdapter = new QuestionAdapter(new ArrayList<>());
        rvQuestion.setLayoutManager(new LinearLayoutManager(this));
        rvQuestion.setAdapter(questionAdapter);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        formatQuestionJson();
    }

    @Override
    public void initListener() {
        super.initListener();
        btnShowAnswer.setOnClickListener(v -> {
            StringBuilder stringBuilder = new StringBuilder();
            //显示所有答案
            for (QuestionBean.QuestionData questionItem : questionAdapter.getData()) {
                if (questionItem.getType() == QuestionBean.TYPE_SINGLE_CHOICE) {
                    //单选
                    stringBuilder.append("问题id: " + questionItem.getId() + " 答案id: " + questionItem.getCheckedId() + "\n");
                } else if (questionItem.getType() == QuestionBean.TYPE_MULTI_CHOICE) {
                    //多选
                    ArrayList<Integer> answerIdList = new ArrayList<>();
                    for (QuestionBean.QuestionData.AnswerData answerItem : questionItem.getAnswer()) {
                        if (answerItem.isChecked()) {
                            answerIdList.add(answerItem.getId());
                        }
                    }
                    stringBuilder.append("问题id: " + questionItem.getId() + " 答案id: " + answerIdList.toString() + "\n");
                } else if (questionItem.getType() == QuestionBean.TYPE_FILL_BLANKS) {
                    //填空
                    stringBuilder.append("问题id: " + questionItem.getId() + " 答案: " + questionItem.getInputAnswer() + "\n");
                }
            }
            tvAllAnswer.setText(stringBuilder);
        });
    }

    /**
     * 格式化问题的json
     */
    private void formatQuestionJson() {
        String questionJsonStr = ResourceUtils.readAssets2String("question.json");
        QuestionBean questionBean = GsonUtils.fromJson(questionJsonStr, QuestionBean.class);
        questionAdapter.setNewData(questionBean.getData());
    }
}
package com.walter.covid19app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.walter.covid19app.Questions;
import com.walter.covid19app.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    List<Questions> questionList;

    public QuestionAdapter(List<Questions> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Questions questions = questionList.get(position);
        holder.question.setText(questions.getQuestion());
        holder.answer.setText(questions.getAnswers());

        boolean isExpandable = questionList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView question, answer;
        LinearLayout questionItem;
        RelativeLayout expandableLayout;

        public ViewHolder(@NonNull View item) {
            super(item);

            question = item.findViewById(R.id.questionTv);
            answer = item.findViewById(R.id.answerTv);

            questionItem = item.findViewById(R.id.questionItem);
            expandableLayout = item.findViewById(R.id.expandableLayout);

            questionItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Questions questions = questionList.get(getAdapterPosition());
                    questions.setExpandable(!questions.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

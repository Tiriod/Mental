package com.example.mental.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.TestCardItem;
import com.example.mental.R;

import java.util.List;

public class TestCardItemAdapter extends RecyclerView.Adapter<TestCardItemAdapter.CardItemViewHolder> {
    private List<TestCardItem> cardItemList;

    public TestCardItemAdapter(List<TestCardItem> cardItemList) {
        this.cardItemList = cardItemList;
    }

    @NonNull
    @Override
    public CardItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_testcard, parent, false);
        return new CardItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardItemViewHolder holder, int position) {
        TestCardItem testCardItem = cardItemList.get(position);
        holder.cardImageView.setImageResource(testCardItem.getImageResId());
        holder.cardQuestionTextView.setText(testCardItem.getQuestion());
        holder.choiceARadioButton.setText(testCardItem.getChoiceA());
        holder.choiceBRadioButton.setText(testCardItem.getChoiceB());
        holder.choiceCRadioButton.setText(testCardItem.getChoiceC());

        // 在这里添加逻辑来处理单选按钮的选择事件
    }

    @Override
    public int getItemCount() {
        return cardItemList.size();
    }

    static class CardItemViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImageView;
        TextView cardQuestionTextView;
        RadioButton choiceARadioButton;
        RadioButton choiceBRadioButton;
        RadioButton choiceCRadioButton;

        public CardItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImageView = itemView.findViewById(R.id.card_test_image);
            cardQuestionTextView = itemView.findViewById(R.id.card_test_question);
            choiceARadioButton = itemView.findViewById(R.id.card_test_choiceA);
            choiceBRadioButton = itemView.findViewById(R.id.card_test_choiceB);
            choiceCRadioButton = itemView.findViewById(R.id.card_test_choiceC);
        }
    }
}

package com.example.english.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.english.R;
import com.example.english.pojo.Word;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<Word> words;
    private OnWordClickListener onWordClickListener;

    public interface OnWordClickListener {
        void onWordClick(int position);
    }

    public void setOnWordClickListener(OnWordClickListener onWordClickListener) {
        this.onWordClickListener = onWordClickListener;
    }

    public WordAdapter(List<Word> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        holder.textViewEnglish.setText(word.getEnglish());
        holder.textViewRussian.setText(word.getRussian());

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewEnglish;
        private TextView textViewRussian;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
            textViewRussian = itemView.findViewById(R.id.textViewRussian);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onWordClickListener != null) {
                        onWordClickListener.onWordClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}

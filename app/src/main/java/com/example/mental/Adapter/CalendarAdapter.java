package com.example.mental.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mental.Definition.CalendarDay;
import com.example.mental.R;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<String> daysOfWeek;
    private List<String> weekDates;
    private String currentDate;

    public CalendarAdapter(List<String> daysOfWeek, List<String> weekDates, String currentDate) {
        this.daysOfWeek = daysOfWeek;
        this.weekDates = weekDates;
        this.currentDate = currentDate;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar, parent, false);
        return new CalendarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        String dayOfWeek = daysOfWeek.get(position);
        String date = weekDates.get(position);

        holder.dayOfWeekTextView.setText(dayOfWeek);
        holder.dateTextView.setText(date);

        if (date.equals(currentDate)) {
            holder.dateTextView.setBackgroundResource(R.drawable.shape_calendarball);
            holder.dateTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        } else {
            holder.dateTextView.setBackgroundResource(0);
            holder.dateTextView.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return daysOfWeek.size();
    }

    static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeekTextView;
        TextView dateTextView;

        CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeekTextView = itemView.findViewById(R.id.dayOfWeekTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}


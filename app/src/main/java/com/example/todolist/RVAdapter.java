package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.databinding.RvItemBinding;

public class RVAdapter extends ListAdapter<Task, RVAdapter.ViewHolder> {

    public RVAdapter() {
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Task> CALLBACK = new DiffUtil.ItemCallback<Task>() {
        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = getItem(position);
        holder.binding.taskTitle.setText(task.getTitle());
        holder.binding.taskDes.setText(task.getDescription());
        holder.binding.taskDate.setText("Date: " + task.getDueDate());
        holder.binding.taskTime.setText("Time: " + task.getDueTime());

        holder.binding.dateBtn.setOnClickListener(v -> {
            onItemDeleteListener.onItemDeleted(task);
        });
    }

    public Task getTask(int position) {
        return getItem(position);
    }

    public interface OnItemDeleteListener {
        void onItemDeleted(Task task);
    }

    private OnItemDeleteListener onItemDeleteListener;

    public void setOnItemDeleteListener(OnItemDeleteListener listener) {
        this.onItemDeleteListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RvItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RvItemBinding.bind(itemView);
        }
    }
}

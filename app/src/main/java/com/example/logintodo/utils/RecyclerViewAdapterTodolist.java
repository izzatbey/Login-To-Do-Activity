package com.example.logintodo.utils;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.logintodo.R;
import com.example.logintodo.data.model.Task;
import com.example.logintodo.data.source.local.TableHandler;

public class RecyclerViewAdapterTodolist extends RecyclerView.Adapter<com.example.logintodo.utils.RecyclerViewAdapterTodolist.MyViewHolder> {
    private static ArrayList<Task> mDataset;
    private static MyClickListener myClickListener;
    private Activity activity;
    private TableHandler tableHandler;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvTitle;
        TextView tvDescription;
        CheckBox textBoxComplete;
        Button btShare;

        public MyViewHolder(final View itemView, final Activity activity) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTodolistTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvTodolistDescription);
            textBoxComplete = (CheckBox)  itemView.findViewById(R.id.todoCheckBox);
            btShare = (Button) itemView.findViewById(R.id.btn_share);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterTodolist(ArrayList<Task> myDataset, final TableHandler tableHandler, Activity activity) {
        mDataset = myDataset;
        this.tableHandler = tableHandler;
        this.activity = activity;
    }

    @Override
    public com.example.logintodo.utils.RecyclerViewAdapterTodolist.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, activity);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(mDataset.get(position).getTitle());
        holder.tvDescription.setText(mDataset.get(position).getDescription());

        Integer isDone = mDataset.get(position).getIsComplete();
        if(isDone == 1)
            holder.textBoxComplete.setChecked(true);
        else if(isDone == 0)
            holder.textBoxComplete.setChecked(false);

        holder.textBoxComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                final Task task = mDataset.get(position);
                if(isChecked) {
                    task.setIsComplete(1);
                }
                else {
                    task.setIsComplete(0);
                }

                ThreadUI.runOnUI(new Runnable() {
                    @Override
                    public void run() {
                        mDataset.set(position, task);
                        notifyItemChanged(position);
                        tableHandler.update(task);
                    }
                });
            }
        });

        holder.btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mDataset.get(position).getTitle();
                String description = mDataset.get(position).getDescription();
                String completeTask = "";


                int isDone = mDataset.get(position).getIsComplete();
                if(isDone == 0)
                    completeTask = "Incomplete";
                else if(isDone == 1)
                    completeTask = "Completed";

                String sentText = "Title     : " + title       + "\n" +
                                  "Description  : " + description + "\n";

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, sentText);
                intent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(intent, null);
                activity.startActivity(shareIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
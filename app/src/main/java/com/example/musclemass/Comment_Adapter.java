package com.example.musclemass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Comment_Adapter extends RecyclerView.Adapter<Comment_Adapter.ViewHolder> {

    private Context mContext;

    private ArrayList<CommentItem> mList;

    public Comment_Adapter(Context mContext, ArrayList<CommentItem> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public Comment_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.community_comment,parent,false);

        Comment_Adapter.ViewHolder vh = new Comment_Adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Comment_Adapter.ViewHolder holder, int position) {

        CommentItem item = mList.get(position);

        holder.date.setText(item.getDate());
        holder.context.setText(item.getContext());
        holder.nickname.setText(item.getNickname());

        Glide.with(holder.itemView.getContext())
                .load(item.getUri())
                .into(holder.comment_profile);

        holder.itemView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nickname;
        TextView context;
        TextView date;
        ImageView comment_profile;


        ViewHolder(View itemView){
            super(itemView);
            this.nickname = itemView.findViewById(R.id.comment_nickname);
            this.context = itemView.findViewById(R.id.comment);
            this.date = itemView.findViewById(R.id.comment_date);
            this.comment_profile = itemView.findViewById(R.id.comment_profile);
        }
    }
}

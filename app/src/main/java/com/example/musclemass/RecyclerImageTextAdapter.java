package com.example.musclemass;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
    private ArrayList<Communityitem> mData;
    Context context;
    private String head;
    private String body;
    private String nickname;


    public RecyclerImageTextAdapter(Context context , ArrayList<Communityitem> list) {
        this.context = context;

        this.mData = list;

    }





    // 리스트뷰가 생성 될 때의 생명주기 oncreate//
    @Override
    public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.community_recyclerview, parent, false);

        RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder (view);

        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

        Communityitem item = mData.get(position);

        holder.community_head.setText(item.getTitleStr());
        holder.community_text.setText(item.getDescStr());
        holder.community_nickname.setText(item.getNicknameStr());

        holder.itemView.setTag(position);


    }


    @Override
    public int getItemCount() {
        return (null != mData ? mData.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView community_head;
        TextView community_text;
        TextView community_nickname;

        ViewHolder(View itemView) {
            super(itemView);
            this.community_head = itemView.findViewById(R.id.recycler_header);
            this.community_text = itemView.findViewById(R.id.recycler_body);
            this.community_nickname = itemView.findViewById(R.id.recycler_nickname);


            // 아이템 클릭 이벤트 처리.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        head = community_head.getText().toString();
                        body = community_text.getText().toString();
                        nickname = community_nickname.getText().toString();

                        Intent intent = new Intent(context, Community_confirm.class);
                        intent.putExtra("communityhead1",head);
                        intent.putExtra("communityacc1",body);
                        intent.putExtra("usernickname1",nickname);
                        context.startActivity(intent);
                    }

                }
            });
        }
    }
}
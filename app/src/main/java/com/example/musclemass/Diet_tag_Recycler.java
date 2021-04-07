package com.example.musclemass;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static android.media.CamcorderProfile.get;

public class Diet_tag_Recycler extends RecyclerView.Adapter<Diet_tag_Recycler.TagViewHolder> {
    private Context mContext;


    private ArrayList<Diet_tag_item> mList;

    ArrayList<Userinfo> userinfoArrayList;

    private ArrayList<Diet_tag_item> load_diets;

    String connect_diet_id;

    public Diet_tag_Recycler(Context mContext, ArrayList<Diet_tag_item> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Diet_tag_Recycler.TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_tag_item, parent, false);

        TagViewHolder vh = new TagViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull Diet_tag_Recycler.TagViewHolder holder, int position) {

        holder.foodname.setText(mList.get(position).getFoodname());

        holder.x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mList.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


    public class TagViewHolder extends RecyclerView.ViewHolder {
        protected TextView foodname;

        protected ImageButton x;

        public TagViewHolder(@NonNull View itemView) {
            super(itemView);


            // 어레이리스트를 리사이클러뷰에게 보여주기위한 코드 //
            this.foodname = (TextView) itemView.findViewById(R.id.diet_tag);

            this.x = (ImageButton) itemView.findViewById(R.id.diet_tag_imgbtn);


        }
    }
}
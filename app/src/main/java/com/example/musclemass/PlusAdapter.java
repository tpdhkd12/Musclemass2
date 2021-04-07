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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PlusAdapter extends RecyclerView.Adapter<PlusAdapter.DietViewHolder> {

    private Context mContext;
    private ArrayList<Diet_tag_item> tag_itemArrayList;

    private ArrayList<Diet_item> mList;

    ArrayList<Userinfo> userinfoArrayList;

    private ArrayList<Diet_item> load_diets;

    String connect_diet_id;

    private RecyclerView diet_tag;

    private Diet_tag_Recycler adapter;



    public PlusAdapter(Context context, ArrayList<Diet_item> list) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public PlusAdapter.DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_item, parent, false);

        DietViewHolder vh = new DietViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlusAdapter.DietViewHolder holder, int position) {


        holder.foodname.setText(mList.get(position).getFoodname());

        holder.carbohydrate.setText(mList.get(position).getCarbohydrate());

        holder.protein.setText(mList.get(position).getProtein());

        holder.fat.setText(mList.get(position).getFat());


    }

    //    implements View.OnCreateContextMenuListener
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public class DietViewHolder extends RecyclerView.ViewHolder {

        protected TextView foodname;


        protected TextView carbohydrate;

        protected TextView protein;

        protected TextView fat;


        // 어레이리스트를 리사이클러뷰에게 보여주기위한 코드 //

        public DietViewHolder(@NonNull View itemView) {
            super(itemView);

            this.foodname = (TextView) itemView.findViewById(R.id.foodname);

            this.carbohydrate = (TextView) itemView.findViewById(R.id.carbohydrate);

            this.protein = (TextView) itemView.findViewById(R.id.protein);

            this.fat = (TextView) itemView.findViewById(R.id.fat);


//            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Diet_tag_item diet_tag_item = new Diet_tag_item(mList.get(getAdapterPosition()).getFoodname(),mList.get(getAdapterPosition()).getCarbohydrate(),mList.get(getAdapterPosition()).getProtein(),mList.get(getAdapterPosition()).getFat(),mList.get(getAdapterPosition()).getDay(),mList.get(getAdapterPosition()).getId(),mList.get(getAdapterPosition()).getTimeStamp());
                    Dietplus.tag_itemArrayList.add(diet_tag_item);
                    Dietplus.mmAdapter.notifyDataSetChanged();

                }
            });


        }
    }
}
package com.example.musclemass;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {

    private Context mContext;


    private ArrayList<Diet_item> mList;

    public DietAdapter(Context context, ArrayList<Diet_item> list) {
        this.mList = list;
        this.mContext = context;
    }


    @NonNull
    @Override
    public DietAdapter.DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_item, parent, false);

        DietViewHolder vh = new DietViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DietAdapter.DietViewHolder holder, int position) {


        holder.foodname.setText(mList.get(position).getFoodname());

        holder.carbohydrate.setText(mList.get(position).getCarbohydrate());

        holder.protein.setText(mList.get(position).getProtein());

        holder.fat.setText(mList.get(position).getFat());


    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public class DietViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

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

            itemView.setOnCreateContextMenuListener(this);


        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);


        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case 1001:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextfoodname = (EditText) view.findViewById(R.id.edittext_dialog_foodname);
                        final EditText editTextcarby = (EditText) view.findViewById(R.id.edittext_dialog_carby);
                        final EditText editTextprot = (EditText) view.findViewById(R.id.edittext_dialog_prot);
                        final EditText editTextfat = (EditText) view.findViewById(R.id.edittext_dialog_fat);


                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextfoodname.setText(mList.get(getAdapterPosition()).getFoodname());
                        editTextcarby.setText(mList.get(getAdapterPosition()).getCarbohydrate());
                        editTextprot.setText(mList.get(getAdapterPosition()).getProtein());
                        editTextfat.setText(mList.get(getAdapterPosition()).getFat());


                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String Strfoodname = editTextfoodname.getText().toString();
                                String Strcarby = editTextcarby.getText().toString();
                                String Strprot = editTextprot.getText().toString();
                                String Strfat = editTextfat.getText().toString();

                                Diet_item dict = new Diet_item(Strfoodname, Strcarby, Strprot, Strfat);

                                mList.set(getAdapterPosition(), dict);

                                notifyItemChanged(getAdapterPosition());
                                dialog.dismiss();


                            }
                        });
                        dialog.show();

                        break;

                    case 1002:

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        break;
                }
                return true;
            }
        };
    }
}
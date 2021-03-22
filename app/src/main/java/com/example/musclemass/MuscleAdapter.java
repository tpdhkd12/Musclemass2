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

public class MuscleAdapter extends RecyclerView.Adapter<MuscleAdapter.MuscleViewHolder>{

    private Context mContext;


    private ArrayList<Muscle_item> mList;

    public MuscleAdapter(Context context, ArrayList<Muscle_item> list) {
        this.mList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MuscleAdapter.MuscleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.muscle_item, parent, false);

        MuscleViewHolder vh = new MuscleViewHolder(view);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MuscleAdapter.MuscleViewHolder holder, int position) {

        holder.kg.setText(mList.get(position).getKg());

        holder.count.setText(mList.get(position).getCount());

        holder.set.setText(mList.get(position).getSet());

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public class MuscleViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        protected TextView kg;


        protected TextView count;

        protected TextView set;



        public MuscleViewHolder(@NonNull View itemView) {
            super(itemView);

            this.kg = (TextView) itemView.findViewById(R.id.muscle_kg);

            this.count = (TextView) itemView.findViewById(R.id.muscle_count);

            this.set = (TextView) itemView.findViewById(R.id.muscle_set);

            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem Edit = menu.add(Menu.NONE, 1003, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1004, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu1);
            Delete.setOnMenuItemClickListener(onEditMenu1);

        }
        private final MenuItem.OnMenuItemClickListener onEditMenu1 = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case 1003:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.

                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box_muscle, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit1);
                        final EditText editTextkg = (EditText) view.findViewById(R.id.edittext_dialog_kg);
                        final EditText editTextcount = (EditText) view.findViewById(R.id.edittext_dialog_count);
                        final EditText editTextset = (EditText) view.findViewById(R.id.edittext_dialog_set);


                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextkg.setText(mList.get(getAdapterPosition()).getKg());
                        editTextcount.setText(mList.get(getAdapterPosition()).getCount());
                        editTextset.setText(mList.get(getAdapterPosition()).getSet());


                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String Strkg = editTextkg.getText().toString();
                                String Strcount = editTextcount.getText().toString();
                                String Strset = editTextset.getText().toString();

                                Muscle_item dict = new Muscle_item(Strkg, Strcount, Strset);

                                mList.set(getAdapterPosition(), dict);

                                notifyItemChanged(getAdapterPosition());
                                dialog.dismiss();


                            }
                        });
                        dialog.show();

                        break;

                    case 1004:

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

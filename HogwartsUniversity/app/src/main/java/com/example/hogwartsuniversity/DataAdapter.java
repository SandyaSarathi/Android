package com.example.hogwartsuniversity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<DataClass> data;
    String Type;
    boolean readOnly=false;
    public DataAdapter(ArrayList<DataClass> dataClass, String type) {
        this.data=dataClass;
        this.Type=type;
    }
    public DataAdapter(ArrayList<DataClass> dataClass, boolean ReadOnly) {
        this.data=dataClass;
        this.readOnly=ReadOnly;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.recycler_view_items, parent, false);
        if(readOnly==false)
        {
            ViewHolder viewHolder = new ViewHolder(listItem);
            return viewHolder;
        }
        else
        {
            ViewHolder viewHolderReadOnly= new ViewHolder(listItem,readOnly);

            return viewHolderReadOnly;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final DataClass myListData = data.get(position);
        holder.lbl_user_id.setText(String.valueOf(myListData.getId()));
        holder.lbl_name.setText(myListData.getFname());
        holder.lbl_department.setText(myListData.getDepartment());
        holder.lbl_mail.setText(myListData.getMail());
      /*  holder.tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"working"+data.size(),Toast.LENGTH_LONG).show();
            }

        });*/
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView lbl_user_id,lbl_name,lbl_department,lbl_mail,lbl_view;
        public TableLayout tableLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.lbl_user_id = (TextView) itemView.findViewById(R.id.lbl_user_id);
            this.lbl_name = (TextView) itemView.findViewById(R.id.lbl_name);
            this.lbl_department = (TextView) itemView.findViewById(R.id.lbl_department);
            this.lbl_mail = (TextView) itemView.findViewById(R.id.lbl_mail);
            this.lbl_view=(TextView) itemView.findViewById(R.id.lbl_view);
            lbl_view.setOnClickListener(this);
            tableLayout = (TableLayout) itemView.findViewById(R.id.table_layout);
        }

        @SuppressLint("WrongConstant")
        public ViewHolder(View listItem, boolean readOnly) {
            super(listItem);
            this.lbl_user_id = (TextView) itemView.findViewById(R.id.lbl_user_id);
            this.lbl_name = (TextView) itemView.findViewById(R.id.lbl_name);
            this.lbl_department = (TextView) itemView.findViewById(R.id.lbl_department);
            this.lbl_mail = (TextView) itemView.findViewById(R.id.lbl_mail);
            this.lbl_view=(TextView) itemView.findViewById(R.id.lbl_view);
            this.lbl_view.setVisibility(View.GONE);
            tableLayout = (TableLayout) itemView.findViewById(R.id.table_layout);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.lbl_view:
                    if(Type.equals("Student"))
                    {
                        Intent i = new Intent(v.getContext(),StudentProfile.class);
                        i.putExtra("Mail",lbl_mail.getText().toString());
                        v.getContext().startActivity(i);
                    }
                    else
                    {
                        Intent i = new Intent(v.getContext(),FacultyProfile.class);
                        i.putExtra("Mail",lbl_mail.getText().toString());
                        v.getContext().startActivity(i);
                    }
                    break;
            }

        }
    }

}

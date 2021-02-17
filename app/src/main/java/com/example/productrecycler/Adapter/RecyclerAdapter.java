package com.example.productrecycler.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.productrecycler.HomeActivity;
import com.example.productrecycler.Model.ProductModel;
import com.example.productrecycler.ProductActivity;
import com.example.productrecycler.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

// create array list and its constructor
ArrayList<ProductModel> arrayList;
Context context;

    public RecyclerAdapter(ArrayList<ProductModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_format,parent,false);
       return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final ProductModel productModel = arrayList.get(position);
        holder.title.setText(productModel.getProduct_title());
        Glide.with(holder.imageView.getContext()).load(productModel.getImageurl()).into(holder.imageView);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Bundle bundle = new Bundle();
////                bundle.putString("description",productModel.getProduct_description());
////                bundle.putString("inmage",productModel.getImageurl());
////                Intent intent = new Intent(context,ProductActivity.class);
////                intent.putExtra("title",productModel.getProduct_title());
////                intent.putExtra("key",bundle);
////                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                context.startActivity(intent);
////
////            }
////        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.imageView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1800)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText edit_title = myview.findViewById(R.id.recycle_title_edit);
                final EditText description = myview.findViewById(R.id.recycle_description_edit);
                final EditText color = myview.findViewById(R.id.recycle_color_edit);
                EditText size = myview.findViewById(R.id.recycle_product_size);
                final EditText quantity = myview.findViewById(R.id.recycle_quantity_edit);
                Button submit = myview.findViewById(R.id.update_product);


                edit_title.setText(productModel.getProduct_title());
                description.setText(productModel.getProduct_description());
                color.setText(productModel.getProduct_color());
                quantity.setText(productModel.getQuantity());

                dialogPlus.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //create map to get all the data
                            Map<String ,Object> map = new HashMap<>();
                            map.put("product_title",edit_title.getText().toString());
                            map.put("product_description",description.getText().toString());
                            map.put("product_color",color.getText().toString());
                            map.put("quantity",quantity.getText().toString());


                            FirebaseDatabase.getInstance().getReference().child("addproduct")
                                    .child(productModel.getSerial_number()).updateChildren(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            dialogPlus.dismiss();
                                            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                            dialogPlus.dismiss();
                                    Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
            }
        });

                        holder.delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(holder.delete.getContext());
                                builder.setTitle("Remove Product");
                                builder.setMessage("Are you sure you want to delete this product?");

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("addproduct")
                                                .child(productModel.getSerial_number()).removeValue();
                                    }
                                });

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.show();
                            }
                        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,edit,delete;
        TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_image);
            title = itemView.findViewById(R.id.recycler_text);
            edit = itemView.findViewById(R.id.editicon);
            delete = itemView.findViewById(R.id.removeicon);
        }
    }
}

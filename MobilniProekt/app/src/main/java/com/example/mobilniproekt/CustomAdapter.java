package com.example.mobilniproekt;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    private WorkoutCLickListener mWorkoutCLickListener;

    public CustomAdapter(Context context, Cursor cursor,WorkoutCLickListener workoutCLickListener){
        mContext=context;
        mCursor=cursor;
        mWorkoutCLickListener=workoutCLickListener;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nameText;
        public TextView diffText;
        public ImageView mImage;
        WorkoutCLickListener wcl;

        public CustomViewHolder(@NonNull View itemView,WorkoutCLickListener x) {
            super(itemView);
            nameText=itemView.findViewById(R.id.textView1);
            diffText=itemView.findViewById(R.id.textView2);
            mImage=itemView.findViewById(R.id.imageView);
            wcl=x;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            wcl.onWorkoutClick(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.example_item,parent,false);
        return new CustomViewHolder(view,mWorkoutCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String name=mCursor.getString(mCursor.getColumnIndex("name"));
        String diff=mCursor.getString(mCursor.getColumnIndex("diff"));
        byte[] img=mCursor.getBlob(mCursor.getColumnIndex("img"));

        holder.nameText.setText(name);
        holder.diffText.setText(diff);
        Bitmap bmp= BitmapFactory.decodeByteArray(img,0,img.length);
        holder.mImage.setImageBitmap(bmp);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor=newCursor;
        if(newCursor!=null){
            notifyDataSetChanged();
        }
    }

    public interface WorkoutCLickListener{
        void onWorkoutClick(int position);
    }

}

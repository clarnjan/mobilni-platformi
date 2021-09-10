package com.example.mobilniproekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ImageView mImage;
    TextView mTitle;
    TextView mDifficulty;
    ArrayList<String> listaVezbi;
    private RecyclerView mRecyclerView;
    private DetailsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mImage=findViewById(R.id.IWDetailImage);
        mTitle=findViewById(R.id.TWTitle);
        mDifficulty=findViewById(R.id.TWDifficulty);

        Bundle bundle =getIntent().getExtras();
        if(getIntent().hasExtra("img")) {
            byte[] src = bundle.getByteArray("img");
            Bitmap bmp= BitmapFactory.decodeByteArray(src,0,src.length);
            mImage.setImageBitmap(bmp);
        }
        if(getIntent().hasExtra("image")){
            int src=bundle.getInt("image");
            mImage.setImageResource(src);
        }
        if(getIntent().hasExtra("title")) {
            String title = bundle.getString("title");
            mTitle.setText(title);
        }
        if(getIntent().hasExtra("difficulty")) {
            String difficulty = bundle.getString("difficulty");
            mDifficulty.setText(difficulty);
        }
        if(getIntent().hasExtra("list")) {
            listaVezbi = bundle.getStringArrayList("list");
            for(int i=0;i<listaVezbi.size();i++){
                if(listaVezbi.get(i).equals("NO EXERCISE")){
                    listaVezbi.remove(i);
                    i--;
                }
            }
            mRecyclerView = findViewById(R.id.RWDetails);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new DetailsAdapter(listaVezbi);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }

    }
}

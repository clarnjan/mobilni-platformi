package com.example.recipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipes.MainActivity;
import com.example.recipes.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ImageView mImageView;
    TextView mTextView;
    CheckBox mTextView1,mTextView2,mTextView3,mTextView4,mTextView5;
    TextView mTextView6,mTextView7,mTextView8;
    ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mImageView=findViewById(R.id.IV);
        mTextView=findViewById(R.id.TV);
        mTextView1=findViewById(R.id.TV2);
        mTextView2=findViewById(R.id.TV3);
        mTextView3=findViewById(R.id.TV4);
        mTextView4=findViewById(R.id.TV5);
        mTextView5=findViewById(R.id.TV6);
        mTextView6=findViewById(R.id.TV7);
        mTextView7=findViewById(R.id.TVPortions);
        mTextView8=findViewById(R.id.TVCalories);

        mTextView1.setVisibility(View.INVISIBLE);
        mTextView2.setVisibility(View.INVISIBLE);
        mTextView3.setVisibility(View.INVISIBLE);
        mTextView4.setVisibility(View.INVISIBLE);
        mTextView5.setVisibility(View.INVISIBLE);


        Bundle bundle=getIntent().getExtras();
        if(getIntent().hasExtra("image")) {
            int src = bundle.getInt("image");
            mImageView.setImageResource(src);
        }
        if(getIntent().hasExtra("img")) {
            byte[] src=bundle.getByteArray("img");
            Bitmap bmp= BitmapFactory.decodeByteArray(src,0,src.length);
            mImageView.setImageBitmap(bmp);
        }
        if(getIntent().hasExtra("title")) {
            String title = bundle.getString("title");
            mTextView.setText(title);
        }
        if(getIntent().hasExtra("list")) {
            list = bundle.getStringArrayList("list");
            if (list.get(0) != null)
                mTextView1.setVisibility(View.VISIBLE);
                mTextView1.setText(list.get(0));
            if (list.get(1) != null)
                mTextView2.setVisibility(View.VISIBLE);
                mTextView2.setText(list.get(1));
            if (list.get(2) != null)
                mTextView3.setVisibility(View.VISIBLE);
                mTextView3.setText(list.get(2));
            if (list.get(3) != null)
                mTextView4.setVisibility(View.VISIBLE);
                mTextView4.setText(list.get(3));
            if (list.get(4) != null)
                mTextView5.setVisibility(View.VISIBLE);
                mTextView5.setText(list.get(4));

            if (list.get(5) != null)
                mTextView6.setText(list.get(5));
        }
        if(getIntent().hasExtra("portions")) {
            String portions = bundle.getString("portions");
            mTextView7.setText("portions: "+portions);
        }
        if(getIntent().hasExtra("calories")) {
            String calories = bundle.getString("calories");
            mTextView8.setText("calories: "+calories);
        }



    }

}

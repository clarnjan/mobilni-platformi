package com.example.recipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recipes.CustomAdapter;
import com.example.recipes.DataBaseHelper;
import com.example.recipes.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity implements CustomAdapter.customClickListener {

    EditText mName;
    EditText mFirst;
    EditText mSecond;
    EditText mThird;
    EditText mFourth;
    EditText mFifth;
    ImageView mImageView;
    Button mAddPhoto;
    Button mCreate;
    Button mCamera;
    EditText mCalories,mPortions;

    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase mDatabase;
    CustomAdapter mAdapter;
    static int GALLERY_CODE=1;
    static int CAMERA_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mDataBaseHelper=new DataBaseHelper(getApplicationContext());
        mDatabase=mDataBaseHelper.getWritableDatabase();
        mAdapter=new CustomAdapter(this,mDatabase.query("tabela",null,null,null,null,null,null),this);

        init();


        mAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture();

            }
        });

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_CODE);

            }
        });
        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mName.getText().length() != 0 && mFirst.getText().length() != 0) {
                    String name = mName.getText().toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", name);
                    contentValues.put("i1", mFirst.getText().toString());
                    contentValues.put("i2", mSecond.getText().toString());
                    contentValues.put("i3", mThird.getText().toString());
                    contentValues.put("i4", mFourth.getText().toString());
                    contentValues.put("i5", mFifth.getText().toString());
                    contentValues.put("img",imageViewToByte(mImageView));
                    contentValues.put("calories",mCalories.getText().toString());
                    contentValues.put("portions",mPortions.getText().toString());
                    long result = mDatabase.insert("tabela", null, contentValues);
                    mAdapter.swapCursor(mDatabase.query("tabela", null, null, null, null, null, null));
                    if (result == -1)
                        Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        mAdapter.notifyDataSetChanged();
                        Intent intent=new Intent();
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }
                else if(mName.getText().length() == 0 ){
                    mName.setError("Please Enter a name");
                    mName.requestFocus();
                }
                else if(mFirst.getText().length() == 0){
                    mFirst.setError("Recipe must have at least one ingredient!");
                    mFirst.requestFocus();
                }
            }
        });


    }

    public byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }

    private void changePicture() {
        ActivityCompat.requestPermissions(
                AddActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                GALLERY_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==GALLERY_CODE){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,GALLERY_CODE);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_CODE && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                mImageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,mImageView.getWidth(),
                        mImageView.getHeight(),false));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(requestCode==CAMERA_CODE && resultCode==RESULT_OK && data!=null){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(bitmap);
        }

    }

    private void init() {
        mName=findViewById(R.id.namee);
        mFirst=findViewById(R.id.firstt);
        mSecond=findViewById(R.id.secondd);
        mThird=findViewById(R.id.thirdd);
        mFourth=findViewById(R.id.fourthh);
        mFifth=findViewById(R.id.fifthh);
        mImageView=findViewById(R.id.slika);
        mAddPhoto=findViewById(R.id.changePhoto);
        mCreate=findViewById(R.id.createe);
        mCamera=findViewById(R.id.cameraPhoto);
        mCalories=findViewById(R.id.enterCalories);
        mPortions=findViewById(R.id.enterPortions);
    }

    @Override
    public void onCustomClick(int position) {

    }
}

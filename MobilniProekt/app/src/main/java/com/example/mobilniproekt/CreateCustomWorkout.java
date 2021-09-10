package com.example.mobilniproekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;

public class CreateCustomWorkout extends AppCompatActivity implements CustomAdapter.WorkoutCLickListener  {
    EditText mName;
    Button mDifficulty;
    ImageView mImage;
    Button mChangePicture;
    Button mCamera;
    EditText mExerciseName;
    EditText mSets;
    EditText mReps;
    Button mAddExercise;
    Button mCreateWorkout;
    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase mDatabase;
    CustomAdapter mAdapter;
    int arraylength=0;
    ArrayList<String> exercises;
    private static final int GALLERY_REQUEST_CODE=1;
    private static final int CAMERA_CODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_custom_workout);

        init();

        mDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDificulty();
            }
        });
        mAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

        mCreateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWorkout();
            }
        });
        mChangePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePicture();
            }
        });
        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useCamera();
            }
        });

    }

    private void useCamera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_CODE);
    }

    private void changePicture() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST_CODE);
    }

    private void createWorkout() {
        if(mName.getText().length()!=0 && mDifficulty.getText().length()!=0 ) {
            String name=mName.getText().toString();
            String diff=mDifficulty.getText().toString();
            ContentValues contentValues=new ContentValues();
            contentValues.put("name", name);
            contentValues.put("diff", diff);
            for (int i=0;i<arraylength;i++)
                contentValues.put("e"+Integer.toString(i+1), exercises.get(i));
            for (int i=arraylength;i<8;i++)
                contentValues.put("e"+Integer.toString(i+1), "NO EXERCISE");
            contentValues.put("img",imageViewToByte(mImage));
            long result = mDatabase.insert("tabela", null, contentValues);
            mAdapter.swapCursor(mDatabase.query("tabela", null, null, null, null, null, null));
            if (result == -1)
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Workout name can't be empty",Toast.LENGTH_SHORT).show();
        }
    }
    private byte[] imageViewToByte(ImageView image){
        Bitmap bitmap=((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte [] bytes=stream.toByteArray();
        return  bytes;
    }

    private void addExercise() {
        if(mExerciseName.getText().length()!=0 && mSets.getText().length()!=0 && mReps.getText().length()!=0){
            String name=mExerciseName.getText().toString();
            String sets=mSets.getText().toString();
            String reps=mReps.getText().toString();
            arraylength++;
            exercises.add(sets+"x"+reps+" "+name);
            Toast.makeText(CreateCustomWorkout.this, "Exercise added successfully", Toast.LENGTH_SHORT).show();
            mExerciseName.setText("");
            mSets.setText("");
            mReps.setText("");
        }
    }

    private void changeDificulty() {
        if(mDifficulty.getText().toString()=="Beginner")
            mDifficulty.setText("Advanced");
        else
            mDifficulty.setText("Beginner");
    }

    private void init(){
        mDataBaseHelper=new DataBaseHelper(getApplicationContext());
        mDatabase=mDataBaseHelper.getWritableDatabase();
        mAdapter=new CustomAdapter(getApplicationContext(),mDatabase.query("tabela",null,null,null,null,null,null), this);
        exercises=new ArrayList<>();

        mCreateWorkout=findViewById(R.id.BTNCreateWorkout);
        mName=findViewById(R.id.ETName);
        mDifficulty=findViewById(R.id.BTNDiff);
        mImage=findViewById(R.id.IWCreate);
        mChangePicture=findViewById(R.id.BTNChangePicture);
        mCamera=findViewById(R.id.BTNCamera);
        mExerciseName=findViewById(R.id.ETExerciseName);
        mSets=findViewById(R.id.ETSets);
        mReps=findViewById(R.id.ETReps);
        mAddExercise=findViewById(R.id.BTNAddExercise);
        mDifficulty.setText("Beginner");
        mImage.setImageResource(R.drawable.lunges);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.IHome){
            Home();
        }
        else if (item.getItemId()==R.id.ILogOut)
            LogOut();
        else if (item.getItemId()==R.id.IMaps)
            NeerbyGym();
        return super.onOptionsItemSelected(item);
    }

    private void Home(){
        startActivity(new Intent(getApplicationContext(),LoggedInActivity.class));
    }

    private void NeerbyGym(){
        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
    }

    private void LogOut(){
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_dialoglogout)
                .setTitle("Logging Out")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        LoginManager.getInstance().logOut();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onWorkoutClick(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== GALLERY_REQUEST_CODE && resultCode==RESULT_OK && data !=null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= rotateBitmap(BitmapFactory.decodeStream(inputStream),90);

                mImage.setImageBitmap(Bitmap.createScaledBitmap(bitmap, mImage.getWidth(),
                        mImage.getHeight(), false));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (requestCode==CAMERA_CODE && resultCode==RESULT_OK && data !=null){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            mImage.setImageBitmap(bitmap);
        }
    }
    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

}

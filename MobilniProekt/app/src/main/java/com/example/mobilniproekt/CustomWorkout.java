package com.example.mobilniproekt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class CustomWorkout extends AppCompatActivity implements CustomAdapter.WorkoutCLickListener{
    Button mGoCreate;
    DataBaseHelper mDataBaseHelper;
    SQLiteDatabase mDatabase;
    RecyclerView recyclerView;
    CustomAdapter mAdapter;
    ArrayList<String> exercises;
    int CREATED_WORKOUT=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_workout);
        mDataBaseHelper=new DataBaseHelper(getApplicationContext());
        mDatabase=mDataBaseHelper.getWritableDatabase();

        mGoCreate=findViewById(R.id.BTNGoToCreate);
        mGoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(),CreateCustomWorkout.class),CREATED_WORKOUT);
            }
        });
        recyclerView=findViewById(R.id.RWCustom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter=new CustomAdapter(this,mDatabase.query("tabela",null,null,null,null,null,null),this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onWorkoutClick(int position) {
        //Intent intent=new Intent(getApplicationContext(),BuildMuscle.class);
        //startActivity(intent);
        Cursor cursor=mDatabase.rawQuery("select * from tabela",null);
        if(cursor.getCount()==0)
            Toast.makeText(getApplicationContext(),"NO DATA",Toast.LENGTH_SHORT).show();
        else{
            cursor.moveToPosition(position);
            String name=cursor.getString(1);
            String diff=cursor.getString(2);
            exercises=new ArrayList<>();
            exercises.add(cursor.getString(3));
            exercises.add(cursor.getString(4));
            exercises.add(cursor.getString(5));
            exercises.add(cursor.getString(6));
            exercises.add(cursor.getString(7));
            exercises.add(cursor.getString(8));
            exercises.add(cursor.getString(9));
            exercises.add(cursor.getString(10));
            byte[] image=cursor.getBlob(11);
            Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
            intent.putExtra("title",name);
            intent.putExtra("img",image);
            intent.putExtra("difficulty",diff);
            intent.putExtra("list",exercises);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CREATED_WORKOUT && resultCode==RESULT_OK){
            Toast.makeText(getApplicationContext(),"New workout added successfully",Toast.LENGTH_SHORT).show();
            mAdapter.swapCursor(mDatabase.query("tabela", null, null, null, null, null, null));
        }
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

}

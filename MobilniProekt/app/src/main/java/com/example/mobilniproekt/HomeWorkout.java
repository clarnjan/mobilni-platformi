package com.example.mobilniproekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeWorkout extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private ArrayList<ExampleItem> exampleList;
    ArrayList<String> listaVezbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_workout);
        createList();
        buildRecyclerView();
    }

    private void createList() {
        exampleList=new ArrayList<>();
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x10 Pushup");
        listaVezbi.add("3x8  Diamond Push-up");
        listaVezbi.add("3x25 Squats");
        listaVezbi.add("2x10 Superman");
        listaVezbi.add("3x20 Lunges");
        listaVezbi.add("3x20 Situps");
        listaVezbi.add("3x10 Dumbell curl");
        exampleList.add(new ExampleItem(R.drawable.sideplank,"Full body workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x10 Pushup");
        listaVezbi.add("3x15 Bench Dip");
        listaVezbi.add("3x10 Close-Grip Pushup");
        listaVezbi.add("3x8  Decline push-ups");
        listaVezbi.add("2x10 Overhead Triceps Extension");
        exampleList.add(new ExampleItem(R.drawable.dips,"Chest & triceps workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x20 Squats");
        listaVezbi.add("3x10 Lunges");
        listaVezbi.add("3x10 Calf Raise");
        listaVezbi.add("3x10 Reverse Lunges");
        listaVezbi.add("3x10 Plank leg lifts");
        exampleList.add(new ExampleItem(R.drawable.lunges,"Leg workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x7  Pull-ups");
        listaVezbi.add("3x10 Reverse fly");
        listaVezbi.add("3x10 Dumbbell Curl");
        listaVezbi.add("3x10 Hammer Curl");
        listaVezbi.add("3x10 Superman");
        exampleList.add(new ExampleItem(R.drawable.superman,"Back & Biceps workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("10min Walking");
        listaVezbi.add("30min Running");
        listaVezbi.add("3x20 Superman");
        listaVezbi.add("3x20 Lunge");
        listaVezbi.add("3x20 Push-ups");
        listaVezbi.add("3x20 Sit-ups");
        listaVezbi.add("3x20 Bench Dip");
        listaVezbi.add("4x10  Hammer Curl");
        exampleList.add(new ExampleItem(R.drawable.ropejump,"Full Body with Cardio","Advanced",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("2x10  Leg Raise");
        listaVezbi.add("3x10 Flutter Kick");
        listaVezbi.add("3x60s Plank");
        listaVezbi.add("4x10 Knee Raise");
        listaVezbi.add("3x60s Mountain climber");
        listaVezbi.add("3x10  Leg Raise");
        exampleList.add(new ExampleItem(R.drawable.core,"Core workout","Advanced",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x10 Push-Ups");
        listaVezbi.add("3x10 Decline Push-ups");
        listaVezbi.add("3x8  Dumbbell Flye");
        listaVezbi.add("3x60s Jump Rope");
        listaVezbi.add("3x10 Wide-Grip Push-ups");
        listaVezbi.add("3x8  Close-Grip Push-ups");
        listaVezbi.add("30min Running");
        exampleList.add(new ExampleItem(R.drawable.advancedchest,"Chest with Cardio workout","Advanced",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x6  Pull-Ups");
        listaVezbi.add("3x10 Push-Ups");
        listaVezbi.add("3x8  Decline Push-ups");
        listaVezbi.add("3x10 Reverse fly");
        listaVezbi.add("3x20 Sit-ups");
        listaVezbi.add("4x25 Squats");
        listaVezbi.add("4x8  Dumbbell Curl");
        listaVezbi.add("4x8  Hammer Curl");
        listaVezbi.add("3x20 Lunges");
        listaVezbi.add("3x20 Bench Dip");
        exampleList.add(new ExampleItem(R.drawable.fullhome,"Full body workout","Advanced",listaVezbi));
    }

    private void buildRecyclerView() {
        mRecyclerView=findViewById(R.id.RWHome);
        mRecyclerView.setHasFixedSize(true);
        mAdapter=new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickedListener(new ExampleAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
                intent.putExtra("image",exampleList.get(position).getmImageResource());
                intent.putExtra("title",exampleList.get(position).getmText1());
                intent.putExtra("difficulty",exampleList.get(position).getmText2());
                intent.putExtra("list",exampleList.get(position).getListaVezbi());
                startActivity(intent);
            }
        });
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

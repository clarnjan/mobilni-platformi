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

import com.example.mobilniproekt.ExampleAdapter;
import com.example.mobilniproekt.ExampleItem;
import com.example.mobilniproekt.LoggedInActivity;
import com.example.mobilniproekt.MapsActivity;
import com.example.mobilniproekt.R;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BuildMuscle extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleItem> exampleList;
    ArrayList<String> listaVezbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_muscle);
        createList();
        buildRecyclerView();
    }

    private void createList() {
        exampleList=new ArrayList<>();
        listaVezbi=new ArrayList<>();
        listaVezbi.add("2x10 Bench press");
        listaVezbi.add("2x10 Incline bench press");
        listaVezbi.add("3x30s Plate Pressout");
        listaVezbi.add("3x10 Dumbbell Flye");
        listaVezbi.add("3x8  Incline Dumbbell Flye");
        exampleList.add(new ExampleItem(R.drawable.chestgirl,"Chest Workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x5  Pull-Up");
        listaVezbi.add("3x10 Lat Pull-Downs");
        listaVezbi.add("3x10 Reverse fly");
        listaVezbi.add("3x8  Bent-Over Row");
        listaVezbi.add("3x8  Kettlebell Swings");
        listaVezbi.add("2x10 Single Arm Row");
        exampleList.add(new ExampleItem(R.drawable.back,"Back Workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x10 BARBELL CURL");
        listaVezbi.add("3x10 HAMMER CURL");
        listaVezbi.add("3x8  DECLINE DUMBBELL CURL");
        listaVezbi.add("3x8  INCLINE DUMBBELL CURL");
        exampleList.add(new ExampleItem(R.drawable.bicepsgirl,"Biceps Workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x6  Tricep Dips");
        listaVezbi.add("3x10 Skullcrushers");
        listaVezbi.add("3x10 Rope Tricep Pushdown");
        listaVezbi.add("3x10 Overhead Triceps Extension");
        exampleList.add(new ExampleItem(R.drawable.triceps,"Triceps Workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("4x10 Squats");
        listaVezbi.add("3x10 Lunges");
        listaVezbi.add("2x10 Reverse Lunges");
        listaVezbi.add("3x10 Step-ups");
        listaVezbi.add("3x8  Leg Press");
        exampleList.add(new ExampleItem(R.drawable.legs,"Leg Workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x20 Situp");
        listaVezbi.add("3x45s Plank");
        listaVezbi.add("3x8  Leg Raise");
        listaVezbi.add("3x10 Knee Raise");
        listaVezbi.add("3x8  Ab Wheel Rollout");
        exampleList.add(new ExampleItem(R.drawable.abs,"Ab Workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x10 Bench press");
        listaVezbi.add("3x5  Dips");
        listaVezbi.add("3x10 Incline bench press");
        listaVezbi.add("3x8  Rope Tricep Pushdown");
        listaVezbi.add("3x8  Dumbbell Flye");
        listaVezbi.add("3x8  Skullcrushers");
        exampleList.add(new ExampleItem(R.drawable.chest,"Chest & Triceps","Advanced",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x7  Chin-ups");
        listaVezbi.add("3x10 Lat Pull-Downs");
        listaVezbi.add("3x10 Reverse fly");
        listaVezbi.add("3x8  Bent-Over Row");
        listaVezbi.add("3x10 BARBELL CURL");
        listaVezbi.add("3x10 HAMMER CURL");
        exampleList.add(new ExampleItem(R.drawable.backgirl,"Back & Biceps","Advanced",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x6  Pull-Up");;
        listaVezbi.add("3x7  Bench press");
        listaVezbi.add("3x7  Incline bench press");
        listaVezbi.add("3x10 Lat Pull-Downs");
        listaVezbi.add("3x7  BARBELL CURL");
        listaVezbi.add("3x10 Rope Tricep Pushdown");
        listaVezbi.add("3x7  Overhead Triceps Extension");
        listaVezbi.add("3x7  HAMMER CURL");
        exampleList.add(new ExampleItem(R.drawable.full,"Full body workout","Advanced",listaVezbi));
    }

    private void buildRecyclerView() {
        mRecyclerView=findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
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
        startActivity(new Intent(getApplicationContext(), LoggedInActivity.class));
    }

    private void NeerbyGym(){
        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
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

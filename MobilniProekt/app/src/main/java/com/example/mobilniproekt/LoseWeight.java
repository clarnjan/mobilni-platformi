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

public class LoseWeight extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleItem> exampleList;
    ArrayList<String> listaVezbi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_weight);
        createList();
        buildRecyclerView();
    }

    private void createList() {
        exampleList=new ArrayList<>();
        listaVezbi=new ArrayList<>();
        listaVezbi.add("30min Cycling");
        listaVezbi.add("2x10  Lunge");
        listaVezbi.add("2x10  Kettlebell Swing");
        listaVezbi.add("3x10  Bodyweight Balance");
        listaVezbi.add("15min Walking");
        exampleList.add(new ExampleItem(R.drawable.bike,"Cycling workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("10min Walking");
        listaVezbi.add("20min Running");
        listaVezbi.add("3x10  Tabata Drill");
        listaVezbi.add("3x30  Jumping Jacks");
        listaVezbi.add("20min Running");
        exampleList.add(new ExampleItem(R.drawable.run,"Runnig workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("10min Walking");
        listaVezbi.add("3x10  Sit-ups");
        listaVezbi.add("3x30  Jumping Jacks");
        listaVezbi.add("3x10  Flutter Kick");
        listaVezbi.add("2x60s Plank");
        listaVezbi.add("20min Running");
        exampleList.add(new ExampleItem(R.drawable.situp,"Ab workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("10min Walking");
        listaVezbi.add("20min Running");
        listaVezbi.add("3x30  Jumping Jacks");
        listaVezbi.add("3x10  Mountain Climbers");
        listaVezbi.add("3x60s Jump Rope");
        listaVezbi.add("10min Walking");
        exampleList.add(new ExampleItem(R.drawable.jumprope,"Cardio","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("3x10  Push-ups");
        listaVezbi.add("3x30  Jumping Jacks");
        listaVezbi.add("3x10  Kettlebell Swing");
        listaVezbi.add("3x45s Plank");
        listaVezbi.add("3x10  Squats");
        listaVezbi.add("30min Cycling");
        exampleList.add(new ExampleItem(R.drawable.jumpropegirl,"Full Body workout","Beginner",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("10min Walking");
        listaVezbi.add("3x40  Jumping Jacks");
        listaVezbi.add("4x10  Explosive Lunge");
        listaVezbi.add("3x20  Mountain Climbers");
        listaVezbi.add("3x60s Jump Rope");
        listaVezbi.add("40min Running");
        exampleList.add(new ExampleItem(R.drawable.bike2,"High-Cardio workout","Advanced",listaVezbi));
        listaVezbi=new ArrayList<>();
        listaVezbi.add("25min Cycling");
        listaVezbi.add("3x10  Dips");
        listaVezbi.add("3x50  Jumping Jacks");
        listaVezbi.add("3x15  Kettlebell Swing");
        listaVezbi.add("3x60s Plank");
        listaVezbi.add("3x15  Push-ups");
        listaVezbi.add("3x20  Lunges");
        listaVezbi.add("30min Cycling");
        exampleList.add(new ExampleItem(R.drawable.pushupguy,"Intense Full Body","Advanced",listaVezbi));
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

package com.example.recipes;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements CustomAdapter.customClickListener {
    DataBaseHelper mDatabaseHelper;
    SQLiteDatabase mDatabase;
    CustomAdapter customAdapter;
    RecyclerView nov;
    ArrayList<String> lista;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_home,container,false);
        mDatabaseHelper=new DataBaseHelper(getContext());
        mDatabase=mDatabaseHelper.getWritableDatabase();
        Cursor mCursor = mDatabase.rawQuery("SELECT * FROM tabela", null);
        if(mCursor.getCount() == 0)
            createList();
        nov=v.findViewById(R.id.recyclerview);
        nov.setLayoutManager(new LinearLayoutManager(getContext()));
        nov.setAdapter(new CustomAdapter(getActivity(),mDatabase.query("tabela",null,null,null,null,null,null),this));

        return v;
    }

    @Override
    public void onCustomClick(int position) {
        Cursor cursor=mDatabase.rawQuery("select * from tabela",null);
        if(cursor.getCount()==0){}
        else{
            cursor.moveToPosition(position);
            String name=cursor.getString(1);
            lista=new ArrayList<>();
            lista.add(cursor.getString(2));
            lista.add(cursor.getString(3));
            lista.add(cursor.getString(4));
            lista.add(cursor.getString(5));
            lista.add(cursor.getString(6));
            lista.add(null);
            byte[] image=cursor.getBlob(7);
            String portions=cursor.getString(8);
            String calories=cursor.getString(9);
            Intent intentStart=new Intent(getContext(),DetailsActivity.class);
            intentStart.putExtra("title",name);
            intentStart.putExtra("list",lista);
            intentStart.putExtra("img",image);
            intentStart.putExtra("portions",portions);
            intentStart.putExtra("calories",calories);
            startActivity(intentStart);
        }
    }

    private void createList() {
        Resources res=getResources();

        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        Drawable d=res.getDrawable(R.drawable.risotto);
        Bitmap bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] eden = stream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "Chorizo & pea risotto");
        contentValues.put("i1", "200g chorizo , peeled and chopped");
        contentValues.put("i2","300g arborio risotto rice" );
        contentValues.put("i3", "1.2l chicken stock (fresh is best), heated until simmering");
        contentValues.put("i4", "200g frozen peas");
        contentValues.put("i5","60g parmesan , finely grated, plus extra to serve");
        contentValues.put("img",eden);
        contentValues.put("portions","4");
        contentValues.put("calories","642");
        mDatabase.insert("tabela", null, contentValues);

        ByteArrayOutputStream stream1= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.pork_tacos);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream1);
        byte[] dva = stream1.toByteArray();

        contentValues.put("name", "Pork tacos");
        contentValues.put("i1", "3 whole ancho chiles & 3 whole pasilla chiles");
        contentValues.put("i2","1/2 medium white onion & 4 cloves garlic" );
        contentValues.put("i3", "Corn tortillas, warmed");
        contentValues.put("i4", "1 tablespoon cider vinegar");
        contentValues.put("i5","2 bay leaves");
        contentValues.put("img",dva);
        contentValues.put("portions","2");
        contentValues.put("calories","1150");
        mDatabase.insert("tabela", null, contentValues);

        ByteArrayOutputStream stream2= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.cooked);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream2);
        byte[] tri = stream2.toByteArray();

        contentValues.put("name", "Marinated Chicken Breasts");
        contentValues.put("i1", "1 to 2 tablespoons vinegar, like cider, balsamic, or red wine");
        contentValues.put("i2","2 to 3 teaspoons dried herbs, like thyme, oregano, rosemary" );
        contentValues.put("i3", "1 to 2 tablespoons mustard");
        contentValues.put("i4", "1/4 cup extra-virgin olive oil");
        contentValues.put("i5","4 boneless, skinless chicken breast");
        contentValues.put("img",tri);
        contentValues.put("portions","2");
        contentValues.put("calories","1050");
        mDatabase.insert("tabela", null, contentValues);


        ByteArrayOutputStream stream3= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.sal);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream3);
        byte[] cetiri = stream3.toByteArray();
        contentValues.put("name", "Salmon Baked in Foil");
        contentValues.put("i1", "4 (5 ounces each) salmon fillets");
        contentValues.put("i2","3 tomatoes, chopped" );
        contentValues.put("i3", "2 chopped shallots");
        contentValues.put("i4", "2 tablespoons fresh lemon juice");
        contentValues.put("i5","1 teaspoon dried oregano & 1 teaspoon dried thyme");
        contentValues.put("img",cetiri);
        contentValues.put("portions","3");
        contentValues.put("calories","950");
        mDatabase.insert("tabela", null, contentValues);

        ByteArrayOutputStream stream4= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.herbed);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream4);
        byte[] pet = stream4.toByteArray();
        contentValues.put("name", "Herbed Chicken Marsala");
        contentValues.put("i1", "1/3 cup whole wheat flour");
        contentValues.put("i2","1 1/2 tablespoons extra-virgin olive oil" );
        contentValues.put("i3", "1/3 cup sun-dried tomatoes & 1/2 teaspoon finely chopped fresh rosemary");
        contentValues.put("i4", "1/3 cup sweet marsala wine");
        contentValues.put("i5","1 to 2 tablespoons roughly chopped fresh flat-leaf parsley");
        contentValues.put("img",pet);
        contentValues.put("portions","2");
        contentValues.put("calories","1000");
        mDatabase.insert("tabela", null, contentValues);

        ByteArrayOutputStream stream5= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.pasta);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream5);
        byte[] sest = stream5.toByteArray();
        contentValues.put("name", "Pasta and Beans");
        contentValues.put("i1", "1 quart chicken stock");
        contentValues.put("i2","1 1/2 cups ditalini" );
        contentValues.put("i3", "Two 15-ounce cans cannellini beans");
        contentValues.put("i4", "1 medium onion & 4 large cloves garlic");
        contentValues.put("i5","1/8 pound (about 3 slices) pancetta");
        contentValues.put("img",sest);
        contentValues.put("portions","1");
        contentValues.put("calories","400");
        mDatabase.insert("tabela", null, contentValues);

        ByteArrayOutputStream stream6= new ByteArrayOutputStream();
        d=res.getDrawable(R.drawable.piperki);
        bitmap=((BitmapDrawable)d).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream6);
        byte[] sedum = stream6.toByteArray();
        contentValues.put("name", "Roman-Style Chicken");
        contentValues.put("i1", "4 skinless chicken breast halves, with ribs");
        contentValues.put("i2","2 skinless chicken thighs, with bones" );
        contentValues.put("i3", "1/2 teaspoon salt, 1/2 teaspoon freshly ground black pepper");
        contentValues.put("i4", "1 tablespoon fresh thyme leaves");
        contentValues.put("i5","1/2 cup white wine");
        contentValues.put("img",sedum);
        contentValues.put("portions","4");
        contentValues.put("calories","1600");
        mDatabase.insert("tabela", null, contentValues);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}

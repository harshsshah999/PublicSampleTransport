package com.orka.publicsampletransport;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdminDisplayRecords extends AppCompatActivity {

    TextView texturl,info,emp;
    ImageView image;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference ref;
    // Query query=reference.orderByChild("sno").orderByChild().orderByChild().equalTo();
    private int count;

    //private Button insertButton;
    ArrayList<ExampleItem> exampleList=new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

       // createExampleList();
        buildRecyclerView();


        emp=(TextView) findViewById(R.id.texturl);
        info=(TextView)findViewById(R.id.info);
        image=(ImageView)findViewById(R.id.image);
        ref= FirebaseDatabase.getInstance().getReference("staff");
        Query query=ref.orderByChild("sno").equalTo(1);




        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
//                        count=1;
//                        while (true)
//                        {
/*
                        String staff= snapshot.child("images").child("img1").child("image").getValue().toString();
                        String caption=snapshot.child("images").child("img1").child("Info").getValue().toString();
                        String text= (String) snapshot.child("sname").getValue();
                        texturl.setText(text);
                        info.setText(caption);
                        Picasso.get().load(staff).into(image);

                        // count++;*/
                        //}



                        String img= snapshot.child("image").getValue().toString();
                        String caption=snapshot.child("info").getValue().toString();
                        String text= (String) snapshot.child("sname").getValue();
                      //  emp.setText(text);
                       // info.setText(caption);

                        insertItem(0,img,caption,text);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void insertItem(int position,String imge,String tx1,String tx2){
        exampleList.add(position,new ExampleItem(imge,tx1,tx2));
        mAdapter.notifyDataSetChanged();
    }

   public Bitmap getBit(String url){
           Bitmap mIcon11 = null;
           try {
               InputStream in = new java.net.URL(url).openStream();
               mIcon11 = BitmapFactory.decodeStream(in);
           } catch (Exception e) {
               Log.e("Error", e.getMessage());
               e.printStackTrace();
           }
           return mIcon11;

   }


    public void createExampleList(){
        exampleList.add(new ExampleItem("https://kidshealth.org/EN/images/illustrations/ASDestab_433x259_enIL.png","Line1","Line2"));

    }

    public void buildRecyclerView(){
        mRecyclerView=findViewById(R.id.Recycler);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }







}

package com.orka.publicsampletransport;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Find extends AppCompatActivity {

    DatabaseReference db;
    ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        img=(ImageView) findViewById(R.id.imageView);
        txt=findViewById(R.id.textView);
        db= FirebaseDatabase.getInstance().getReference("staff");
        Query query=db.orderByChild("sno").equalTo(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                      //  Staff staff=snapshot.getValue(Staff.class);
                        String stf=snapshot.child("images").child("img1").child("image").getValue().toString();
                        txt.setText(stf);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.orka.publicsampletransport;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import android.os.Bundle;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.database.ValueEventListener;
        import com.squareup.picasso.Picasso;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

public class AdminDisplayRecords extends AppCompatActivity {

    TextView texturl,info;
    ImageView image;

    private DatabaseReference ref;
    // Query query=reference.orderByChild("sno").orderByChild().orderByChild().equalTo();
    private int count;



    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        texturl=(TextView) findViewById(R.id.texturl);
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

                        String staff= snapshot.child("images").child("img1").child("image").getValue().toString();
                        String caption=snapshot.child("images").child("img1").child("Info").getValue().toString();
                        String text= (String) snapshot.child("sname").getValue();
                        texturl.setText(text);
                        info.setText(caption);
                        Picasso.get().load(staff).into(image);

                        // count++;
                        //}
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





}


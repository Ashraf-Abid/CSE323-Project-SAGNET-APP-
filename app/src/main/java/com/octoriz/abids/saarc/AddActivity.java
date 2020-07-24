package com.octoriz.abids.saarc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.octoriz.abids.saarc.Adapter.NameAdapter;
import com.octoriz.abids.saarc.Entity.Name;
import com.octoriz.abids.saarc.ViewModel.ShakeViewModel;

import java.util.List;
import java.util.Objects;

public class AddActivity extends AppCompatActivity {

    private int id, numOfPeople = 0, counter=0;
    private String name;
    private AppCompatEditText edtNum, edtName;
    private AppCompatButton btnSetNum, btnAddPeople;
    private RecyclerView recyclerNameList;
    private ShakeViewModel shakeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edtNum = findViewById(R.id.edtNumber);
        edtName = findViewById(R.id.edtName);
        btnSetNum = findViewById(R.id.btnNumFix);
        btnAddPeople = findViewById(R.id.btnAddPeople);
        recyclerNameList = findViewById(R.id.recyclerNameList);

        shakeViewModel = ViewModelProviders.of(this).get(ShakeViewModel.class);

        Intent intent = getIntent();
        if(intent.hasExtra("id")){
            id = intent.getIntExtra("id",-1);
            name = intent.getStringExtra("name");
            setTitle(Html.fromHtml("<font color='#FFFFFF'>"+name+"</font>"));

            recyclerNameList.setLayoutManager(new LinearLayoutManager(this));
            recyclerNameList.setHasFixedSize(true);

            final NameAdapter adapter = new NameAdapter();
            recyclerNameList.setAdapter(adapter);

            shakeViewModel.getNameList(id).observe(this, new Observer<List<Name>>() {
                @Override
                public void onChanged(List<Name> names) {
                    adapter.setNameList(names);
                }
            });
        }

        //back btn in toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnSetNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(!TextUtils.isEmpty(edtNum.getText())){
                    numOfPeople = Integer.parseInt(Objects.requireNonNull(edtNum.getText()).toString());
                    if(numOfPeople<1)
                    {
                        Toast.makeText(AddActivity.this, "Provide Valid number", Toast.LENGTH_SHORT).show();
                    }else{
                        edtNum.setEnabled(false);
                        Toast.makeText(AddActivity.this, "Good! Now Add People", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AddActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                }
             
            }
        });
        
        btnAddPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(edtName.getText()))
                {
                    if(numOfPeople>0){
                        if(counter<numOfPeople){
                            counter++;
                            saveName(edtName.getText().toString());
                        }else{
                            Toast.makeText(AddActivity.this, "you've added all the people", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AddActivity.this, "Enter Number of People First", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AddActivity.this, "Name is Empty", Toast.LENGTH_SHORT).show();
                }
               
            }
        });

    }

    private void saveName(String name) {
        Name name1 = new Name(id,name);
        shakeViewModel.insert(name1);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

package com.example.dpl1.saptarshi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserHome extends AppCompatActivity implements View.OnClickListener{

    ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10;
    TextView consumerName;
    String conNo;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        conNo=extras.getString("conNo");
        this.setTitle("Welcome, "+name);
        iv1=(ImageView)findViewById(R.id.imageView3);
        iv1.setOnClickListener(this);
        iv2=(ImageView)findViewById(R.id.imageView4);
        iv2.setOnClickListener(this);
        iv3=(ImageView)findViewById(R.id.imageView9);
        iv3.setOnClickListener(this);
        iv4=(ImageView)findViewById(R.id.imageView7);
        iv4.setOnClickListener(this);
        iv5=(ImageView)findViewById(R.id.imageView5);
        iv5.setOnClickListener(this);
        //consumerName=(TextView)findViewById(R.id.textView2);
        //consumerName.setText(name);

    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if(v == iv1){
            Intent intent = new Intent(getApplicationContext(),Dashboard.class);
            intent.putExtra("conNo",conNo);
            startActivity(intent);
        }
        if(v == iv2){
            Intent intent = new Intent(getApplicationContext(),CurrentBill.class);
            intent.putExtra("conNo",conNo);
            intent.putExtra("conName",name);
            startActivity(intent);
        }
        if(v == iv3){

            Intent intent = new Intent(getApplicationContext(),TariffView.class);
            intent.putExtra("conNo",conNo);
            intent.putExtra("conName",name);
            startActivity(intent);
        }
        if(v == iv4){

            Intent intent = new Intent(getApplicationContext(),Consumption.class);
            intent.putExtra("conNo",conNo);
            startActivity(intent);
        }

        if(v == iv5){

            Intent intent = new Intent(getApplicationContext(),BillHistory.class);
            intent.putExtra("conNo",conNo);
            startActivity(intent);
        }



    }
}

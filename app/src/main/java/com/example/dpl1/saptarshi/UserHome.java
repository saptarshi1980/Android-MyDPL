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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        this.setTitle("DPL- User Home");
        Bundle extras=getIntent().getExtras();
        String name=extras.getString("name");
        conNo=extras.getString("conNo");
        System.out.println("****************** CONSUMER DATA ******************************************");
        System.out.println("NAME-"+name);
        System.out.println("PARTY CODE-"+conNo);
        System.out.println("************************************************************");

        iv1=(ImageView)findViewById(R.id.imageView3);
        iv1.setOnClickListener(this);
        consumerName=(TextView)findViewById(R.id.textView2);
        consumerName.setText(name);

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

    }
}

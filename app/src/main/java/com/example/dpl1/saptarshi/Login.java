package com.example.dpl1.saptarshi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private EditText consumerNo,meterNo;
    private static final String REGISTER_URL = "http://thedpl.in/billappws/billinfo/ConsAuth";
    ArrayList<Consumer> consumerList=new ArrayList<Consumer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("DPL- Login");
        consumerNo = (EditText) findViewById(R.id.editText);
        meterNo = (EditText) findViewById(R.id.editText2);
        buttonLogin = (Button) findViewById(R.id.button);
        buttonLogin.setOnClickListener(this);


    }


    public void onClick(View v) {
        if(v == buttonLogin){
            //registerUser();

            //Intent intent = new Intent(getApplicationContext(), UserHome.class);
            //intent.putExtra("name","saptarshi");
            //startActivity(intent);

            registerUser(consumerNo.getText().toString(),meterNo.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void registerUser(String consumerNo,String meterNo) {
        register(consumerNo, meterNo);
    }

    private void register(String consumerNo, String meterNo) {
        class GetInformation extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            GetInfo ruc = new GetInfo();


            @Override
            protected void onPreExecute() {
                //adapter.clear();
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {


                if(s.matches("UNAUTHORIZED"))
                {
                    Toast.makeText(getApplicationContext(), "Invalid Consumer Number/Meter Number", Toast.LENGTH_LONG).show();

                }

                else
                {
                    String name=s.substring(0,s.indexOf("|"));
                    String conNo=s.substring(s.indexOf("|") + 1, s.length());
                    Intent intent = new Intent(getApplicationContext(), UserHome.class);
                    intent.putExtra("name",name);
                    intent.putExtra("conNo",conNo);
                    startActivity(intent);
                    finish();

                }
                loading.dismiss();
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("conNo",params[0]);
                data.put("meterNo",params[1]);
                String consumerName;
                String consumerNo=params[0];
                String meterNo=params[1];


                String result = ruc.sendPostRequest(REGISTER_URL,data);

                System.out.println("Here is my data from consumer authentication-"+result);


                /*try{
                    JSONArray contacts = new JSONArray(result);
                    for (int i = 0; i < contacts.length(); i++)
                    {
                        Consumer consumer=new Consumer();
                        JSONObject consumerjson = contacts.getJSONObject(i);
                        String name = consumerjson.getString("name");
                        consumer.setConsumerName(name);
                        consumerList.add(consumer);
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                        System.out.println("Consumer Name-"+name);
                        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

                       // Log.e("Parsed data is", "Title:" + title + "||URL:" + url + "||Description: " + description + " ||ID: " + id);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                return  result;
            }
        }

        GetInformation ru = new GetInformation();
        ru.execute(consumerNo,meterNo);
    }



}

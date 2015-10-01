package com.example.dpl1.saptarshi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class UpdateEmail extends AppCompatActivity implements View.OnClickListener{
    EditText et;
    String emailId,conNo;
    Button submit;
    private static final String REGISTER_URL = "http://thedpl.in/billappws/billinfo/UpdateEmail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);
        et=(EditText)findViewById(R.id.editText3);
        Bundle extras=getIntent().getExtras();
        conNo=extras.getString("conNo");
        this.setTitle("Update Email..");
        emailId=et.getText().toString();
        submit=(Button)findViewById(R.id.button2);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == submit){
            emailId=et.getText().toString();
            register(conNo, emailId);
        }
    }

    private void register(String consumerNo,String emailId) {
        class GetInformation extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            GetInfo ruc = new GetInfo();


            @Override
            protected void onPreExecute() {
                //adapter.clear();
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateEmail.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {


                if(s.matches("SUCCESS"))
                {
                    Toast.makeText(getApplicationContext(), "Email Id Updated !!!!!", Toast.LENGTH_LONG).show();
                    et.setText("");

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_LONG).show();

                }
                loading.dismiss();
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("conNo",params[0]);
                data.put("emailId",params[1]);
                String result = ruc.sendPostRequest(REGISTER_URL,data);
                System.out.println("Here is my data from consumer authentication-"+result);
                return  result;
            }
        }

        GetInformation ru = new GetInformation();
        ru.execute(consumerNo,emailId);
    }
}

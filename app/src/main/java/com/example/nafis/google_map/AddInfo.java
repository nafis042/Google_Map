package com.example.nafis.google_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddInfo extends AppCompatActivity {

    EditText et1,et2,et3;
    Button button;
    boolean update=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
       et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        button=(Button)findViewById(R.id.button);
        button.setVisibility(View.VISIBLE);
        Bundle bundle =getIntent().getExtras();
        if(bundle!=null)
        {
            String  getinfo = bundle.getString("view");
            if(getinfo==null) getinfo = bundle.getString("update");
            String[]token = getinfo.split("@");

            if(token[token.length-1].equals("view"))
            {
                button.setVisibility(View.INVISIBLE);
                et1.setText(token[0]);
                et2.setText(token[1]);
                et3.setText(token[2]);
                et1.setEnabled(false);

            }
            else
            {
                et1.setText(token[0]);
                et2.setText(token[1]);
                et3.setText(token[2]);
                update=true;
            }
        }


    }
    public void submitclick(View v)
    {

        String storedinfo=null;
        if(update==true)
                storedinfo= et1.getText()+"@"+et2.getText()+"@"+et3.getText()+"@"+"update";
        else
            storedinfo= et1.getText()+"@"+et2.getText()+"@"+et3.getText()+"@"+"insert";
        Intent intent = new Intent(AddInfo.this,MainActivity.class);
        Toast.makeText(AddInfo.this,storedinfo,Toast.LENGTH_SHORT).show();
        intent.putExtra("data",storedinfo);
        startActivity(intent);
    }
}

package com.pronoymukherjee.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditTextActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FileController fileController=new FileController(this);
        EditText editText=(EditText) findViewById(R.id.text);
        String data=editText.getText().toString();
        if(!data.equals("")) {
            fileController.saveData(data);
            Messages.ToastMessage(this,"Saved.","");
        }
        else
            Messages.ToastMessage(this,"Nothing to save.","");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        String data;
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            data = bundle.getString(Constants.CURRENT_FILE);
            EditText editText=(EditText) findViewById(R.id.text);
            editText.setText(data);
        }
        catch (Exception e){Messages.LogMessage("ERROR: ",e.toString());}
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

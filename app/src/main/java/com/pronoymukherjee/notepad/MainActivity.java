package com.pronoymukherjee.notepad;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FileController fileController;
    ArrayList<String> files=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileController=new FileController(MainActivity.this);
        Constants.NUMBER_FILES=fileController.getFileNumber();
        Messages.LogMessage("FILE NUMBER: ",Constants.NUMBER_FILES+"");
        populateList();
        final ListView listView = (ListView) findViewById(R.id.notesList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = fileController.openFile(listView.getItemAtPosition(i).toString());
                Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constants.CURRENT_FILE, data);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(Constants.ID_DELETE);
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create:
                CreateNew();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPause() {
        super.onPause();
        fileController.saveFileNumber();
    }

    private void CreateNew(){
        Constants.NUMBER_FILES++;
        //updateSharedPreferencesData();
        Intent intent= new Intent(MainActivity.this,EditTextActivity.class);
        startActivity(intent);
    }
    private void populateList(){
        int pos=0;
        File dir=getDir(Constants.FOLDER_NAME,MODE_PRIVATE);
        for(String fileName: dir.list()){
            files.add(fileName);
        }
        if(!files.isEmpty()) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, files);
            ListView listView = (ListView) findViewById(R.id.notesList);
            listView.setAdapter(arrayAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateList();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==Constants.ID_DELETE) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(R.string.deleteMessage);
            builder.setPositiveButton(R.string.postiveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setNegativeButton(R.string.negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        }
        return super.onCreateDialog(id);
    }
}

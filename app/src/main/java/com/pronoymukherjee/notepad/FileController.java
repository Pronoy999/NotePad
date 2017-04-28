package com.pronoymukherjee.notepad;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Scanner;


public class FileController {
    private Context context;
    protected FileController(Context context){
        this.context=context;
    }
    protected boolean saveData(String data){
        data=Encode_Decode.encodeText(data);
        Calendar calendar=Calendar.getInstance();
        int h=calendar.get(Calendar.HOUR_OF_DAY);
        int m=calendar.get(Calendar.MINUTE);
        int s=calendar.get(Calendar.SECOND);
        String fileName=h+":"+m+":"+s;
        File dir=context.getDir(Constants.FOLDER_NAME,Context.MODE_PRIVATE);
        File file=new File(dir,fileName);
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
        }
        catch (Exception e){
            Messages.LogMessage("ERROR: ",e.toString());
            return false;
        }
        return true;
    }
    protected String openFile(String openfileName){
        String text="";
        File dir=context.getDir(Constants.FOLDER_NAME,Context.MODE_PRIVATE);
        boolean isFileFound=false;
        for(String fileName:dir.list()){
            if(fileName.equalsIgnoreCase(openfileName)) {
                isFileFound=true;
                break;
            }
        }
        if(isFileFound) {
            try {
                FileInputStream fileInputStream = context.openFileInput(openfileName);
                Scanner scanner = new Scanner(fileInputStream);
                scanner.useDelimiter("_");
                while (scanner.hasNext()) {
                    text += scanner.next();
                }
                text = Encode_Decode.decodeText(text);
            } catch (Exception e) {
                Messages.ToastMessage(context, e.toString(), "long");
                return text;
            }
        }
        else {
            Messages.ToastMessage(context,"File Not Found.","");
        }
        return text;
    }
    protected int getFileNumber(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.settings,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Constants.fileNumbers,0);
    }
    protected void saveFileNumber(){
        SharedPreferences sharedPreferences=context.getSharedPreferences(Constants.settings,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(Constants.fileNumbers,Constants.NUMBER_FILES);
        editor.apply();
        editor.commit();
    }
}

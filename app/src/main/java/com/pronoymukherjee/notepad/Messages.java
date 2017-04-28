package com.pronoymukherjee.notepad;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pronoymukherjee on 22/03/17.
 */

public class Messages {
    public static void ToastMessage(Context context,String msg,String delay){
        if(delay.equals("") || delay.equals("short"))
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
    public static void LogMessage(String tag,String msg){
        Log.d(tag,msg);
    }
}

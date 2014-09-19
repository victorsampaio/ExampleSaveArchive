package com.app.examplesavearchive;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveArchive extends Activity {

    private static final String ARCHIVE = "archive.txt";
    private static final String CATEGORY = "saveArchive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_archive);

        Button button= (Button) findViewById(R.id.btSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });


        // DELETE
        button = (Button) findViewById(R.id.btDelete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // boolean ok = deleteFile(ARCHIVE);
                //Log.i(CATEGORY, "Archive Deleted " + ok);
                delete();
                //visualizeArchive();
            }
        });
        visualizeArchive();
    }

    private void visualizeArchive() {
        TextView text = (TextView) findViewById(R.id.archive);
        try {
            File file = getFileStreamPath(ARCHIVE);
            Log.i(CATEGORY, "Opening Archive: " + file.getAbsolutePath());

            if (file.exists()) {
                FileInputStream inputStream = openFileInput(ARCHIVE);
                int size = inputStream.available();
                byte bytes[] = new byte[size];
                inputStream.read(bytes);

                // Sring 's'
                String s = new String(bytes);
                text.setText(s);
            } else {
                Log.e(CATEGORY, "Archive not found or deleted");
                text.setText("");
            }

        } catch (FileNotFoundException e) {
            Log.e(CATEGORY, "Not Found" + e.getMessage(), e);

        } catch (IOException e) {
            Log.e(CATEGORY, e.getMessage(), e);
        }


   /**
         @Override public boolean onCreateOptionsMenu(Menu menu) {
         // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.save_archive, menu);
         return true;
         }

         @Override public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();
         if (id == R.id.action_settings) {
         return true;
         }
         return super.onOptionsItemSelected(item);
         }*/
    }

    //DELETE - METHOD
    protected void delete(){
        boolean ok = deleteFile(ARCHIVE);
        Log.i(CATEGORY, "Archive Deleted " + ok);
        visualizeArchive();
    }

    //SAVE - METHOD
    protected void save(){
        try {
            FileOutputStream out = openFileOutput(ARCHIVE, MODE_APPEND);

            EditText text = (EditText) findViewById(R.id.text);

            String msg = text.getText().toString();

            out.write("\n".getBytes());
            out.write(msg.getBytes());
            out.close();

            Log.i(CATEGORY, msg + " - writing successfully");

            visualizeArchive();

        } catch (FileNotFoundException e) {
            Log.e(CATEGORY, e.getMessage(), e);
        } catch (IOException e) {
            Log.e(CATEGORY, e.getMessage(), e);
        }
    }

    }





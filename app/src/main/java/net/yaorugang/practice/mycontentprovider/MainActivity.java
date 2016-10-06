package net.yaorugang.practice.mycontentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view)
    {
        String name = ((EditText)findViewById(R.id.editText2)).getText().toString();
        String grade = ((EditText)findViewById(R.id.editText3)).getText().toString();

        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, name);
        values.put(StudentsProvider.GRADE, grade);

        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);

        Toast.makeText(this, uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view)
    {
        String URL = "content://net.yaorugang.provider.College/students";
        Uri uri = Uri.parse(URL);

        Cursor c = getContentResolver().query(uri, null, null, null, "name");

        if (c != null)
        {
            String list = "";
            while (c.moveToNext())
            {
                list += String.format("Name: %s, Grade: %s\n",
                        c.getString(c.getColumnIndex(StudentsProvider.NAME)),
                        c.getString(c.getColumnIndex(StudentsProvider.GRADE)));
            }

            Toast.makeText(this, list, Toast.LENGTH_LONG).show();
        }
    }
}

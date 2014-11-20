package com.kekwanu.passingdataviaintentsexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements Button.OnClickListener{
    private static String TAG = MainActivity.class.getCanonicalName();

    private EditText make;
    private EditText model;
    private EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        make = (EditText)findViewById(R.id.make_input);
        model = (EditText)findViewById(R.id.model_input);
        year = (EditText)findViewById(R.id.year_input);

        Button primitiveBtn = (Button)findViewById(R.id.primitive_type_button);
        primitiveBtn.setOnClickListener(this);

        Button serializerBtn = (Button)findViewById(R.id.serialized_type_button);
        serializerBtn.setOnClickListener(this);

        Button parcealableBtn = (Button)findViewById(R.id.parcealable_type_button);
        parcealableBtn.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {

        String makeStr;
        String modelStr;
        String yearStr;

        if (isEmpty(make.getText())) {

            Toast.makeText(this, getString(R.string.no_make), Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(model.getText())) {

            Toast.makeText(this, getString(R.string.no_model), Toast.LENGTH_SHORT).show();
        }
        else if (isEmpty(year.getText())) {

            Toast.makeText(this, getString(R.string.no_year), Toast.LENGTH_SHORT).show();
        }

        else {

            makeStr = make.getText().toString().trim();
            modelStr = model.getText().toString();
            yearStr = year.getText().toString();

            Intent intent = new Intent(this, ResultsActivity.class);

            switch (v.getId()) {
                case R.id.primitive_type_button:
                    intent.putExtra("type", "primitive");
                    intent.putExtra("make", makeStr);
                    intent.putExtra("model", modelStr);
                    intent.putExtra("year", yearStr);

                    break;

                case R.id.serialized_type_button:
                    CarSerializable carSerializable = new CarSerializable(makeStr, modelStr, yearStr);
                    intent.putExtra("type", "serializable");

                    //there are multiple ways to do this. Since this calss is serializable, you can use the objectToString method defined
                    // in the Serializer singleton to "flatten" the class to a String, then pass th string as an intent data, and reconstruct
                    //the CarSerializable object back from the received string in the called Activity, or
                    //you can just use a Bundle, which has internal/automatic methods to deal with Serializable objects.

                    //intent.putExtra("car", Serializer.INSTANCE.objectToString(carSerializable));

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("car", carSerializable);
                    intent.putExtras(bundle);

                    break;

                case R.id.parcealable_type_button:
                    CarParcealable carParcealable = new CarParcealable(makeStr, modelStr, yearStr);
                    intent.putExtra("type", "parcealable");
                    intent.putExtra("car", carParcealable);
                    break;
            }

            startActivity(intent);
        }
    }

    public boolean isEmpty(Editable editable){

        if (editable.equals(null)){
            Log.i(TAG, "isEmpty - editable is null");

            return true;
        }
        else{
            return editable.toString().trim().length() == 0;
        }
    }
}

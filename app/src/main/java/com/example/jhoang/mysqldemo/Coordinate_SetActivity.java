package com.example.jhoang.mysqldemo;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Coordinate_SetActivity extends AppCompatActivity {

    Coordinate_SetDatabaseHelper myDb;
    EditText editNumSet,editFront2Back,editSide2Side,editNumCounts, editMeasures, editFieldNum, editShowId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate__set);

        Intent extraIntent = getIntent();
        username = extraIntent.getStringExtra("username");
        password = extraIntent.getStringExtra("password");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new Coordinate_SetDatabaseHelper(this);
        editNumSet = (EditText)findViewById(R.id.editText_SetNumber);
        editFront2Back = (EditText)findViewById(R.id.editText_Frount2Back);
        editSide2Side = (EditText)findViewById(R.id.editText_Side2Side);
        editNumCounts = (EditText)findViewById(R.id.editText_NumCounts);
        editMeasures = (EditText)findViewById(R.id.editText_Measures);
        editFieldNum= (EditText)findViewById(R.id.editText_FieldNum);
        editShowId= (EditText)findViewById(R.id.editText_ShowId);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_coordinate, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.switchMain:
                Intent switchIntent = new Intent("com.example.jhoang.mysqldemo.Music_BookList");
                switchIntent.putExtra("username", username);
                switchIntent.putExtra("password", password);
                startActivity(switchIntent);
                break;

            case R.id.notification:
                Intent notifyIntent = new Intent(Coordinate_SetActivity.this, RecyclerViewList.class);
                notifyIntent.putExtra("username", username);
                notifyIntent.putExtra("password", password);
                startActivity(notifyIntent);
                break;

            case R.id.logout:
                String type = "logout";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, username, password);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editNumSet.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Coordinate_SetActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Coordinate_SetActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(
                                editNumSet.getText().toString(),
                                editFront2Back.getText().toString(),
                                editSide2Side.getText().toString(),
                                editNumCounts.getText().toString(),
                                editMeasures.getText().toString(),
                                editFieldNum.getText().toString(),
                                editShowId.getText().toString()
                        );
                        if (isUpdate == true)
                            Toast.makeText(Coordinate_SetActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Coordinate_SetActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                editNumSet.getText().toString(),
                                editFront2Back.getText().toString(),
                                editSide2Side.getText().toString(),
                                editNumCounts.getText().toString(),
                                editMeasures.getText().toString(),
                                editFieldNum.getText().toString(),
                                editShowId.getText().toString()
                        );
                        if (isInserted == true)
                            Toast.makeText(Coordinate_SetActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Coordinate_SetActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Number of Set :" + res.getString(1) + "\n");
                            buffer.append("Front to Back  :" + res.getString(2) + "\n");
                            buffer.append("Side to Side :" + res.getString(3) + "\n");
                            buffer.append("Number of Counts :" + res.getString(4) + "\n");
                            buffer.append("Measures :" + res.getString(5) + "\n");
                            buffer.append("Field Number :" + res.getString(6) + "\n");
                            buffer.append("Show ID :" + res.getString(7) + "\n\n");
                        }

                        // Show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}

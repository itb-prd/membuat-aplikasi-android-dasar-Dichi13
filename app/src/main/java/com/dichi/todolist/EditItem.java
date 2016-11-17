package com.dichi.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.dichi.todolist.MainActivity.itemsAdapter;

public class EditItem extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item2);
        setTitle("Ubah");
        Intent intents = getIntent();
        String isi = intents.getExtras().getString("Isi Array");
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        etNewItem.setText(isi);
    }

    public void onAddItem(View v) {
        Intent intents = getIntent();
        String isi = intents.getExtras().getString("Isi Array");
        int position = intents.getIntExtra("Hoho", 0);
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.insert(itemText, position);
        itemsAdapter.notifyDataSetChanged();
        Toast.makeText(EditItem.this,"To do '" + isi + "' diubah menjadi '" + itemText + "'.",Toast.LENGTH_LONG).show();
        finish();
    }
}

package com.dichi.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> items;
    public static ArrayAdapter<String> itemsAdapter;
    public ListView lvItems;
    int position;
    String isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddItem.class);
                startActivity(intent);
            }
        });

        setTitle("To Do List");

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        items.add("Belajar Android di kelas kita");
        items.add("Ngerjain tugas Android");
        items.add("Ngelayout Applikasi");
        items.add("Persiapan Ujian");
        items.add("Upload ke store aplikasi");
        items.add("Testing aplikasi yang dibuat");
        items.add("Pelajari tentang github lebih dalam");
        items.add("Buat resume tentang tugas Android");
        items.add("Mengumpulkan tugas to do list");
        setupListViewListener();
    }

    private void open(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apa yang ingin Anda lakukan dengan to do '" + items.get(position) + "'?");
                alertDialogBuilder.setPositiveButton("Hapus",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(MainActivity.this,"'" + items.get(position) + "' dihapus.",Toast.LENGTH_LONG).show();
                                items.remove(position);
                                itemsAdapter.notifyDataSetChanged();
                            }
                        });

        alertDialogBuilder.setNegativeButton("Edit",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isi = items.get(position);
                items.remove(position);
                Intent intents = new Intent(MainActivity.this, EditItem.class);
                intents.putExtra("Hoho", position);
                intents.putExtra("Isi Array", isi);
                startActivity(intents);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public final boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, final int pos, long id) {
                        position = pos;
                        open();
                        return true;
                    }
                });
    }
}
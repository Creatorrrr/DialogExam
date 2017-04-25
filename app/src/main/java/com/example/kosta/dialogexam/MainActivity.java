package com.example.kosta.dialogexam;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String[] ary = {"문재인", "안철수", "유승민"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder( MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle("Dialog-1")
                        .setMessage("Information")
                        .setNeutralButton("close", null)
                        .show();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog-2")
                        .setItems(ary, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new AlertDialog.Builder(MainActivity.this)
                                        .setMessage("선택한 사람은 " + which + ":" + ary[which]).show();
                            }
                        }).show();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog-3")
                        .setMultiChoiceItems(ary, new boolean[] {true, false, false}, null)
                        .setPositiveButton("Positive", null)
                        .setNeutralButton("Neutral", null)
                        .setNegativeButton("Negative", null)
                        .show();
            }
        });

        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.SEND_TO_VOICEMAIL};

        final Cursor cursor = this.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                projection, null, null, null);

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog-4")
                        .setMultiChoiceItems(
                                cursor,
                                ContactsContract.Contacts.SEND_TO_VOICEMAIL,
                                ContactsContract.Contacts.DISPLAY_NAME, null)
                        .show();
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                final View login = inflater.inflate(R.layout.login, null);

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Dialog-5")
                        .setView(login)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("CANCEL", null)
                        .show();
            }
        });
    }
}

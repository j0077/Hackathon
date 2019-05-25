package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class WriteActivity extends AppCompatActivity {

    //식당 아이디 받아오기
    Intent intent = getIntent();
    String id = intent.getExtras().getString("id");

    ImageView imageView;
    Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.uploadButton1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog();
            }
        });
        button = (Button) findViewById(R.id.uploadButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog();
            }
        });
        button = (Button) findViewById(R.id.uploadButton3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog();
            }
        });
    }
    private void makeDialog(){
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(WriteActivity.this);
        alt_bld.setTitle("사진 업로드").setCancelable(false).setPositiveButton(
                "앨범선택",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Log.v("알림", "다이얼로그 > 앨범선택 선택");
                        //앨범에서 선택
                        selectAlbum();
                    }
                }).setNegativeButton("취소   ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.v("알림", "다이얼로그 > 취소 선택");
                        // 취소 클릭. dialog 닫기.
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
    //앨범 선택 클릭
    public void selectAlbum(){
        //앨범 열기
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
    }
}
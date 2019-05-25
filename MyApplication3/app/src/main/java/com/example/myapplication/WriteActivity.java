package com.example.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.myapplication.adapters.ClickListener;
import com.example.myapplication.adapters.ImageAdapter;
import com.example.myapplication.adapters.ReviewAdapter;
import com.example.myapplication.models.ImageModel;
import com.example.myapplication.models.ReviewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WriteActivity extends AppCompatActivity {

    ImageAdapter imageAdapter;
    String id;

    ArrayList<String> images = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInAnonymously();

        TedPermission.with(this)
                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                    }
                })
                .setDeniedMessage("사진 추가를 위해서 사진 읽기와 카메라 권한이 필요합니다")
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();

        imageAdapter = new ImageAdapter();

        RecyclerView photoList = findViewById(R.id.rv_upload_photos);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        photoList.setLayoutManager(layoutManager);
        photoList.setAdapter(imageAdapter);

        Button btnAddPhoto = findViewById(R.id.btn_add_photo);
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAlbum();
            }
        });

        final EditText textView = findViewById(R.id.review_edit_text);
        final RatingBar ratingBar = findViewById(R.id.rating_bar);

        Button btnWrite = findViewById(R.id.btn_write);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewModel reviewModel = new ReviewModel();
                reviewModel.images = images;
                reviewModel.name = "김리뷰";
                reviewModel.restarunantId = id;
                reviewModel.vip = true;
                reviewModel.rate = (long)ratingBar.getRating();
                reviewModel.text = textView.getText().toString();

                FirebaseFirestore store = FirebaseFirestore.getInstance();
                Task<DocumentReference> task = store.collection("reviews-new").add(reviewModel);
                task.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        finish();
                    }
                });
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
        startActivityForResult(intent, 200);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            if (requestCode == 200) {
                Uri photoUri = data.getData();
                Cursor cursor = null;

                try {
                    String[] proj = { MediaStore.Images.Media.DATA };
                    cursor = getContentResolver().query(photoUri, proj, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    cursor.moveToFirst();

                    Uri uri = Uri.fromFile(new File(cursor.getString(column_index)));
                    FirebaseStorage storage = FirebaseStorage.getInstance();

                    final StorageReference ref = storage.getReference("reviews/" + id + "/" + uri.getLastPathSegment());
                    UploadTask task = ref.putFile(uri);
                    task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.i("Image", uri.toString());

                                    images.add(uri.toString());

                                    ImageModel model = new ImageModel();
                                    model.imageUri = uri.toString();

                                    imageAdapter.addItem(model);
                                }
                            });
                        }
                    });

                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                }

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
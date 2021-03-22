package com.example.musclemass;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gun0912.tedpermission.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Editprofile extends AppCompatActivity {
    private String id;
    private String pw;
    private String name;
    private String nickname;
    private ImageView mSetImageView;



    ArrayList<Userinfo> userinfo ;
    private int PICK_IMAGE = 1;
    private int CAPTURE_IMAGE = 2;
    private String imageFilePath;
    private Uri photoUri;

    ArrayList<ID_image> id_image;

    ArrayList<ID_image> save_ID_IMAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        loadData();

        final EditText edit_nickname = (EditText) findViewById(R.id.edit_nickname);

        id = userinfo.get(0).getId();
        pw = userinfo.get(0).getPw();
        name = userinfo.get(0).getName();
        nickname = userinfo.get(0).getNickname();

        edit_nickname.setText(nickname);


        mSetImageView = (ImageView) findViewById(R.id.imageView7);

        // 이미지 파일 저장되어있는거 로드해줘야함 //

        SharedPreferences sharedPreferences = getSharedPreferences("profile_image", MODE_PRIVATE);
        Gson gson4 = new Gson();
        String json4 = sharedPreferences.getString("profile_image","");
        Type type = new TypeToken<ArrayList<ID_image>>() {}.getType();
        save_ID_IMAGE = gson4.fromJson(json4, type);

        if (save_ID_IMAGE == null){

            save_ID_IMAGE = new ArrayList<>();
        }
        for (int i = 0; i<save_ID_IMAGE.size(); i++){

            if (id.equals(save_ID_IMAGE.get(i).getId())) {
                String image = save_ID_IMAGE.get(i).getImage();

                photoUri = Uri.parse(image);

                mSetImageView = (ImageView) findViewById(R.id.profile_image);


                // for문으로 아이디와 같은 값을 가진 사진만 넣어주면 회원마다 프로필 이미지가 달라짐 //
                Glide.with(this).asBitmap().load(photoUri).into(mSetImageView);
            }
        }


        ////








        //프로필 변경 하시겠습니까해서 했을때의 onclick //
        Button profile_accept = (Button) findViewById(R.id.profile_accept);
        profile_accept.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {

                        if (photoUri != null) {

                            nickname = edit_nickname.getText().toString();

                            Userinfo user = new Userinfo(id,pw,name,nickname);
                            userinfo.set(0,user);


                            // 쉐어드에 이미지& id 값 저장 //

                            String photo = photoUri.toString();
                            id_image = new ArrayList<>();

                            ID_image id_imag = new ID_image(id,photo);
                            id_image.add(id_imag);

                            // id와 image값을 arraylist로 만들어서 저장 //
                            SharedPreferences preferences = getSharedPreferences("profile_image",MODE_PRIVATE);
                            SharedPreferences.Editor edit = preferences.edit();
                            Gson gson1 = new Gson();
                            String json1 = gson1.toJson(id_image);
                            edit.putString("profile_image",json1);
                            edit.commit();

                            // 쉐어드에 현재접속 유저정보 & 변경된 닉네임 저장 //
                            SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            Gson gson = new Gson();
                            String json = gson.toJson(userinfo);
                            editor.putString("connectuser", json);
                            editor.commit();


                        }

                        Intent intent = new Intent(view.getContext(), Profile.class);
                        startActivity(intent);

                        finish();
                    }
                }
        );
        Button uploadimage = (Button) findViewById(R.id.uploadimage);
        uploadimage.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){

                        //권한체킹//

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            } else {
                                ActivityCompat.requestPermissions(Editprofile.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        }

                        // 카메라나 갤러리 접근 할 수 있게 하는 다이얼로그 //


                        photoDialogRadio();





                    }
                }
        );

        Button profile_cancel = (Button) findViewById(R.id.profile_cancel);
        profile_cancel.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), Profile.class);

                        startActivity(intent);

                        finish();
                    }
                }
        );



    }

    // 다이얼로그 메소드 //
    private void photoDialogRadio() {
        final CharSequence[] PhotoModels = {"갤러리", "카메라"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
        //alt_bld.setIcon(R.drawable.icon);
        alt_bld.setTitle("프로필사진 설정");
        alt_bld.setSingleChoiceItems(PhotoModels, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) { //갤러리
                    takePhotoFromGallery();
                } else { //카메라
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (camera.resolveActivity(getPackageManager()) != null){

                        File photoFile = null;
                        try {
                            photoFile = createImageFile();

                        }catch (IOException e){

                        }
                        if (photoFile != null){

                            photoUri = FileProvider.getUriForFile(getApplicationContext(),getPackageName(), photoFile);
                            camera.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                            startActivityForResult(camera, CAPTURE_IMAGE);

                        }
                    }
                }
            }
        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    //이미지파일의 이름을 시간단위로 생성해서 저장을 시켜주기 때문에 중복되지 않는다. //
    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storgeDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storgeDir
        );
        imageFilePath = image.getAbsolutePath();
        return image;

    }

    private void takePhotoFromGallery() {
        File photoFile = null;
        try {
            photoFile = createImageFile();

        }catch (IOException e){

        }
        if (photoFile != null){


            photoUri = FileProvider.getUriForFile(getApplicationContext(),getPackageName(), photoFile);

        }
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE);
    }

    //갤러리에서 이미지 불러온 후 행동
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ) {

            if (requestCode == PICK_IMAGE && data.getData() != null ) {
                //방법1
                try {
                    //불러온 사진 데이터를 비트맵으로 저장합니다.
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    //이미지뷰에 비트맵 세팅해줍니다
                    mSetImageView.setImageBitmap(bitmap);


                    photoUri = getImageUri(this,bitmap);


                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else if (requestCode == CAPTURE_IMAGE  ) {

                Bitmap bitmap = BitmapFactory.decodeFile(imageFilePath);
                ExifInterface exif = null;

                try {
                    exif = new ExifInterface(imageFilePath);

                }catch (IOException e){

                    e.printStackTrace();
                }

                int exifOrientation;
                int exifDegree;

                if (exif != null){

                    exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                    exifDegree = exifOrientationToDegrees(exifOrientation);
                }else {
                    exifDegree = 0;
                }

                mSetImageView.setImageBitmap(rotate(bitmap,exifDegree));


            }
        }
    }

    private  int exifOrientationToDegrees(int exifOrientation){
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap bitmap, float degree){
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("connectuser", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("connectuser","");
        Type type = new TypeToken<ArrayList<Userinfo>>() {}.getType();
        userinfo = gson.fromJson(json, type);

        if (userinfo == null){
            userinfo = new ArrayList<>();
        }

    }

    // bitmap 을 uri로 바꿔주는 메소드 //
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        imageFilePath = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(imageFilePath);
    }


}
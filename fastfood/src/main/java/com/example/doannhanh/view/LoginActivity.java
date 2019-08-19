package com.example.doannhanh.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.doannhanh.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    LoginButton btn_login;
    CallbackManager call;
    TextView name;
    ImageView img;
    SharedPreferences sharedPreferences;
    String result, out, in;
    String tenmonan, tenloai, nd, anh, giatien, giamgia2, yeuthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("data_login", MODE_PRIVATE);
        btn_login = findViewById(R.id.login_facebook);
        img = findViewById(R.id.img);
        name = findViewById(R.id.name);
        call = CallbackManager.Factory.create();
        btn_login.setReadPermissions(Arrays.asList("email", "public_profile"));
        Intent intent = getIntent();
        out = intent.getStringExtra("logout");
        in = intent.getStringExtra("login");
        if (in != "") {
            btn_login.registerCallback(call, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Intent intent = getIntent();
                    result = intent.getStringExtra("order");
                    if (result != null) {
                        loadprofile2();
                    } else {
                        loadprofile();
                    }
                }

                @Override
                public void onCancel() {
                    onStart();
                }

                @Override
                public void onError(FacebookException error) {
                    error.printStackTrace();
                }
            });
        }
//        else if(out!=""){
//            LoginManager.getInstance().logOut();
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("id","");
//            editor.putString("name","");
//            editor.putString("image","");
//            editor.commit();
//            Intent intent2 = new Intent(LoginActivity.this,MainActivity.class);
//            startActivity(intent2);
//        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        call.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
//    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//            if(currentAccessToken !=null) {
////                Intent intent = getIntent();
////                result=intent.getStringExtra("order");
////                if(result!=""){
////                    loadprofile2(currentAccessToken);
////                }else {
////                    loadprofile(currentAccessToken);
////                }
//
//            }else {
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                editor.putString("id","");
////                editor.putString("image","");
////                editor.commit();
////                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
////                startActivity(intent);
//            }
//        }
//    };

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }

    public void loadprofile() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
//                    String email = object.getString("email");
                    String id = object.getString("id");
                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
//                    Log.d("abc",first_name);
//                    name.setText(first_name);
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.dontAnimate();
//                    Glide.with(LoginActivity.this).load(image_url).into(img);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id", id);
                    editor.putString("name", first_name + " " + last_name);
                    editor.putString("image", image_url);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void loadprofile2() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String id = object.getString("id");

                    String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
//                    Log.d("abc",first_name);
//                    name.setText(first_name);
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.dontAnimate();
//                    Glide.with(LoginActivity.this).load(image_url).into(img);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id", id);
                    editor.putString("name", first_name + " " + last_name);
                    editor.putString("image", image_url);
                    editor.commit();

                    Intent intent2 = getIntent();
                    tenmonan = intent2.getStringExtra("tenmonan");
                    tenloai = intent2.getStringExtra("tenloai");
                    nd = intent2.getStringExtra("noidung");
                    anh = intent2.getStringExtra("anh");
                    giatien = intent2.getStringExtra("giatien");
                    giamgia2 = intent2.getStringExtra("giamgia");
                    yeuthich = intent2.getStringExtra("yeuthich");
                    Intent intent = new Intent(LoginActivity.this, Order.class);
                    intent.putExtra("order", "order");
                    intent.putExtra("tenmonan", tenmonan);
                    intent.putExtra("tenloai", tenloai);
                    intent.putExtra("noidung", nd);
                    intent.putExtra("anh", anh);
                    intent.putExtra("giatien", giatien);
                    intent.putExtra("giamgia", giamgia2);
                    intent.putExtra("yeuthich", yeuthich);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,id");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

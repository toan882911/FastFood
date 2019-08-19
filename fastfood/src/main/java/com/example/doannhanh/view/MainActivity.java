package com.example.doannhanh.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.doannhanh.R;
import com.example.doannhanh.util.ImageConverter;
import com.example.doannhanh.view.Dring;
import com.example.doannhanh.view.Food;
import com.example.doannhanh.view.Home;
import com.facebook.login.LoginManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab_doan,fab_douong;
    Animation move_tren,move_trai,back_doan,back_douong;
    boolean moveBack = false;
    TextView txtlogin,txtname,txtlogout,appname;
    ImageView imglogo;
    SharedPreferences sharedPreferences;
    NavigationView navigationView;
    String id,name,image;
    ImageButton search,search2;
    EditText edtsearch;
    search callback;
     String tenmon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("data_login",MODE_PRIVATE);
        id = sharedPreferences.getString("id","");
        name = sharedPreferences.getString("name","");
        image = sharedPreferences.getString("image","");
// 1168434853324831
//        Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        anhxa();

        if(id!="" && image!="" && name!=""){
            txtlogout.setVisibility(View.VISIBLE);
            txtlogin.setVisibility(View.INVISIBLE);
            txtlogout.setText(name);
            Glide.with(imglogo.getContext()).load(image).into(imglogo);
        }else {
            txtlogin.setVisibility(View.VISIBLE);
            txtlogout.setVisibility(View.INVISIBLE);

        }

        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                intent.putExtra("login","login");
                startActivity(intent);
            }
        });

//        txtlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//                intent.putExtra("logout","logout");
//                startActivity(intent);
//
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(moveBack == false){
                   Move();
                   moveBack = true;
               }else {
                   Back();
                   moveBack = false;
               }
            }
        });
        fab_doan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment(new Food());

                 Back();

            }
        });
        fab_douong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment(new Dring());
                Back();

            }

        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


        fragment(new Home());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtsearch.setVisibility(View.VISIBLE);
                appname.setVisibility(View.INVISIBLE);
                search.setVisibility(View.INVISIBLE);
                search2.setVisibility(View.VISIBLE);
            }
        });

        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenmon = edtsearch.getText().toString();
                if(callback!=null){
                    callback.Search(tenmon);
                }

//                Toast.makeText(MainActivity.this, tenmon, Toast.LENGTH_SHORT).show();
//                Search search = new Search();
//                Bundle bundle = new Bundle();
//                bundle.putString("tenmonan","bánh");
////                search.newInstance(tenmon);
//                search.setArguments(bundle);
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.contentview,search);
//                fragmentTransaction.commit();
//                FragmentBundle(new Search(),bundle);
//                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
            }
        });
//        if(isNetworkConnected(MainActivity.this)){
//            Toast.makeText(this, "Not Internet !", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            fragment(new Home());
        }else if (id == R.id.cart) {
            Intent intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
        }else if (id == R.id.cart) {
            Intent intent = new Intent(MainActivity.this,CartActivity.class);
            startActivity(intent);
        } else if (id == R.id.table) {
            Intent intent = new Intent(MainActivity.this,SetTable.class);
            startActivity(intent);
        }else if (id == R.id.address) {
            Intent intent3 = new Intent(MainActivity.this,Google_maps.class);
            startActivity(intent3);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void fragment(Fragment ft){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentview,ft);
        fragmentTransaction.commit();
    }
    private void FragmentBundle(Fragment ft,Bundle bundle){
        ft.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentview,ft);
        fragmentTransaction.commit();
    }
    private void Move(){
        fab_douong.show();
        fab_doan.show();
        FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fab_doan.getLayoutParams();
        paramsTrai.rightMargin = (int) (fab_doan.getWidth() * 1.7);
        fab_doan.setLayoutParams(paramsTrai);
        fab_doan.startAnimation(move_trai);

        FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fab_douong.getLayoutParams();
        paramsTren.bottomMargin = (int) (fab_douong.getWidth() * 1.7);
        fab_douong.setLayoutParams(paramsTren);
        fab_douong.startAnimation(move_tren);

    }
    private void Back(){
        fab_doan.hide();
        fab_douong.hide();
        FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fab_doan.getLayoutParams();
        paramsTrai.rightMargin = 0;
        fab_doan.setLayoutParams(paramsTrai);
//        FrameLayout.LayoutParams paramsTrai = (FrameLayout.LayoutParams) fab_doan.getLayoutParams();
//        paramsTrai.rightMargin -= (int) (fab_doan.getWidth() * 1.7);
//        fab_doan.setLayoutParams(paramsTrai);
//        fab_doan.startAnimation(back_doan);
        FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fab_douong.getLayoutParams();
        paramsTren.bottomMargin = 0;
        fab_douong.setLayoutParams(paramsTren);
//        FrameLayout.LayoutParams paramsTren = (FrameLayout.LayoutParams) fab_douong.getLayoutParams();
//        paramsTren.bottomMargin -= (int) (fab_douong.getWidth() * 1.7);
//        fab_douong.setLayoutParams(paramsTren);
//        fab_douong.startAnimation(back_douong);

    }
    private void anhxa(){
        fab_doan = (FloatingActionButton) findViewById(R.id.fab_doan);
        fab_douong = (FloatingActionButton) findViewById(R.id.fab_douong);
        move_trai = AnimationUtils.loadAnimation(this,R.anim.move_doan);
        move_tren = AnimationUtils.loadAnimation(this,R.anim.move_douong);
        back_doan = AnimationUtils.loadAnimation(this,R.anim.back_doan);
        back_douong = AnimationUtils.loadAnimation(this,R.anim.back_douong);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txtlogin = (TextView) headerView.findViewById(R.id.login);
        txtname = (TextView) headerView.findViewById(R.id.name);
        imglogo = (ImageView) headerView.findViewById(R.id.imageView);
        txtlogout = (TextView) headerView.findViewById(R.id.logout);
        search = findViewById(R.id.search);
        search2 = findViewById(R.id.search2);
        edtsearch = findViewById(R.id.edtsearch);
        appname = findViewById(R.id.appname);
    }
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    interface search{
        void Search(String chuoi);

    }

}

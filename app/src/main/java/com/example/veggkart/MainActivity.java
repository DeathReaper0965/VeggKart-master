package com.example.veggkart;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ButtonClickNotify, View.OnClickListener {
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private Toolbar toolbar1;
    private TabLayout tabLayout;
    private String Category;
    private TextView textView1;
    private Button frag_button;
    private ClipData.Item item;
    ListView listView;
    Animation anim_close_fab1, anim_open_fab1, anim_open_fab2, anim_close_fab2, forward_rotate, backward_rotate;
    FloatingActionButton fab, fab1, fab2;
    AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
    AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
    TextView textView2;
    Boolean isOpen = false;
    View translucentView;
    public static String[] images={
            "http://androidexample.com/media/webservice/LazyListView_images/image0.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image1.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image5.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image6.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image7.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image8.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image9.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image10.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image0.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image1.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image5.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image6.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image7.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image8.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image9.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image10.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image0.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image1.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image2.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image3.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image4.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image5.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image6.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image7.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image8.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image9.png",
            "http://androidexample.com/media/webservice/LazyListView_images/image10.png"

    };
    public static String[] proNames = {"Product 1", "Product 2", "Product 3", "Product 4",
            "Product 5", "Product 6","Product 7", "Product 8", "Product 9", "Product 10"};

    public static String[] desc;
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        frag_button = (Button) findViewById(R.id.frag_button);
//        frag_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                Contact_Us_Activity dFragment = new Contact_Us_Activity();
//                // Show DialogFragment
//                dFragment.show(fm, "Dialog Fragment");
//            }
//        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        anim_open_fab1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_open_fab1);
        anim_close_fab1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_close_fab1);
        anim_open_fab2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_open_fab2);
        anim_close_fab2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_close_fab2);
        forward_rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.forward_rotate);
        backward_rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.backward_rotate);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fadeIn.setDuration(250);
        fadeOut.setDuration(250);
        fadeIn.setFillAfter(true);
        fadeOut.setFillAfter(true);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        translucentView = findViewById(R.id.translucentView);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    //textView1 = (TextView) findViewById(R.id.textView1);
    tabLayout = (TabLayout) findViewById(R.id.tabs);

    bindWidgetWithEvent();


    tabLayout.addTab(tabLayout.newTab().setText("   Products  "), true);
    tabLayout.addTab(tabLayout.newTab().setText("    Offers   "));
    tabLayout.addTab(tabLayout.newTab().setText("      New    "));


}

    public void FABanimationStart(){
        fab.startAnimation(forward_rotate);
        fab1.startAnimation(anim_open_fab1);
        fab2.startAnimation(anim_open_fab2);
        textView1.startAnimation(fadeIn);
        textView2.startAnimation(fadeIn);
        translucentView.startAnimation(fadeIn);
        textView1.setVisibility(View.VISIBLE);
        textView2.setVisibility(View.VISIBLE);
        translucentView.setVisibility(View.VISIBLE);
        translucentView.setClickable(true);
        fab1.setClickable(true);
        fab2.setClickable(true);
        isOpen = true;
    }

    public void FABanimationStop(){
        fab.startAnimation(backward_rotate);
        fab1.startAnimation(anim_close_fab1);
        fab2.startAnimation(anim_close_fab2);
        textView1.startAnimation(fadeOut);
        textView2.startAnimation(fadeOut);
        translucentView.startAnimation(fadeOut);
        fab1.setClickable(false);
        fab2.setClickable(false);
        textView1.setVisibility(View.INVISIBLE);
        textView2.setVisibility(View.INVISIBLE);
        translucentView.setVisibility(View.INVISIBLE);
        translucentView.setClickable(false);
        isOpen = false;
    }

    private void FABanimation() {
        if(isOpen){
            FABanimationStop();
        }else{
            FABanimationStart();
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.fab:

                FABanimation();
                break;
            case R.id.fab1:

                startActivity(new Intent(MainActivity.this,cart_activity.class));
                FABanimation();
                break;
            case R.id.fab2:

                Toast.makeText(MainActivity.this, "Search Veggkart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView1:

                Toast.makeText(MainActivity.this, "Search Veggkart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textView2:

                startActivity(new Intent(MainActivity.this,cart_activity.class));
                FABanimation();
                break;
            case R.id.translucentView:

                FABanimationStop();
                break;
        }
    }

    private void bindWidgetWithEvent() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentCategory(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }


        });
    }


    private void setCurrentCategory(int tabPosition) {
        switch (tabPosition) {
            case 0:
                Category = "Product";

                break;
            case 1:
                Category = "Offer";
                break;
            case 2:
                Category = "New";
                break;
        }
        String proNames1[]=new String[8];
        for (int i = 0; i < proNames1.length; i++) {
            proNames1[i]=Category+proNames[i];
        }


        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(new CustomProductAdapter(this, images, proNames1, desc));
         AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {

            }
        };
        listView.setOnItemClickListener(mMessageClickedHandler);
       // textView1.setText(Category);

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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //@Override
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
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            FragmentManager fm= getSupportFragmentManager();

            Contact_Us_Activity dFragment = new Contact_Us_Activity();
            // Show DialogFragment
            dFragment.show(fm, "Dialog Fragment");

        }else if (id == R.id.login){
            startActivity(new Intent(MainActivity.this, LoginAct.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onButtonClick(int position) {
        FragmentManager fm= getSupportFragmentManager();

        SelectQuantity dFragment = new SelectQuantity();
        // Show DialogFragment
        dFragment.show(fm, "Select Quantity");

    }
}

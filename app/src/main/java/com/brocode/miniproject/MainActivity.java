package com.brocode.miniproject;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> fragmentTitle = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        initiateFragments();


    }

    private void addFragment(String title, Fragment fragment){
        fragmentList.add(fragment);
        fragmentTitle.add(title);
    }

    private void initiateFragments(){
        addFragment("Budget", new BudgetFragment());
        addFragment("Risk Matrix", new RiskMatrixFragment());
        addFragment("Schedule", new ScheduleFragment());


        TabLayout tabLayout = findViewById(R.id.tabs);
        for(int tabIndex = 0; tabIndex < fragmentList.size(); tabIndex++){
            tabLayout.addTab(tabLayout.newTab().setText(fragmentTitle.get(tabIndex)));
        }


        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    private void searchUser(String user){
        StringBuilder message = new StringBuilder();

        Log.d("DEBUG", "Size -> " + jsonReader.tasks_contributors.toString());

        for(Map.Entry<String,String[][]> entry : jsonReader.taskData.entrySet()) {
            String key = entry.getKey();
            String[][] value = entry.getValue();
            for (String[] taskContainer : value){
                for (String taskData : taskContainer){
                    if(taskData.toUpperCase().equals(user.toUpperCase())){
                        message.append(key.replace("_contributors", "").replaceAll("_", " ").toUpperCase());
                        message.append("\n");
                        message.append("Week 1: ").append(taskContainer[1]).append(" h").append("\n");
                        message.append("Week 2: ").append(taskContainer[2]).append(" h").append("\n");
                        message.append("Week 3: ").append(taskContainer[3]).append(" h").append("\n");
                        message.append("Week 4: ").append(taskContainer[4]).append(" h");
                        message.append("\n");
                        message.append("\n");
                    }
                }
            }


        }

        if(message.toString().equals("")){
            message.append("No results found...");
        }





        new AlertDialog.Builder(MainActivity.this)
                .setTitle(user.toUpperCase() + ":")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Whatever...
                    }
                }).show();
    }


    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.menu_main, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print

                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                    searchUser(query);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}

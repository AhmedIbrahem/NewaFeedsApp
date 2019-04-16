package ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.main;

import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.R;
import ahmed.fciibrahem.helwan.edu.eg.newafeedsapp.model.News;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String SOURCE = "the-next-web";
    public static final String API_KEY = "533af958594143758318137469b41ba9";
    private RecyclerView newsRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter adapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    MainPresenter presenter;
    SwipeRefreshLayout srMainActivity;
    RelativeLayout rlErrorLayout;
    private ImageView errorImage;
    private TextView txtErrorTitle, txtErrorBody;
    private Button btnRetery;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeViews();
        context = getApplicationContext();
        presenter = new MainPresenter(this, context);
        presenter.getNews(SOURCE, API_KEY);
        srMainActivity.setOnRefreshListener(() -> presenter.getNews(SOURCE, API_KEY));
        srMainActivity.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        newsRecyclerView.setLayoutManager(layoutManager);
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        newsRecyclerView.setNestedScrollingEnabled(false);


    }

    //intialize activity views
    @Override
    public void intializeViews() {
        rlErrorLayout = findViewById(R.id.error_layout);
        errorImage = findViewById(R.id.error_image);
        txtErrorTitle = findViewById(R.id.error_title);
        txtErrorBody = findViewById(R.id.error_message);
        btnRetery = findViewById(R.id.btn_retery);
        srMainActivity = findViewById(R.id.refresh);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setTitle("Link Development");


    }


    @Override
    public void showLodaing() {
        srMainActivity.setRefreshing(true);
        rlErrorLayout.setVisibility(View.GONE);


    }

    @Override
    public void hideLodaing() {
        srMainActivity.setRefreshing(false);
        rlErrorLayout.setVisibility(View.GONE);


    }

    //get data from api and notify recycler view adapter
    @Override
    public void onGetResult(News news) {
        adapter = new MainAdapter(news, this);
        adapter.notifyDataSetChanged();
        newsRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }

    //intializeViews Navigation drawer in toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.explore:
                showToast(getResources().getString(R.string.explore));
                break;
            case R.id.liveChat:
                showToast(getResources().getString(R.string.live_chat));
                break;
            case R.id.gallery:
                showToast(getResources().getString(R.string.gallery));
                break;
            case R.id.wishList:
                showToast(getResources().getString(R.string.wish_list));

                break;
            case R.id.eMagazine:
                showToast(getResources().getString(R.string.e_magazine));

                break;
        }

        return true;
    }

    //intialize Search view
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Searh about news");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public void showErrorMessage(int imageview, String title, String message) {
        if (rlErrorLayout.getVisibility() == View.GONE) {
            rlErrorLayout.setVisibility(View.VISIBLE);

        }
        txtErrorTitle.setText(title);
        txtErrorBody.setText(message);
        errorImage.setImageResource(imageview);
        btnRetery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getNews(SOURCE, API_KEY);
            }
        });
    }

    @Override
    public void onRefresh() {
        presenter.getNews(SOURCE, API_KEY);

    }


    @Override
    public void showToast(String actionName) {
        Toast.makeText(this, actionName, Toast.LENGTH_SHORT).show();


    }
}

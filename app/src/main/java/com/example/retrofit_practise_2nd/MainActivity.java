package com.example.retrofit_practise_2nd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.retrofit_practise_2nd.Adapter.RecyclerViewAdapter;
import com.example.retrofit_practise_2nd.Service.ItemClickListenerInt;
import com.example.retrofit_practise_2nd.Service.Model.Product;
import com.example.retrofit_practise_2nd.Service.Response.Detail;
import com.example.retrofit_practise_2nd.Service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClickListenerInt {
    private static String TAG = "GET_API";
    Thread getAllProdThread;
    Toolbar toolbar;
    List<Product> productsList;
    RecyclerView recyclerView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar.setTitle("Title");
//        toolbar.setTitleTextColor();
        toolbar.setNavigationIcon(R.drawable.baseline_self_improvement_24);
        setSupportActionBar(toolbar);
        getAllProdThread = new Thread(new GetProducts());
        getAllProdThread.start();
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//        Call<Detail> call = RetrofitClient.getInstance().getAPIService().getAllData();
//        call.enqueue(new Callback<Detail>() {
//            @Override
//            public void onResponse(Call<Detail> call, Response<Detail> response) {
//                Log.d(TAG, "getting response");
//                if (response.isSuccessful()){
//                    Detail model = response.body();
//                    Toast.makeText(MainActivity.this, "getting response :;", Toast.LENGTH_SHORT).show();
//                    Log.d(TAG, "getting response as :"+model.getProducts().get(0).getTitle());
//                    productsList = model.getProducts();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, productsList);
//                            LinearLayoutManager HorizontalLayout
//                                    = new LinearLayoutManager(
//                                    MainActivity.this,
//                                    LinearLayoutManager.HORIZONTAL,
//                                    false);
//                            recyclerView.setLayoutManager(HorizontalLayout);
//                            recyclerView.hasFixedSize();
////                            LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
////                            linearSnapHelper.attachToRecyclerView(recyclerView);
//                            PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
//                            pagerSnapHelper.attachToRecyclerView(recyclerView);
//                            recyclerView.setAdapter(adapter);
//
//                        }
//                    });
//
//                }else{
//                    Toast.makeText(MainActivity.this, "not getting response :;", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Detail> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "never getting response :;", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.option_1){
//
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(Product product) {
        Toast.makeText(this, "Item has been clicked", Toast.LENGTH_SHORT).show();
    }

    public class GetProducts  implements Runnable{

        @Override
        public void run() {
            Call<Detail> call = RetrofitClient.getInstance().getAPIService().getAllData();
            call.enqueue(new Callback<Detail>() {
                @Override
                public void onResponse(Call<Detail> call, Response<Detail> response) {
                    Log.d(TAG, "getting response");
                    if (response.isSuccessful()){
                        Detail model = response.body();
                        Toast.makeText(MainActivity.this, "getting response :;", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "getting response as :"+model.getProducts().get(0).getTitle());
                        productsList = model.getProducts();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(MainActivity.this, productsList, MainActivity.this::onItemClicked);
                                LinearLayoutManager HorizontalLayout
                                        = new LinearLayoutManager(
                                        MainActivity.this,
                                        LinearLayoutManager.HORIZONTAL,
                                        false);
                                recyclerView.setLayoutManager(HorizontalLayout);
                                recyclerView.hasFixedSize();
//                            LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
//                            linearSnapHelper.attachToRecyclerView(recyclerView);
                                PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
                                pagerSnapHelper.attachToRecyclerView(recyclerView);
                                recyclerView.setAdapter(adapter);

                            }
                        });

                    }else{
                        Toast.makeText(MainActivity.this, "not getting response :;", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Detail> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "never getting response :;", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
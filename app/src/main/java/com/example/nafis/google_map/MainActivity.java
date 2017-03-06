package com.example.nafis.google_map;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nafis.google_map.models.MovieModel;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int listposition=-1;
  //  public static List<MovieModel> searchResults=null;
    List<MovieModel> results = new ArrayList<>();
    public List<MovieModel> movieModelList = new ArrayList<>();
    public static MyCustomBaseAdapter adapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JSONTask().execute("http://m.uploadedit.com/ba3s/1488726271568.txt");

      //  searchResults = GetSearchResults();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
         super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.menu_main2, menu);
    }

    public void addbuttonclick(View v)
    {
       Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,AddInfo.class);
        startActivity(intent);
    }
    public void monitorbuttonclick(View v)
    {
        Toast.makeText(MainActivity.this,"Monitor All",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,MapsActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        listposition=info.position;
        switch(item.getItemId())
        {
            case R.id.viewinfo:
                Intent intent = new Intent(MainActivity.this,AddInfo.class);
                String storedinfo =results.get(info.position).getName()+"@"+results.get(info.position).getLongitude()+"@"+results.get(info.position).getLatitude()+"@"+"view";
                intent.putExtra("view",storedinfo);
                startActivity(intent);
                break;

            case R.id.delete:
                results.remove(info.position);
                adapter.notifyDataSetChanged();
                break;
            case R.id.monitor:
                int location = info.position;
                String lock=results.get(info.position).getLongitude()+" "+results.get(info.position).getLatitude();
                Intent in=new Intent(MainActivity.this,MapsActivity.class);
                in.putExtra("abc",lock);
                Toast.makeText(MainActivity.this,lock,Toast.LENGTH_SHORT).show();
                startActivity(in);
                break;

            case R.id.update:

                Intent intent1 = new Intent(MainActivity.this,AddInfo.class);
                String storedinfo1 =results.get(info.position).getName()+"@"+results.get(info.position).getLongitude()+"@"+results.get(info.position).getLatitude()+"@"+"update";
                intent1.putExtra("update",storedinfo1);
                startActivity(intent1);
                break;
        }

         return true;
    }

    private List<MovieModel> GetSearchResults(){





  return results;

  /*      SearchResults sr1 = new SearchResults();
        sr1.setName("John Smith");
        sr1.setCityState("Dallas, TX");
        sr1.setPhone("214-555-1234");
        results.add(sr1);

        sr1 = new SearchResults();
        sr1.setName("Jane Doe");
        sr1.setCityState("Atlanta, GA");
        sr1.setPhone("469-555-2587");
        results.add(sr1);

        sr1 = new SearchResults();
        sr1.setName("Steve Young");
        sr1.setCityState("Miami, FL");
        sr1.setPhone("305-555-7895");
        results.add(sr1);

        sr1 = new SearchResults();
        sr1.setName("Fred Jones");
        sr1.setCityState("Las Vegas, NV");
        sr1.setPhone("612-555-8214");
        results.add(sr1);
*/
    //    return results;
         }

    public class JSONTask extends AsyncTask<String,String, List<MovieModel> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // dialog.show();
           // Toast.makeText(MainActivity.this,"pre",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<MovieModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
           // Toast.makeText(MainActivity.this,"background",Toast.LENGTH_SHORT).show();
            try {
              //  Toast.makeText(MainActivity.this,"background",Toast.LENGTH_SHORT).show();
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("customers");



                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    MovieModel movieModel = gson.fromJson(finalObject.toString(), MovieModel.class); // a single line json parsing using Gson

                    movieModelList.add(movieModel);
                }

                return movieModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }  finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onPostExecute(final List<MovieModel> result) {
            super.onPostExecute(result);

        results=result;
            Bundle bundle = getIntent().getExtras();
            if(bundle!=null) {
                String info = bundle.getString("data");
                MovieModel srnew = new MovieModel();
                String[] token = info.split("@");
                srnew.setName(token[0]);
                srnew.setLongitude(token[1]);
                srnew.setLatitude(token[2]);

                if(token[token.length-1].equals("insert")) {
                    results.add(srnew);
                    //         Toast.makeText(MainActivity.this, "added", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    results.remove(listposition);
                    results.add(listposition,srnew);
                }

            }

            //    if(bundle!=null) Toast.makeText(MainActivity.this,"not added "+bundle.getString("data"),Toast.LENGTH_SHORT).show();




            final ListView lv1 = (ListView) findViewById(R.id.ListView01);
            adapter=new MyCustomBaseAdapter(MainActivity.this, results);
            lv1.setAdapter(adapter);

            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = lv1.getItemAtPosition(position);
                    MovieModel fullObject = (MovieModel)o;
                    Toast.makeText(MainActivity.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
                }});
            registerForContextMenu(lv1);
        }
    }




}

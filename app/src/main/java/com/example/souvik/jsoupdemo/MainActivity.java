package com.example.souvik.jsoupdemo;

import android.content.Context;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.souvik.jsoupdemo.models.MovieModels;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    TextView text1;
    ImageView imageView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (TextView)findViewById(R.id.name);
        listView = (ListView)findViewById(R.id.listView);


        new doIt().execute();

    }

    public class doIt extends AsyncTask<String,String,List<MovieModels>>{

        String words="",mName="";

        protected List<MovieModels> doInBackground(String... params){

            List<MovieModels> movieModelsList = new ArrayList<>();
            try {

                Document doc = Jsoup.connect("http://www.imdb.com/chart/top").get();
                Elements link = doc.select("img[src$=.jpg]");
                Elements content = doc.select("a[href^=/title/][title]");
                for(Element e:link){
                    words += e.attr("src");
                    break;
                }
                for(Element e:content){
                    mName = "";
                    mName += e.text();
                    MovieModels movieModels = new MovieModels();
                    movieModels.setName(mName);

                    movieModelsList.add(movieModels);

                }


            }catch (Exception e){
                e.printStackTrace();
            }


            return movieModelsList;
        }




        @Override
        protected void onPostExecute(List<MovieModels> result) {
            super.onPostExecute(result);

            MovieAdapter adapter = new MovieAdapter(MainActivity.this,R.layout.content,result);
            listView.setAdapter(adapter);

//
//            text1.setText(txt);
//            Picasso.with(MainActivity.this).load(words).into(imageView);
//            movieAdapter = new MovieAdapter(getApplicationContext(),R.layout.content,result);
//            listView.setAdapter(movieAdapter);

        }
    }




}



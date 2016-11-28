package com.example.souvik.jsoupdemo;

import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        double rate=0;

        protected List<MovieModels> doInBackground(String... params){

            List<MovieModels> movieModelsList = new ArrayList<>();
            String urls[] = new String[255];
            double rating[] = new double[255];
            try {

                Document doc = Jsoup.connect("http://www.imdb.com/chart/top").get();
                Elements link = doc.select("img[src$=.jpg]");
                Elements content = doc.select("a[href^=/title/][title]");
                Elements ratings = doc.select("strong[title]");
                int i=0;
                for(Element e:link){
                    words = e.attr("src");
                    urls[i] = words;
                    i++;
                }
                i=0;
                for(Element e:ratings){

                    rate=Double.parseDouble(e.text());
                    rating[i] = rate;
                    i++;
                }
                i=0;
                for(Element e:content){

                    mName = e.text();

                    MovieModels movieModels = new MovieModels();

                    movieModels.setName(mName);
                    movieModels.setImageURL(urls[i]);
                    movieModels.setRating(rating[i]);

                    movieModelsList.add(movieModels);
                    i++;

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

        }
    }




}



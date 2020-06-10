package com.example.tvremote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View.OnClickListener clickHandler = new View.OnClickListener() {
            public void onClick(View v) {
                String buttonID = v.getResources().getResourceName(v.getId()).split("/")[1];
                String urlString = "http://0.1.2.3/" + buttonID;
                new HttpGetRequest().execute(urlString);
            }
        };

        Button reboot = (Button) findViewById(R.id.reboot);
        Button power = (Button) findViewById(R.id.pwr);
        Button source = (Button) findViewById(R.id.src);
        Button menu = (Button) findViewById(R.id.menu);
        Button mute = (Button) findViewById(R.id.mute);
        Button up = (Button) findViewById(R.id.up);
        Button down = (Button) findViewById(R.id.down);
        Button left = (Button) findViewById(R.id.left);
        Button right = (Button) findViewById(R.id.right);
        Button ok = (Button) findViewById(R.id.ok);
        Button voldn = (Button) findViewById(R.id.voldn);
        Button volup = (Button) findViewById(R.id.volup);
        reboot.setOnClickListener(clickHandler);
        power.setOnClickListener(clickHandler);
        source.setOnClickListener(clickHandler);
        menu.setOnClickListener(clickHandler);
        mute.setOnClickListener(clickHandler);
        up.setOnClickListener(clickHandler);
        down.setOnClickListener(clickHandler);
        left.setOnClickListener(clickHandler);
        right.setOnClickListener(clickHandler);
        ok.setOnClickListener(clickHandler);
        voldn.setOnClickListener(clickHandler);
        volup.setOnClickListener(clickHandler);
    }

    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 500;
        public static final int CONNECTION_TIMEOUT = 500;

        @Override
        protected String doInBackground(String... params) {
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }
}
package sujan.maka.com.jsonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailActivity extends AppCompatActivity {

    private TextView textView;
    private String JSON_STRING;
    private String impName;
    private static String url = "http://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
         impName = extras.getString("name");

        textView = (TextView) findViewById(R.id.detail);


       // Toast.makeText(DetailActivity.this, impName, Toast.LENGTH_SHORT).show();

        getJSON();

    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showDetail();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(url);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void showDetail(){
        JSONObject jsonObject = null;



        try {
            /*jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY_DRIVER);*/

            JSONArray json = new JSONArray(JSON_STRING);

            for(int i = 0; i<json.length(); i++){
                JSONObject jo = json.getJSONObject(i);


                String id = jo.getString("id");
                String name = jo.getString("name");
                String username = jo.getString("username");
                String  email = jo.getString("email");

                JSONObject address = jo.getJSONObject("address");
                String street = address.getString("street");
                String suite = address.getString("suite");
                String city = address.getString("city");
                String  zipcode = address.getString("zipcode");

                JSONObject geo = address.getJSONObject("geo");
                String lat = geo.getString("lat");
                String lng = geo.getString("lng");

                String phone = jo.getString("phone");
                String website = jo.getString("website");

                JSONObject company = jo.getJSONObject("company");
                String compName = company.getString("name");
                String catchPhrase = company.getString("catchPhrase");
                String bs = company.getString("bs");

                if(impName.equals("{name="+name+"}")){
                  //  Toast.makeText(this, "the name is " + id + name + email , Toast.LENGTH_SHORT).show();
                    textView.setText(" id : " + id + " \n " +
                            " name : " + name + " \n " +
                            " username : " + username + " \n " +
                            " email : " + email + " \n " +
                            " street : " + street + " \n " +
                            " suite : " + suite + " \n " +
                            " city : " + city + " \n " +
                            " zipcode : " + zipcode + " \n " +
                            " lat : " + lat + " \n " +
                            " lng : " + lng + " \n " +
                            " phone : " + phone + " \n " +
                            " website : " + website + " \n " +
                            " compName : " + compName + " \n " +
                            " catchPhrase : " + catchPhrase + " \n " +
                            " bs : " + bs );
                }


                //MIDD = Double.parseDouble(phone);




            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



}

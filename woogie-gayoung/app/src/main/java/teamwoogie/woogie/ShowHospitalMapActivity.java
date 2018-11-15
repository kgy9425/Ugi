//ShowHospitalMapActivity (non-searcing)//



package teamwoogie.woogie;


import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ShowHospitalMapActivity extends AppCompatActivity
        implements OnMapReadyCallback {


    public static final int LOAD_SUCCESS = 101;
    private static final String TAG = "googlemap_example";
    //  private List<HashMap<String,String>> Hospital_infoList = null;
    // JSONArray jsonArray ;



    //  String hospital_name = null;
    Double current_position_x = null;
    Double current_position_y = null;
    String title = null;
    LatLng position = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hospital_map);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        //  hospital_name = intent.getStringExtra("name");
        current_position_x = intent.getDoubleExtra("current_position_x", 37.56);
        current_position_y = intent.getDoubleExtra("current_position_y", 126.97);


        //Hospital_infoList = new ArrayList<HashMap<String,String>>();
        //getJSON();

    }

/*
    private String REQUEST_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
            + current_position_x +",%" + current_position_y+
            "&radius=1500&keyword= " + hospital_name + "&key=AIzaSyAO3PnnhQGoMHOaTTwtcNlb8bilkPEILR4";






    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    private final MyHandler mHandler = new MyHandler(this);
    class MyHandler extends Handler {
        private final WeakReference<ShowHospitalMapActivity> weakReference;
        public MyHandler(ShowHospitalMapActivity showHospitalMapActivity ){
            weakReference = new WeakReference<ShowHospitalMapActivity>(showHospitalMapActivity);
        }
        @Override
        public void handleMessage(Message msg) {
            ShowHospitalMapActivity showHospitalMapActivity= weakReference.get();
            if (showHospitalMapActivity != null) {
                switch (msg.what) {
                    case LOAD_SUCCESS:
                        // jsonViewActivity.progressDialog.dismiss();
                        String jsonString = (String)msg.obj;
                        // jsonViewActivity.JSONText.setText(jsonString);
                        break;
                }
            }
        }
    }
    /////////////////////////////////////검색 REQUEST /////////////////////////////////////////////////////////
    public void  getJSON() {

        Thread thread = new Thread(new Runnable() {

            //private static final String TAG = ;

            public void run() {

                String result;

                try {

                    Log.d(TAG, REQUEST_URL);
                    URL url = new URL(REQUEST_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                    httpURLConnection.setReadTimeout(3000);
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.connect();

                    int responseStatusCode = httpURLConnection.getResponseCode();


                    InputStream inputStream;
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();

                    }

                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();

                    result = sb.toString().trim();

                } catch (Exception e) {
                    result = e.toString();
                }

                if (jsonParser(result)) {
                    Message message = mHandler.obtainMessage(LOAD_SUCCESS, result);
                    mHandler.sendMessage(message);
                }
            }
        });
        thread.start();
    }


    /////////// json parsing ///////////////////////////////////////////////////////
    public boolean jsonParser(String jsonString){


        if (jsonString == null ) return false;

        //jsonString = jsonString.replace("jsonFlickrApi(", "");
        //jsonString = jsonString.replace(")", "");

        try {


            JSONObject jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("results");

            Hospital_infoList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {

                // 결과별로 결과 object 얻기
                JSONObject  result = jsonArray.getJSONObject(i);

                // 위도, 경도 얻기
                JSONObject  geo = result.getJSONObject("geometry");
                JSONObject  location = geo.getJSONObject("location");
                String H_lat = location.getString("lat");
                String H_lng = location.getString("lng");

                // 이름 얻기
                String H_name = result.getString("name");



                HashMap<String, String> hospitalinfoMap = new HashMap<String, String>();
                hospitalinfoMap.put("name", H_name);
                hospitalinfoMap.put("lat", H_lat);
                hospitalinfoMap.put("lng", H_lng);

                Hospital_infoList.add(hospitalinfoMap);
            }


            return true;
        } catch (JSONException e) {

            Log.d(TAG, e.toString() );
        }
        return false;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

*/


























    @Override
    public void onMapReady(GoogleMap map) {
        //
        LatLng home_position = new LatLng(current_position_x, current_position_y);
        //  CameraPosition cp = new CameraPosition.Builder().target(home_position).zoom(50).build();

        //map.animateCamera(CameraUpdateFactory.zoomTo(15));


        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(home_position);
        markerOptions.title("현재위치");
        //markerOptions.snippet("[현재위치]");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(home_position, 15));
        //map.animateCamera(CameraUpdateFactory.zoomTo(15));
        // map.getUiSettings().setMyLocationButtonEnabled(true);
        // map.animateCamera(CameraUpdateFactory.zoomTo(15));



        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.hospital_marker);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 200, 200, false);
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));


        markerOptions
                .position(new LatLng(37.507012, 126.948367))
                .title("본치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5045356, 126.9494402))
                .title("연세자연치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5045356, 126.9528906))
                .title("자작나무치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.49648699999999, 126.953057))
                .title("연세하늘치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5080694, 126.9613895))
                .title("중앙치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.4994881, 126.9547449))
                .title("서울본치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.4975898, 126.9531092))
                .title("윌튼치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.50529179999999, 126.9482797))
                .title("이화치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5050418, 126.9462244))
                .title("상도세브란스치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5121799, 126.952168))
                .title("미소를주는치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5124021, 126.9512792))
                .title("현대치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5072918, 126.9622783))
                .title("미래열린치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.4977736, 126.9528356))
                .title("연세미소그린치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5030976, 126.9511406))
                .title("연세프라임치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5068072, 126.9681646))
                .title("김준수치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5065974, 126.9621394))
                .title("보스톤치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5009312, 126.949863))
                .title("연세어린이사랑치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5037642, 126.9476409))
                .title("웰플란트치과의원");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5034865, 126.9513906))
                .title("E-라임치과");
        map.addMarker(markerOptions);
        markerOptions
                .position(new LatLng(37.5069585, 126.9615284))
                .title("서진치과의원 Seojin Dental Clinic");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(home_position));
        map.animateCamera(CameraUpdateFactory.zoomTo(15));





        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {

                title = marker.getTitle();
                position = marker.getPosition();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.kr/search?hl=ko&source=hp&ei=F2DkW72ZBZb3hwPK8rbgBw&q="+ title ));
                startActivity(intent);

            }
        });
    }

}
package software.reconocerpaises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import software.reconocerpaises.WebService.Asynchtask;
import software.reconocerpaises.WebService.WebService;

public class DetallesPais extends AppCompatActivity implements Asynchtask {

    TextView StatusMsg;
    TextView Name;
    TextView Capital;
    TextView GeoRectangle;
    TextView TelPref;
    TextView CountryInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pais);

        StatusMsg = findViewById(R.id.StatusMsg);
        Name = findViewById(R.id.Name);
        Capital = findViewById(R.id.Capital);
        GeoRectangle = findViewById(R.id.GeoRectangle);
        TelPref = findViewById(R.id.TelPref);
        CountryInfo = findViewById(R.id.CountryInfo);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/EC.json",
                datos, DetallesPais.this, DetallesPais.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        try {
            //System.out.println(result);
            JSONObject jsonObject = new JSONObject(result);

            StatusMsg.setText( "StatusMsg: " + jsonObject.getString("StatusMsg") );

            JSONObject Results = jsonObject.getJSONObject("Results");
            Name.setText( "Name: " + Results.getString("Name") );

            JSONObject objeto = Results.getJSONObject("Capital");
            Capital.setText( "Capital: " + objeto.getString("Name") );

            objeto = Results.getJSONObject("GeoRectangle");
            GeoRectangle.setText( "GeoRectangle:" +
                    "\n    West: " + objeto.getString("West") +
                    "\n    East: " + objeto.getString("East") +
                    "\n    North: " + objeto.getString("North") +
                    "\n    South: " + objeto.getString("South"));

            TelPref.setText( "TelPref" + Results.getString("TelPref") );

            CountryInfo.setText( "CountryInfo" + Results.getString("CountryInfo") );
        }
        catch (JSONException e){
            System.out.println(e.getMessage());
        }
    }
}
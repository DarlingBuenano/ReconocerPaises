package software.reconocerpaises;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import software.reconocerpaises.WebService.Asynchtask;
import software.reconocerpaises.WebService.WebService;

public class DetallesPais extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pais);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("http://www.geognos.com/api/en/countries/info/EC.json",
                datos, DetallesPais.this, DetallesPais.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        try {
            JSONObject jsonObject = new JSONObject(result);
        }
        catch (JSONException e){
            System.out.println(e.getMessage());
        }
    }
}
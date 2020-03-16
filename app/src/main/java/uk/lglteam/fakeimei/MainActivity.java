package uk.lglteam.fakeimei;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        final Button bIM = (Button)findViewById(R.id.bBtn);
        final TextView tIM = (TextView)findViewById(R.id.tTv);
        String path = Environment.getExternalStorageDirectory().getPath();
        final File file = new File(path + "/ffid.txt");
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append("\n");
                }
                bufferedReader.close();
                tIM.setText(sb.toString().trim());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tIM.setText("File \"ffid.txt\" has not been created. Click FAKE IMEI to unban your device!");
        }
        bIM.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity.this.CheckPermission()) {
                    double random = Math.random();
                    double d = (double) 899999999999999L;
                    Double.isNaN(d);
                    String str = BuildConfig.FLAVOR + (((long) (random * d)) + 100000000000000L);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        outputStreamWriter.append(str);
                        outputStreamWriter.close();
                        fileOutputStream.close();
                        tIM.setText(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
                }
            }
        });
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
    }

    /* access modifiers changed from: private */
    public boolean CheckPermission() {
        return ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}

package pranavmahajan21.com.viewpagermaterial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class ZeroActivity extends AppCompatActivity {

    Intent nextIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);
    }

    public void onDoubt1(View view) {
        nextIntent = new Intent(ZeroActivity.this, MainActivity.class);
        startActivity(nextIntent);
    }

    public void onDoubt2(View view) {
        nextIntent = new Intent(ZeroActivity.this, ImageScaleActivity.class);
        startActivity(nextIntent);
    }

    public void onDoubt3(View view) {
        nextIntent = new Intent(ZeroActivity.this, LLWeightActivity.class);
        startActivity(nextIntent);
    }

    public void onDoubt4(View view) {
        nextIntent = new Intent(ZeroActivity.this, TabActivity.class);
        startActivity(nextIntent);
    }
}

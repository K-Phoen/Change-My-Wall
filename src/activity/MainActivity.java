package activity;

import android.app.Activity;
import android.os.Bundle;

import com.cmw.R;

public class MainActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);    

       setContentView(R.layout.main_view);
    }	
}

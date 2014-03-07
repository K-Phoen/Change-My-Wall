package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.cmw.R;

public class MainActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);    
       setContentView(R.layout.main_view);
       initButtonsListeners();
    }
	
	protected void initButtonsListeners () {
		ImageButton settingsBtn = (ImageButton) findViewById(R.id.settingsBtn);
		final Activity self = this;
		
		settingsBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent (self, ProvidersActivity.class);
				startActivity(intent);
			}
		});
		
	}
}
package activity.prodiver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;

import com.cmw.R;

public class GalleryProviderActivity extends Activity{
	private CheckedTextView checkbox;
	private Button buttonMultipleChoice;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galery_provider_view);
		checkbox = (CheckedTextView) findViewById(R.id.my_checkedtextview);
		buttonMultipleChoice = (Button) findViewById(R.id.buttonMultipleChoice);
		checkbox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (checkbox.isChecked()) {
					checkbox.setChecked(false);
					buttonMultipleChoice.setVisibility(View.VISIBLE);
				}
				else {
					checkbox.setChecked(true);
					buttonMultipleChoice.setVisibility(View.INVISIBLE);
				}
				
			}
		});
	}	
}

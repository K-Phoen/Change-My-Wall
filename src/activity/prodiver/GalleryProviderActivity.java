package activity.prodiver;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;

import com.cmw.R;
import com.luminous.pick.Action;
import com.luminous.pick.CustomGallery;

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
		
		buttonMultipleChoice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Action.ACTION_MULTIPLE_PICK);
				startActivityForResult(i, 200);
				
			}
		});
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String single_path = data.getStringExtra("single_path");
        	System.out.println("file://" + single_path);
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");

            ArrayList<CustomGallery> dataT = new ArrayList<CustomGallery>();

            for (String string : all_path) {
                CustomGallery item = new CustomGallery();
                item.sdcardPath = string;

                dataT.add(item);
                System.out.println(string);
            }

        }
    }

}

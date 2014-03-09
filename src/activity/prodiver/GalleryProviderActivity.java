package activity.prodiver;

import java.util.Arrays;

import settings.provider.GallerySettingsRepository;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.cmw.R;
import com.luminous.pick.Action;

import database.DatabaseHandler;

public class GalleryProviderActivity extends Activity{
	private CheckedTextView checkbox;
	private Button buttonMultipleChoice;
	private GallerySettingsRepository repo;
	private TextView nbrImages;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		repo = new GallerySettingsRepository(new DatabaseHandler(this));
		setContentView(R.layout.galery_provider_view);
		checkbox = (CheckedTextView) findViewById(R.id.my_checkedtextview);
		checkbox.setChecked(repo.useFullGallery());
		buttonMultipleChoice = (Button) findViewById(R.id.buttonMultipleChoice);
		nbrImages = (TextView) findViewById(R.id.nbrImages);
		updateViewButton();
		updateViewText();
		checkbox.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				checkbox.setChecked(!checkbox.isChecked());	
				repo.useFullGallery(checkbox.isChecked());
				updateViewButton();
				updateViewText();
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
	
	protected void updateViewButton() {
		if (repo.useFullGallery())
			buttonMultipleChoice.setVisibility(View.INVISIBLE);
		else
			buttonMultipleChoice.setVisibility(View.VISIBLE);
		
	}
	
	protected void updateViewText() {
		if (repo.useFullGallery())
			nbrImages.setVisibility(View.INVISIBLE);
		else
			nbrImages.setVisibility(View.VISIBLE);
		nbrImages.setText("Vous avez sélectionné "+ repo.selectionImages().size()+" photos");
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            String single_path = data.getStringExtra("single_path");
        	System.out.println("file://" + single_path);
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            String[] all_path = data.getStringArrayExtra("all_path");            
            repo.selectionImages(Arrays.asList(all_path));
            repo.selectionImages();
            updateViewText();
        }
    }

}

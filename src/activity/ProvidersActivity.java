package activity;

import settings.SettingsRepository;
import view.ProviderButton;
import wallpaper.provider.Provider;
import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.cmw.R;

import database.DatabaseHandler;

public class ProvidersActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		setContentView(R.layout.provider_view);
		DatabaseHandler handler = new DatabaseHandler (this);
		final SettingsRepository settings = new SettingsRepository(handler.getConnection());
		
		LinearLayout providerContainer = (LinearLayout) findViewById(R.id.providerContainer);
		WallpaperRepository repository = WallpaperRepository.create();
		if (settings.getCurrentProviderName() != null)
			System.out.println(settings.getCurrentProviderName());
		
		for (Provider provider : repository.getProviders().values()) {
    	   LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           ProviderButton button = (ProviderButton) inflater.inflate(R.layout.provider_button_item, null);
           
    	   button.setProviderName(provider.getName());
    	   button.setProviderImage(this.getResources().getDrawable(provider.getIcon()));
    	   button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ProviderButton button = (ProviderButton)v;
				settings.setCurrentProviderName(button.getProviderName ());
				System.out.println(settings.getCurrentProviderName());
			}
		});
    	   providerContainer.addView(button);
       }
	}
}
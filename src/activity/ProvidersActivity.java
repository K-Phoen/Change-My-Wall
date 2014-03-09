package activity;

import settings.SettingsRepository;
import view.ProviderButton;
import wallpaper.provider.Provider;
import wallpaper.repository.WallpaperRepository;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
		
		final Activity activity = this;

		setContentView(R.layout.provider_view);

		final SettingsRepository settings = new SettingsRepository(new DatabaseHandler(this));
		String currentProviderName = settings.getCurrentProviderName();

		final LinearLayout providerContainer = (LinearLayout) findViewById(R.id.providerContainer);
		final WallpaperRepository repository = WallpaperRepository.create(this);

		for (Provider provider : repository.getProviders().values()) {
			LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ProviderButton button = (ProviderButton) inflater.inflate(R.layout.provider_button_item, null);

			button.setProviderName(provider.getName());
			button.setProviderImage(this.getResources().getDrawable(provider.getIcon()));

			if (provider.getName().equals(currentProviderName)) {
				button.showConfigurationButton();
			}

			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ProviderButton button = (ProviderButton) v;
					ProviderButton otherButton;
					final Provider p = repository.getProvider(button.getProviderName());

					for (int i = 0; i < providerContainer.getChildCount(); i += 1) {
						otherButton = (ProviderButton) providerContainer.getChildAt(i);

						if (otherButton != button) {
							otherButton.hideConfigurationButton();
							otherButton.setOnClickListener(null);
						}
					}

					settings.setCurrentProviderName(button.getProviderName());
					
					if (p.isConfigurable()) {
						button.showConfigurationButton();
						button.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								Intent intent = new Intent(activity, p.getConfigurationActivity());
								startActivity(intent);
							}
						});
					}
				}
			});

			providerContainer.addView(button);
		}
	}
}
package wallpaper.repository;

import java.util.HashMap;
import java.util.Map;

import settings.provider.GallerySettingsRepository;
import wallpaper.entity.Wallpaper;
import wallpaper.provider.AndroidWallpapersProvider;
import wallpaper.provider.DummyProvider;
import wallpaper.provider.GalleryProvider;
import wallpaper.provider.Provider;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import database.DatabaseHandler;

public class WallpaperRepository {
	Map<String, Provider> providers = new HashMap<String, Provider>();
	Provider selectedProvider;
	
	public static WallpaperRepository create(Context context) {
		WallpaperRepository repository = new WallpaperRepository();
		GallerySettingsRepository repo = new GallerySettingsRepository(new DatabaseHandler(context));
		
		
		repository.addProvider(new GalleryProvider(repo));
		repository.addProvider(new AndroidWallpapersProvider());
		repository.addProvider(new DummyProvider("dummy1"));
		return repository;
	}

	public void addProvider(Provider provider) {
		providers.put(provider.getName(), provider);
	}

	public void selectProvider(String name) {
		selectedProvider = getProvider(name);
	}

	public void changeWallpaper(Activity activity, ResultCallback callback) {
		if (selectedProvider == null) {
			if (providers.isEmpty()) {
				throw new RuntimeException("No provider registered");
			}

			selectedProvider = providers.values().iterator().next();
		}

		// get a new wallpaper
		final Activity a = activity;
		final ResultCallback cb = callback;
		selectedProvider.getWallpaper(activity, new ResultCallback() {
			@Override
			public void handleResult(Wallpaper wallpaper) {
				if(wallpaper == null) {
					throw new RuntimeException("No wallpaper returned by the provider " + selectedProvider.getName());
				}

				// and define it as current wallpaper
				WallpaperManager manager = WallpaperManager.getInstance(a);
				wallpaper.promoteAsWallpaper(manager);

				cb.handleResult(wallpaper);
			}
		});
	}
	
	public Map<String, Provider> getProviders () {
		return providers;
	}
	
	public Provider getProvider (String name) {
		Provider provider = providers.get(name);

		if (provider == null) {
			throw new RuntimeException("Provider " + name + " not found");
		}

		return provider;
	}
}

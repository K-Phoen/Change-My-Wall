package wallpaper.repository;

import java.util.HashMap;
import java.util.Map;

import wallpaper.entity.Wallpaper;
import wallpaper.provider.DummyProvider;
import wallpaper.provider.GalleryProvider;
import wallpaper.provider.Provider;
import android.app.Activity;
import android.app.WallpaperManager;

public class WallpaperRepository {
	Map<String, Provider> providers = new HashMap<String, Provider>();
	Provider selectedProvider;
	
	public static WallpaperRepository create() {
		WallpaperRepository repository = new WallpaperRepository();

		repository.addProvider(new GalleryProvider());

		repository.addProvider(new DummyProvider("dummy"));
		repository.addProvider(new DummyProvider("dummy2"));
		repository.addProvider(new DummyProvider("dummy3"));
		repository.addProvider(new DummyProvider("dummy4"));
	
		return repository;
	}

	public void addProvider(Provider provider) {
		providers.put(provider.getName(), provider);
	}

	public void selectProvider(String name) {
		selectedProvider = getProvider(name);
	}

	public Wallpaper changeWallpaper(Activity activity) {
		if (selectedProvider == null) {
			if (providers.isEmpty()) {
				throw new RuntimeException("No provider registered");
			}

			selectedProvider = providers.values().iterator().next();
		}

		// get a new wallpaper
		Wallpaper wallpaper = selectedProvider.getWallpaper(activity);
		if(wallpaper == null) {
			throw new RuntimeException("No wallpaper returned by the provider " + selectedProvider.getName());
		}

		// and define it as current wallpaper
		WallpaperManager manager = WallpaperManager.getInstance(activity);
		wallpaper.promoteAsWallpaper(manager);

		return wallpaper;
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

package wallpaper.repository;

import java.util.HashMap;
import java.util.Map;

import wallpaper.entity.Wallpaper;
import wallpaper.provider.DummyProvider;
import wallpaper.provider.Provider;
import android.app.WallpaperManager;
import android.content.Context;

public class WallpaperRepository {
	Map<String, Provider> providers = new HashMap<String, Provider>();
	Provider selectedProvider;
	
	public static WallpaperRepository create() {
		WallpaperRepository repository = new WallpaperRepository();

		repository.addProvider(new DummyProvider());

		return repository;
	}

	public void addProvider(Provider provider) {
		providers.put(provider.getName(), provider);
	}

	public void selectProvider(String name) {
		Provider provider = providers.get(name);

		if (provider == null) {
			throw new RuntimeException("Provider " + name + " not found");
		}

		selectedProvider = provider;
	}

	public Wallpaper changeWallpaper(Context context) {
		if (selectedProvider == null) {
			throw new RuntimeException("No provider selected");
		}

		// get a new wallpaper
		Wallpaper wallpaper = selectedProvider.getWallpaper(context);
		if(wallpaper == null) {
			throw new RuntimeException("No wallpaper returned by the provider " + selectedProvider.getName());
		}

		// and define it as current wallpaper
		WallpaperManager manager = WallpaperManager.getInstance(context);
		wallpaper.promoteAsWallpaper(manager);

		return wallpaper;
	}
}

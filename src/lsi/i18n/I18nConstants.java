package lsi.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18nConstants {
	private static final String BUNDLE_NAME = "lsi.i18n.i18n_constants_pt_BR";

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private I18nConstants() {
	}

	public static String get(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

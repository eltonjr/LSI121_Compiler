package lsi.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import lsi.Env;

public class I18nConstants {

	private I18nConstants() {
	}

	public static String get(String key) {
		try {
			return ResourceBundle.getBundle(Env.getLanguage()).getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}

package utils;

import java.awt.Color;
import java.lang.reflect.Field;

public class ViewUtils {

	private static ViewUtils instance;

	private ViewUtils() {
	}

	public static synchronized ViewUtils getInstance() {
		if (instance == null) {
			instance = new ViewUtils();
		}
		return instance;
	}	
	
	/**
	 * Converts a given string into a color.
	 * 
	 * @param value
	 *            the team name corresponding to a color.
	 * @param dft
	 *            is sent as a fallback (default) if the parsing fails.
	 * @return the color.
	 */
	public Color stringToColor(final String value, Color dft) {
		// null value is handled by returning default;
		if (value == null) {
			return dft;
		}
		try {
			// try to get a color by name using reflection
			final Field f = Color.class.getField(value);
			return (Color) f.get(null);
		} catch (Exception ce) {
			// if we can't get any color return default
			return dft;
		}
	}
}

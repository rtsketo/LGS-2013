package com.arti.lol.lgs;

import java.lang.reflect.Field;

public class sTrans {
	public static int raw(String name, String type, int num) {
		try {
			Field field = R.raw.class.getField(name + "_" + type + num);
			return field.getInt(null);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int raw(String name) {
		try {
			Field field = R.raw.class.getField(name);
			return field.getInt(null);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int drawable(String name, String square) {
		if (square == null) {
			try {
				Field field = R.drawable.class.getField(name);
				return field.getInt(null);
			} catch (Exception e) {
				return R.drawable.ic_launcher;
			}
		} else {
			try {
				Field field = R.drawable.class.getField(name + "_square");
				return field.getInt(null);
			} catch (Exception e) {
				return R.drawable.ic_launcher;
			}
		}

	}

}

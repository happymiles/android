package com.example.mygal;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import android.graphics.Bitmap;

public class MemoryCache {
	Map<String, Bitmap> cache = Collections
			.synchronizedMap(new LinkedHashMap<String, Bitmap>(20, 3.5f, true));
	Map<String, Bitmap> image = Collections
			.synchronizedMap(new LinkedHashMap<String, Bitmap>(20, 3.5f, true));
	private long size = 0;
	private long limit = 1000000;

	public MemoryCache() {
		setLimit(Runtime.getRuntime().maxMemory() * 2);
	}

	public void setLimit(long new_limit) {
		limit = new_limit;
	}

	public Bitmap get(String id) {
		try {
			if (!cache.containsKey(id))
				return null;
			return cache.get(id);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void put(String id, Bitmap bitmap) {
		try {
			if (cache.containsKey(id))
				size -= getSizeInBytes(cache.get(id));
			cache.put(id, bitmap);
			size += getSizeInBytes(bitmap);
			checkSize();
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
	
	public void putImage(String id, Bitmap bitmap)
	{
		try {
			if (image.containsKey(id))
				size -= getSizeInBytes(image.get(id));
			image.put(id, bitmap);
			size += getSizeInBytes(bitmap);
			checkImageSize();
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}
	public Bitmap getImage(String id) {
		try {
			if (!image.containsKey(id))
				return null;
			return image.get(id);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	private void checkSize() {
		if (size > limit) {
			Iterator<Entry<String, Bitmap>> iter = cache.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Bitmap> entry = iter.next();
				size -= getSizeInBytes(entry.getValue());
				iter.remove();
				if (size <= limit)
					break;
			}
		}
	}
	
	private void checkImageSize() {
		if (size > limit) {
			Iterator<Entry<String, Bitmap>> iter = image.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Bitmap> entry = iter.next();
				size -= getSizeInBytes(entry.getValue());
				iter.remove();
				if (size <= limit)
					break;
			}
		}
	}
	public void clear() {
		try {
			image.clear();
			cache.clear();
			size = 0;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	long getSizeInBytes(Bitmap bitmap) {
		if (bitmap == null)
			return 0;
		return bitmap.getRowBytes() * bitmap.getHeight();
	}
}
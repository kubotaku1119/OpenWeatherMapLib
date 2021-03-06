/**
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2014 kubotaku1119
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of
 *  this software and associated documentation files (the "Software"), to deal in
 *  the Software without restriction, including without limitation the rights to
 *  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 *  the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.kubotaku.android.openweathermap.lib;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kubotaku1119 on 2014/08/11.
 */
public class WeatherIconGetter {

    private static final String BASE_URL = "http://openweathermap.org/img/w/%1$s.png";

    public static Bitmap getWeatherIcon(final String id, final int iconSize) {
        Bitmap icon = null;
        if (id != null) {
            try {
                String requestUrl = String.format(BASE_URL, id);
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.connect();
                BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
                Bitmap iconTmp = BitmapFactory.decodeStream(in);
                if (iconSize != 0) {
                    icon = Bitmap.createScaledBitmap(iconTmp, iconSize, iconSize, true);
                    iconTmp.recycle();
                } else {
                    icon = iconTmp;
                }
                in.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return icon;
    }

}

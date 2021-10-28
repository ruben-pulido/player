/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.player.web.internal.model;

/**
 * @author Rubén Pulido
 */
public class Olympics {

	public Olympics(int year, String city) {
		_year = year;
		_city = city;
	}

	public String getCity() {
		return _city;
	}

	public int getYear() {
		return _year;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(6);

		sb.append(_city);
		sb.append(" ");
		sb.append(_year);

		return sb.toString();
	}

	private String _city;
	private int _year;

}
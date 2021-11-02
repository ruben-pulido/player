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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ClassedModel;

import java.io.Serializable;

/**
 * @author Rubén Pulido
 */
public class Olympics implements ClassedModel {

	public Olympics(int year, String city, String image) {
		_year = year;
		_city = city;
		_image = image;
	}

	public String getCity() {
		return _city;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	public String getImage() {
		return _image;
	}

	@Override
	public Class<?> getModelClass() {
		return Olympics.class;
	}

	@Override
	public String getModelClassName() {
		return Olympics.class.getName();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _year;
	}

	public int getYear() {
		return _year;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
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
	private String _image;
	private int _year;

}
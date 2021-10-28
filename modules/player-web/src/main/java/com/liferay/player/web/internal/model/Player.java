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
public class Player implements ClassedModel {

	public Player(
		long id, String name, String image, String country,
		Olympics[] olympics) {

		_id = id;
		_name = name;
		_image = image;
		_country = country;
		_olympics = olympics;
	}

	public String getCountry() {
		return _country;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	public long getId() {
		return _id;
	}

	public String getImage() {
		return _image;
	}

	@Override
	public Class<?> getModelClass() {
		return Player.class;
	}

	@Override
	public String getModelClassName() {
		return Player.class.getName();
	}

	public String getName() {
		return _name;
	}

	public Olympics[] getOlympics() {
		return _olympics;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	private String _country;
	private long _id;
	private String _image;
	private String _name;
	private Olympics[] _olympics;

}
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

package com.liferay.player.web.internal.info.item.fields;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.ImageInfoFieldType;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.player.web.internal.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rubén Pulido
 */
public class PlayerInfoFields {

	public static final InfoField<SelectInfoFieldType> countryInfoField =
		InfoField.builder(
		).infoFieldType(
			SelectInfoFieldType.INSTANCE
		).name(
			"country"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "country")
		).attribute(
			SelectInfoFieldType.OPTIONS, _getCountryOptions()
		).build();
	public static final InfoField<ImageInfoFieldType> imageInfoField =
		InfoField.builder(
		).infoFieldType(
			ImageInfoFieldType.INSTANCE
		).name(
			"image"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "image")
		).build();
	public static final InfoField<TextInfoFieldType> nameInfoField =
		InfoField.builder(
		).infoFieldType(
			TextInfoFieldType.INSTANCE
		).name(
			"name"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "name")
		).build();

	private static List<SelectInfoFieldType.Option> _getCountryOptions() {
		List<SelectInfoFieldType.Option> options = new ArrayList();

		options.add(new SelectInfoFieldType.Option("Argentina", "Argentina"));
		options.add(new SelectInfoFieldType.Option("China", "China"));
		options.add(new SelectInfoFieldType.Option("Spain", "Spain"));
		options.add(new SelectInfoFieldType.Option("USA", "USA"));

		return options;
	}

}
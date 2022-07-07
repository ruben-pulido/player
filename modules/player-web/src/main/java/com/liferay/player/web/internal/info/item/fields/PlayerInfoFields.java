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
import com.liferay.info.field.type.NumberInfoFieldType;
import com.liferay.info.field.type.SelectInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.localized.*;
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
		).uniqueId(
			"country"
		).name(
			"country"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "country")
		).attribute(
			SelectInfoFieldType.OPTIONS, _getCountryOptions()
		).attribute(
			SelectInfoFieldType.MULTIPLE, true
		).build();
	public static final InfoField<ImageInfoFieldType> imageInfoField =
		InfoField.builder(
		).infoFieldType(
			ImageInfoFieldType.INSTANCE
		).uniqueId(
			"image"
		).name(
			"image"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "image")
		).build();
	public static final InfoField<TextInfoFieldType> nameInfoField =
		InfoField.builder(
		).infoFieldType(
			TextInfoFieldType.INSTANCE
		).uniqueId(
			"name"
		).name(
			"name"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "name")
		).build();
	public static final InfoField<NumberInfoFieldType>
		numberOfBronzeMedalsInfoField = InfoField.builder(
		).infoFieldType(
			NumberInfoFieldType.INSTANCE
		).uniqueId(
			"numberOfBronzeMedalsInfoField"
		).name(
			"numberOfBronzeMedalsInfoField"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "number-of-bronze-medals")
		).build();
	public static final InfoField<NumberInfoFieldType>
		numberOfGoldMedalsInfoField = InfoField.builder(
		).infoFieldType(
			NumberInfoFieldType.INSTANCE
		).uniqueId(
			"numberOfGoldMedalsInfoField"
		).name(
			"numberOfGoldMedalsInfoField"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "number-of-gold-medals")
		).build();
	public static final InfoField<NumberInfoFieldType> numberOfMedalsInfoField =
		InfoField.builder(
		).infoFieldType(
			NumberInfoFieldType.INSTANCE
		).uniqueId(
			"numberOfMedalsInfoField"
		).name(
			"numberOfMedalsInfoField"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "number-of-medals")
		).build();
	public static final InfoField<NumberInfoFieldType>
		numberOfSilverMedalsInfoField = InfoField.builder(
		).infoFieldType(
			NumberInfoFieldType.INSTANCE
		).uniqueId(
			"numberOfSilverMedalsInfoField"
		).name(
			"numberOfSilverMedalsInfoField"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "number-of-silver-medals")
		).build();

	private static List<SelectInfoFieldType.Option> _getCountryOptions() {
		List<SelectInfoFieldType.Option> options = new ArrayList();

		options.add(
			new SelectInfoFieldType.Option(
				new SingleValueInfoLocalizedValue<>("Argentina"), "Argentina"));
		options.add(
			new SelectInfoFieldType.Option(
				new SingleValueInfoLocalizedValue<>("China"), "China"));
		options.add(
			new SelectInfoFieldType.Option(
				new SingleValueInfoLocalizedValue<>("Spain"), "Spain"));
		options.add(
			new SelectInfoFieldType.Option(
				new SingleValueInfoLocalizedValue<>("USA"), "USA"));

		return options;
	}

}
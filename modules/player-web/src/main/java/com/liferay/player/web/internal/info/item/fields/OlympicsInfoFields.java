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
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.player.web.internal.model.Olympics;

/**
 * @author Rubén Pulido
 */
public class OlympicsInfoFields {

	public static final InfoField<SelectInfoFieldType> cityInfoField =
		InfoField.builder(
		).infoFieldType(
			SelectInfoFieldType.INSTANCE
		).uniqueId(
			"city"
		).name(
			"city"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Olympics.class, "city")
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
			InfoLocalizedValue.localize(Olympics.class, "image")
		).build();
	public static final InfoField<NumberInfoFieldType> yearInfoField =
		InfoField.builder(
		).infoFieldType(
			NumberInfoFieldType.INSTANCE
		).uniqueId(
			"year"
		).name(
			"year"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Olympics.class, "year")
		).build();

}
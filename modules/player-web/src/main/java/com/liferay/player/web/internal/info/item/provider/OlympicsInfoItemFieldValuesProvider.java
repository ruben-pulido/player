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

package com.liferay.player.web.internal.info.item.provider;

import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.info.type.WebImage;
import com.liferay.player.web.internal.info.item.fields.OlympicsInfoFields;
import com.liferay.player.web.internal.model.Olympics;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = InfoItemFieldValuesProvider.class)
public class OlympicsInfoItemFieldValuesProvider
	implements InfoItemFieldValuesProvider<Olympics> {

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(Olympics olympics) {
		return InfoItemFieldValues.builder(
		).infoFieldValues(
			_getInfoFieldValues(olympics)
		).infoItemReference(
			new InfoItemReference(Olympics.class.getName(), olympics.getYear())
		).build();
	}

	private List<InfoFieldValue<Object>> _getInfoFieldValues(
		Olympics olympics) {

		List<InfoFieldValue<Object>> infoFieldValues = new ArrayList<>();

		infoFieldValues.add(
			new InfoFieldValue<>(
				OlympicsInfoFields.imageInfoField,
				new WebImage(olympics.getImage())));
		infoFieldValues.add(
			new InfoFieldValue<>(
				OlympicsInfoFields.cityInfoField, olympics.getCity()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				OlympicsInfoFields.yearInfoField, olympics.getYear()));

		return infoFieldValues;
	}

}
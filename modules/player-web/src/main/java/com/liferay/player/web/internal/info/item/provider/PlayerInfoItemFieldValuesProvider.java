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
import com.liferay.player.web.internal.info.item.fields.PlayerInfoFields;
import com.liferay.player.web.internal.model.Player;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = InfoItemFieldValuesProvider.class)
public class PlayerInfoItemFieldValuesProvider
	implements InfoItemFieldValuesProvider<Player> {

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(Player player) {
		return InfoItemFieldValues.builder(
		).infoFieldValues(
			_getInfoFieldValues(player)
		).infoItemReference(
			new InfoItemReference(Player.class.getName(), player.getId())
		).build();
	}

	private List<InfoFieldValue<Object>> _getInfoFieldValues(Player player) {
		List<InfoFieldValue<Object>> infoFieldValues = new ArrayList<>();

		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.imageInfoField,
				new WebImage(player.getImage())));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.nameInfoField, player.getName()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.countryInfoField, player.getCountry()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.numberOfMedalsInfoField,
				player.getNumberOfMedals()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.numberOfGoldMedalsInfoField,
				player.getNumberOfGoldMedals()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.numberOfSilverMedalsInfoField,
				player.getNumberOfSilverMedals()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.numberOfBronzeMedalsInfoField,
				player.getNumberOfBronzeMedals()));

		return infoFieldValues;
	}

}
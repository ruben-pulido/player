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

package com.liferay.player.web.internal.info.collection.provider;

import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.RelatedInfoItemCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.player.web.internal.model.Olympics;
import com.liferay.player.web.internal.model.Player;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = RelatedInfoItemCollectionProvider.class)
public class OlympicsWherePlayerParticipatedCollectionProvider
	implements RelatedInfoItemCollectionProvider<Player, Olympics> {

	@Override
	public InfoPage<Olympics> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		Optional<Object> relatedItemOptional =
			collectionQuery.getRelatedItemObjectOptional();

		Object relatedItem = relatedItemOptional.orElse(null);

		if (!(relatedItem instanceof Player)) {
			return InfoPage.of(Collections.emptyList());
		}

		Player player = (Player)relatedItem;

		return InfoPage.of(Arrays.asList(player.getOlympics()));
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale, "olympics-where-a-player-participated");
	}

}
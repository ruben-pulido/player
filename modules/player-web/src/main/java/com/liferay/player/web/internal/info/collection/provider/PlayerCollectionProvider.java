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
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.player.web.internal.model.Player;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements InfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<Player> players = new ArrayList();

		players.add(new Player(1, "Player 1", _IMAGES_URL_PREFIX + "1.png"));
		players.add(new Player(2, "Player 2", _IMAGES_URL_PREFIX + "2.png"));
		players.add(new Player(3, "Player 3", _IMAGES_URL_PREFIX + "3.png"));
		players.add(new Player(4, "Player 4", _IMAGES_URL_PREFIX + "4.png"));
		players.add(new Player(5, "Player 5", _IMAGES_URL_PREFIX + "5.png"));
		players.add(new Player(6, "Player 6", _IMAGES_URL_PREFIX + "6.png"));
		players.add(new Player(7, "Player 7", _IMAGES_URL_PREFIX + "7.png"));
		players.add(new Player(8, "Player 8", _IMAGES_URL_PREFIX + "8.png"));
		players.add(new Player(9, "Player 9", _IMAGES_URL_PREFIX + "9.png"));
		players.add(new Player(10, "Player 10", _IMAGES_URL_PREFIX + "10.png"));
		players.add(new Player(11, "Player 11", _IMAGES_URL_PREFIX + "11.png"));
		players.add(new Player(12, "Player 12", _IMAGES_URL_PREFIX + "12.png"));
		players.add(new Player(13, "Player 13", _IMAGES_URL_PREFIX + "13.png"));
		players.add(new Player(14, "Player 14", _IMAGES_URL_PREFIX + "14.png"));
		players.add(new Player(15, "Player 15", _IMAGES_URL_PREFIX + "15.png"));
		players.add(new Player(16, "Player 16", _IMAGES_URL_PREFIX + "16.png"));
		players.add(new Player(17, "Player 17", _IMAGES_URL_PREFIX + "17.png"));
		players.add(new Player(18, "Player 18", _IMAGES_URL_PREFIX + "18.png"));
		players.add(new Player(19, "Player 19", _IMAGES_URL_PREFIX + "19.png"));
		players.add(new Player(20, "Player 20", _IMAGES_URL_PREFIX + "20.png"));
		players.add(new Player(21, "Player 21", _IMAGES_URL_PREFIX + "21.png"));

		Pagination pagination = collectionQuery.getPagination();

		return InfoPage.of(
			ListUtil.subList(
				players, pagination.getStart(), pagination.getEnd()),
			pagination, players.size());
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "players");
	}

	private static final String _IMAGES_URL_PREFIX =
		"https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba" +
			"/latest/260x190/";

}
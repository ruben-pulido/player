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
import com.liferay.info.collection.provider.ConfigurableInfoCollectionProvider;
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
	implements ConfigurableInfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<Player> players = new ArrayList();

		players.add(
			new Player(
				1, "Michael Jordan",
				_IMAGES_URL_PREFIX + "v1538355600/primary/ovfsumgucccbmte6jtor",
				"USA"));
		players.add(
			new Player(
				2, "Scottie Pippen",
				_IMAGES_URL_PREFIX + "v1538355600/primary/wwnxbs0gbkfde7e5cexr",
				"USA"));
		players.add(
			new Player(
				3, "Pau Gasol",
				_IMAGES_URL_PREFIX + "v1538355600/primary/kvuhmguznpjsyksbyuf5",
				"Spain"));
		players.add(
			new Player(
				4, "Marc Gasol",
				_IMAGES_URL_PREFIX + "v1538355600/primary/jexpxpfukhtzak7yxsnx",
				"Spain"));
		players.add(
			new Player(
				5, "Juan Carlos Navarro",
				_IMAGES_URL_PREFIX + "v1538355600/primary/h86v1opzpoqp3d73xnua",
				"Spain"));
		players.add(
			new Player(
				6, "Ricky Rubio",
				_IMAGES_URL_PREFIX + "v1624370295/primary/bffqkclthzqcyeedxgod",
				"Spain"));
		players.add(
			new Player(
				7, "Earvin 'Magic' Johnson",
				_IMAGES_URL_PREFIX + "v1538355600/primary/yljqonedpamkg1oorrcf",
				"USA"));
		players.add(
			new Player(
				8, "Larry Bird",
				_IMAGES_URL_PREFIX + "v1538355600/primary/gxutmqwibagxrkxgecjc",
				"USA"));
		players.add(
			new Player(
				9, "Luis Scola",
				_IMAGES_URL_PREFIX + "v1538355600/primary/peryz8keyefpofbuqbvf",
				"Argentina"));
		players.add(
			new Player(
				10, "Rudy Fernández",
				_IMAGES_URL_PREFIX + "v1538355600/primary/on2z2ltua1fvmigl53nd",
				"Spain"));
		players.add(
			new Player(
				11, "Charles Barkley",
				_IMAGES_URL_PREFIX + "v1538355600/primary/n2ia3gbwzyrtzuvltdoj",
				"USA"));
		players.add(
			new Player(
				12, "Clyde Drexler",
				_IMAGES_URL_PREFIX + "v1538355600/primary/s6fycdawxkzerv5knxwu",
				"USA"));
		players.add(
			new Player(
				13, "Patrick Ewing",
				_IMAGES_URL_PREFIX + "v1538355600/primary/fehjyvrjysw92yzzrnc9",
				"USA"));
		players.add(
			new Player(
				14, "Kobe Bryant",
				_IMAGES_URL_PREFIX + "v1580072853/primary/wep96p00aavep8nofb79",
				"USA"));
		players.add(
			new Player(
				15, "David Robinson",
				_IMAGES_URL_PREFIX + "v1538355600/primary/lj7rijgfcq9ne4ofvxag",
				"USA"));
		players.add(
			new Player(
				16, "Ming Yao",
				_IMAGES_URL_PREFIX + "v1538355600/primary/kgbfmwi2xxjaoiod0kci",
				"China"));
		players.add(
			new Player(
				17, "Karl Malone",
				_IMAGES_URL_PREFIX + "v1538355600/primary/jhkeh6yamdyemw1ljosn",
				"USA"));
		players.add(
			new Player(
				18, "John Stockton",
				_IMAGES_URL_PREFIX + "v1538355600/primary/gzgmvlnnlqkczylsypqw",
				"USA"));
		players.add(
			new Player(
				19, "Kevin Durant",
				_IMAGES_URL_PREFIX + "v1538355600/primary/ygq72vralnjrxsq7oely",
				"USA"));
		players.add(
			new Player(
				20, "James Harden",
				_IMAGES_URL_PREFIX + "v1538355600/primary/cidcibl9iadtf14m8irm",
				"USA"));
		players.add(
			new Player(
				21, "Sergio Llull",
				_IMAGES_URL_PREFIX + "v1538355600/primary/qkuita037wkgkvd8axrl",
				"Spain"));

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
		"https://img.olympicchannel.com/images/image/private/t_1-1_600/f_auto/";

}
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
import com.liferay.info.collection.provider.FilteredInfoCollectionProvider;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.form.InfoForm;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.player.web.internal.info.item.fields.PlayerInfoFields;
import com.liferay.player.web.internal.model.Olympics;
import com.liferay.player.web.internal.model.Player;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>,
			   FilteredInfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<Player> players = new ArrayList();

		players.add(
			new Player(
				1, "Michael Jordan",
				_IMAGES_URL_PREFIX + "v1538355600/primary/ovfsumgucccbmte6jtor",
				"USA", new Olympics[] {_OLYMPICS_1984, _OLYMPICS_1992}));
		players.add(
			new Player(
				2, "Scottie Pippen",
				_IMAGES_URL_PREFIX + "v1538355600/primary/wwnxbs0gbkfde7e5cexr",
				"USA", new Olympics[] {_OLYMPICS_1992, _OLYMPICS_1996}));
		players.add(
			new Player(
				3, "Pau Gasol",
				_IMAGES_URL_PREFIX + "v1538355600/primary/kvuhmguznpjsyksbyuf5",
				"Spain",
				new Olympics[] {
					_OLYMPICS_2008, _OLYMPICS_2012, _OLYMPICS_2016,
					_OLYMPICS_2021
				}));
		players.add(
			new Player(
				4, "Marc Gasol",
				_IMAGES_URL_PREFIX + "v1538355600/primary/jexpxpfukhtzak7yxsnx",
				"Spain",
				new Olympics[] {
					_OLYMPICS_2008, _OLYMPICS_2012, _OLYMPICS_2021
				}));
		players.add(
			new Player(
				5, "Juan Carlos Navarro",
				_IMAGES_URL_PREFIX + "v1538355600/primary/h86v1opzpoqp3d73xnua",
				"Spain",
				new Olympics[] {
					_OLYMPICS_2008, _OLYMPICS_2012, _OLYMPICS_2016
				}));
		players.add(
			new Player(
				6, "Ricky Rubio",
				_IMAGES_URL_PREFIX + "v1624370295/primary/bffqkclthzqcyeedxgod",
				"Spain",
				new Olympics[] {
					_OLYMPICS_2008, _OLYMPICS_2016, _OLYMPICS_2021
				}));
		players.add(
			new Player(
				7, "Earvin 'Magic' Johnson",
				_IMAGES_URL_PREFIX + "v1538355600/primary/yljqonedpamkg1oorrcf",
				"USA", new Olympics[] {_OLYMPICS_1992}));
		players.add(
			new Player(
				8, "Larry Bird",
				_IMAGES_URL_PREFIX + "v1538355600/primary/gxutmqwibagxrkxgecjc",
				"USA", new Olympics[] {_OLYMPICS_1992}));
		players.add(
			new Player(
				9, "Luis Scola",
				_IMAGES_URL_PREFIX + "v1538355600/primary/peryz8keyefpofbuqbvf",
				"Argentina", new Olympics[] {_OLYMPICS_2004, _OLYMPICS_2008}));
		players.add(
			new Player(
				10, "Rudy Fernández",
				_IMAGES_URL_PREFIX + "v1538355600/primary/on2z2ltua1fvmigl53nd",
				"Spain",
				new Olympics[] {
					_OLYMPICS_2008, _OLYMPICS_2012, _OLYMPICS_2016,
					_OLYMPICS_2021
				}));
		players.add(
			new Player(
				11, "Charles Barkley",
				_IMAGES_URL_PREFIX + "v1538355600/primary/n2ia3gbwzyrtzuvltdoj",
				"USA", new Olympics[] {_OLYMPICS_1992, _OLYMPICS_1996}));
		players.add(
			new Player(
				12, "Clyde Drexler",
				_IMAGES_URL_PREFIX + "v1538355600/primary/s6fycdawxkzerv5knxwu",
				"USA", new Olympics[] {_OLYMPICS_1992}));
		players.add(
			new Player(
				13, "Patrick Ewing",
				_IMAGES_URL_PREFIX + "v1538355600/primary/fehjyvrjysw92yzzrnc9",
				"USA", new Olympics[] {_OLYMPICS_1984, _OLYMPICS_1992}));
		players.add(
			new Player(
				14, "Kobe Bryant",
				_IMAGES_URL_PREFIX + "v1580072853/primary/wep96p00aavep8nofb79",
				"USA", new Olympics[] {_OLYMPICS_2008, _OLYMPICS_2012}));
		players.add(
			new Player(
				15, "David Robinson",
				_IMAGES_URL_PREFIX + "v1538355600/primary/lj7rijgfcq9ne4ofvxag",
				"USA",
				new Olympics[] {
					_OLYMPICS_1988, _OLYMPICS_1992, _OLYMPICS_1996
				}));
		players.add(
			new Player(
				16, "Ming Yao",
				_IMAGES_URL_PREFIX + "v1538355600/primary/kgbfmwi2xxjaoiod0kci",
				"China",
				new Olympics[] {
					_OLYMPICS_2000, _OLYMPICS_2004, _OLYMPICS_2008
				}));
		players.add(
			new Player(
				17, "Karl Malone",
				_IMAGES_URL_PREFIX + "v1538355600/primary/jhkeh6yamdyemw1ljosn",
				"USA", new Olympics[] {_OLYMPICS_1992, _OLYMPICS_1996}));
		players.add(
			new Player(
				18, "John Stockton",
				_IMAGES_URL_PREFIX + "v1538355600/primary/gzgmvlnnlqkczylsypqw",
				"USA", new Olympics[] {_OLYMPICS_1992, _OLYMPICS_1996}));
		players.add(
			new Player(
				19, "Kevin Durant",
				_IMAGES_URL_PREFIX + "v1538355600/primary/ygq72vralnjrxsq7oely",
				"USA",
				new Olympics[] {
					_OLYMPICS_2012, _OLYMPICS_2016, _OLYMPICS_2021
				}));
		players.add(
			new Player(
				20, "James Harden",
				_IMAGES_URL_PREFIX + "v1538355600/primary/cidcibl9iadtf14m8irm",
				"USA", new Olympics[] {_OLYMPICS_2012}));
		players.add(
			new Player(
				21, "Sergio Llull",
				_IMAGES_URL_PREFIX + "v1538355600/primary/qkuita037wkgkvd8axrl",
				"Spain",
				new Olympics[] {
					_OLYMPICS_2012, _OLYMPICS_2016, _OLYMPICS_2021
				}));

		Pagination pagination = collectionQuery.getPagination();

		List<Player> filteredPlayers = ListUtil.copy(players);

		Optional<Map<String, String[]>> configurationOptional =
			collectionQuery.getConfigurationOptional();

		if (configurationOptional.isPresent()) {
			Map<String, String[]> configurationMap =
				configurationOptional.get();

			String[] countries = configurationMap.get(
				PlayerInfoFields.countryInfoField.getName());

			Stream<Player> stream = filteredPlayers.stream();

			filteredPlayers = stream.filter(
				player -> ArrayUtil.contains(countries, player.getCountry())
			).collect(
				Collectors.toList()
			);
		}

		List<Player> pageFilteredPlayers = ListUtil.subList(
			filteredPlayers, pagination.getStart(), pagination.getEnd());

		return InfoPage.of(
			pageFilteredPlayers, pagination, filteredPlayers.size());
	}

	@Override
	public InfoForm getConfigurationInfoForm() {
		return InfoForm.builder(
		).infoFieldSetEntry(
			PlayerInfoFields.countryInfoField
		).build();
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "players");
	}

	private static final String _IMAGES_URL_PREFIX =
		"https://img.olympicchannel.com/images/image/private/t_1-1_600/f_auto/";

	private static final Olympics _OLYMPICS_1984 = new Olympics(
		1984, "Los Angeles");

	private static final Olympics _OLYMPICS_1988 = new Olympics(1988, "Seoul");

	private static final Olympics _OLYMPICS_1992 = new Olympics(
		1992, "Barcelona");

	private static final Olympics _OLYMPICS_1996 = new Olympics(
		1996, "Atlanta");

	private static final Olympics _OLYMPICS_2000 = new Olympics(2000, "Sydney");

	private static final Olympics _OLYMPICS_2004 = new Olympics(2004, "Athens");

	private static final Olympics _OLYMPICS_2008 = new Olympics(
		2008, "Beijing");

	private static final Olympics _OLYMPICS_2012 = new Olympics(2012, "London");

	private static final Olympics _OLYMPICS_2016 = new Olympics(
		2016, "Rio de Janeiro");

	private static final Olympics _OLYMPICS_2021 = new Olympics(2021, "Tokyo");

}
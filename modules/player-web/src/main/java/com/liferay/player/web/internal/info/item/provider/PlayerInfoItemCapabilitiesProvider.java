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

import com.liferay.info.item.capability.InfoItemCapability;
import com.liferay.info.item.provider.InfoItemCapabilitiesProvider;
import com.liferay.player.web.internal.model.Player;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.template.info.item.capability.TemplateInfoItemCapability;

import java.util.*;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rubén Pulido
 */
@Component(service = InfoItemCapabilitiesProvider.class)
public class PlayerInfoItemCapabilitiesProvider
	implements InfoItemCapabilitiesProvider<Player> {

	@Override
	public List<InfoItemCapability> getInfoItemCapabilities() {
		return ListUtil.fromArray(_templatePageInfoItemCapability);
	}

	@Reference
	private TemplateInfoItemCapability _templatePageInfoItemCapability;

}
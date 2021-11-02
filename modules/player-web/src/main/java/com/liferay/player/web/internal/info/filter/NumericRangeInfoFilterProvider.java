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

package com.liferay.player.web.internal.info.filter;

import com.liferay.info.filter.InfoFilterProvider;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = InfoFilterProvider.class)
public class NumericRangeInfoFilterProvider
	implements InfoFilterProvider<NumericRangeInfoFilter> {

	@Override
	public NumericRangeInfoFilter create(Map<String, String[]> values) {
		NumericRangeInfoFilter numericRangeInfoFilter =
			new NumericRangeInfoFilter();

		for (Map.Entry<String, String[]> entry : values.entrySet()) {
			if (StringUtil.startsWith(
					entry.getKey(),
					NumericRangeInfoFilter.FILTER_TYPE_NAME + "_")) {

				String[] value = entry.getValue();

				if ((value != null) && (value.length > 0)) {
					String[] valueParts = value[0].split("-");

					if (valueParts.length > 0) {
						numericRangeInfoFilter.setMin(
							GetterUtil.getInteger(valueParts[0], -1));
					}
					else {
						numericRangeInfoFilter.setMin(-1);
					}

					if (valueParts.length > 1) {
						numericRangeInfoFilter.setMax(
							GetterUtil.getInteger(valueParts[1], -1));
					}
					else {
						numericRangeInfoFilter.setMax(-1);
					}
				}

				return numericRangeInfoFilter;
			}
		}

		return null;
	}

}
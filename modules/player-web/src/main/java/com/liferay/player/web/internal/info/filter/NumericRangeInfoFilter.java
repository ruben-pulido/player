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

import com.liferay.info.filter.InfoFilter;

/**
 * @author Rubén Pulido
 */
public class NumericRangeInfoFilter implements InfoFilter {

	public static final String FILTER_TYPE_NAME = "numericRange";

	@Override
	public String getFilterTypeName() {
		return FILTER_TYPE_NAME;
	}

	public int getMax() {
		return _max;
	}

	public int getMin() {
		return _min;
	}

	public void setMax(int max) {
		_max = max;
	}

	public void setMin(int min) {
		_min = min;
	}

	private int _max;
	private int _min;

}
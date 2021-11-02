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

import {
	getCollectionFilterValue,
	setCollectionFilterValue,
} from '@liferay/fragment-renderer-collection-filter-impl';

export default function NumericRangeFilter({
	fragmentEntryLinkId,
	fragmentEntryLinkNamespace,
}) {
	const form = document.getElementById(`${fragmentEntryLinkNamespace}form`);
	const minInput = form && form.elements['min'];
	const maxInput = form && form.elements['max'];

	if (!form || (!minInput && !maxInput)) {
		return;
	}

	const handleChange = () => {
		const nextMin = minInput.value;
		const nextMax = maxInput.value;

		setCollectionFilterValue(
			'numericRange',
			fragmentEntryLinkId,
			`${nextMin}-${nextMax}`
		);
	};

	const urlValue = getCollectionFilterValue(
		'numericRange',
		fragmentEntryLinkId
	);

	if (urlValue !== null) {
		const parts = urlValue.split('-');

		minInput.value = isNaN(parts[0]) ? '' : parts[0];
		maxInput.value = isNaN(parts[1]) ? '' : parts[1];
	}

	form.addEventListener('change', handleChange);

	return {
		dispose() {
			form.removeEventListener('change', handleChange);
		},
	};
}

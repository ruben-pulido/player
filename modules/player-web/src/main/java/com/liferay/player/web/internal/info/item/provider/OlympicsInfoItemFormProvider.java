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

import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.player.web.internal.info.item.fields.OlympicsInfoFields;
import com.liferay.player.web.internal.model.Olympics;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rubén Pulido
 */
@Component(immediate = true, service = InfoItemFormProvider.class)
public class OlympicsInfoItemFormProvider
	implements InfoItemFormProvider<Olympics> {

	@Override
	public InfoForm getInfoForm() {
		return InfoForm.builder(
		).infoFieldSetEntry(
			InfoFieldSet.builder(
			).infoFieldSetEntry(
				OlympicsInfoFields.cityInfoField
			).infoFieldSetEntry(
				OlympicsInfoFields.imageInfoField
			).infoFieldSetEntry(
				OlympicsInfoFields.yearInfoField
			).labelInfoLocalizedValue(
				InfoLocalizedValue.localize(
					OlympicsInfoFields.class, "olympics")
			).build()
		).name(
			"Olympics"
		).build();
	}

}
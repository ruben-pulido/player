title: Make Collections shine with the Collection Display fragment
author:
  name: Rubén Pulido
style: basic-style.css
output: index.html
controls: false

--
# Make Collections shine with the Collection Display fragment
--
### The original Dream Team
<img 
  src="./images/usa-dream-team-2.jpg" 
  alt="USA Dream Team" 
  style="margin-left:auto;margin-right:auto;display:block;max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### The Spanish Dream Team
<img 
  src="./images/spain-dream-team.png" 
  alt="Spain Dream Team" 
  style="margin-left:auto;margin-right:auto;display:block;max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Olympics
<img 
  src="./images/olympic-mascots.png" 
  alt="Olympic Mascots" 
  style="margin-left:auto;margin-right:auto;display:block;max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering:
1. **Pagination**
2. **Filtering**
3. **Related Items**
--
### Code
https://github.com/ruben-pulido/player
### Slides
https://ruben-pulido.github.io/player/index.html
--
### Requirement 
Display list of Players supporting pagination, with each page displaying 6 Players

### Solution
- Create ```PlayerCollectionProvider``` supporting pagination
- Create Content Page with Collection Display fragment to display list of Players
- Configure Collection Display fragment to perform pagination
--
### What we will be covering (1): <mark>Pagination</mark>
* <mark>Create a **collection provider**</mark>
* Add support for **pagination** to collection provider
* Display Collection Provider in Content Page with **Collection Display**
* Enable pagination and choose between different **pagination types**
--
### Setup
```
blade init -v portal-7.4-ga3 player

Add to gradle.properties the following properties matching your local environment:

# Set the folder that contains the Liferay bundle downloaded from the
# "liferay.workspace.bundle.url" property. The default value is "bundles".
liferay.workspace.home.dir=/your/path/to/bundles

# Set the folder that contains the Liferay portal source code
liferay.source.home.dir=/your/path/to/liferay-portal

blade create \
  -t service \ 
  -p com.liferay.player.web.internal.info.collection.provider \
  -c PlayerCollectionProvider \
  -s com.liferay.info.collection.provider.InfoCollectionProvider 
  -v 7.4 \
  player-web

blade deploy
```
--
### Implement required methods in Collection Provider
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider implements InfoCollectionProvider {

	@Override
	public InfoPage getCollectionInfoPage(CollectionQuery collectionQuery) {
		return null;
	}

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

}
```
--
### Collection Provider is now listed
<img 
  src="./images/collection-provider.png" 
  alt="Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;"/>
--
### Create Player Model ⛹️
```java
public class Player {

	public Player(long id, String name, String image) {
		_id = id;
		_name = name;
		_image = image;
	}

	public long getId() {
		return _id;
	}

	public String getImage() {
		return _image;
	}

	public String getName() {
		return _name;
	}

	private long _id;
	private String _image;
	private String _name;

}
```
--
### Make Player implement ClassedModel
```java
public class Player implements ClassedModel {

  @Override
  public Class<?> getModelClass() {
    return Player.class;
  }

  @Override
  public String getModelClassName() {
    return Player.class.getName();
  }

  @Override
  public Serializable getPrimaryKeyObj() {
    return _id;
  }
  
  // ...
}
```
--
### Associate PlayerCollectionProvider to Player
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements InfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		return null;
	}

	@Override
	public String getLabel(Locale locale) {
		return null;
	}

}
```
--
### Collection Provider now displays associated type
<img 
  src="./images/player-collection-provider.png" 
  alt="Player Collection Provider"
  style="max-height:470px;max-width:800px;height:auto;width:auto;"/>
--
### Assign label to Collection Provider and associated type
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements InfoCollectionProvider<Player> {

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "players");
	}
}
```
bnd.bnd
```
Provide-Capability:\
	liferay.language.resources;\
		resource.bundle.base.name="content.Language"
```
Language.properties
```
model.resource.com.liferay.player.web.internal.model.Player=Player
players=Players
```
--
### Collection Provider and associated type now display label
<img 
  src="./images/player-collection-provider-with-label.png" 
  alt="Player Collection Provider with Label" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;"/>
--
### Implement getCollectionInfoPage in PlayerCollectionProvider
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider implements InfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(CollectionQuery collectionQuery) {
		List<Player> players = new ArrayList();

		players.add(new Player(1, "Michael Jordan", _PLAYER1_IMAGE_URL));
		players.add(new Player(2, "Shaquille O'Neal", _PLAYER2_IMAGE_URL));
		return InfoPage.of(players);
	}

    // ..
}
```
--
### View items
<img 
  src="./images/view-items-menu.png" 
  alt="View Items Menu" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;"/>
--
### Something is missing
<img 
  src="./images/view-items-error.png" alt="View Items Error" width="800"/>
--
### PlayerInfoItemFieldValuesProvider
```java
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
				PlayerInfoFields.nameInfoField, player.getName()));
		infoFieldValues.add(
			new InfoFieldValue<>(
				PlayerInfoFields.imageInfoField, new WebImage(player.getImage())));

		return infoFieldValues;
	}
}
```
--
### Create PlayerInfoFields Interface describing Player fields
```java
public interface PlayerInfoFields {

	public final InfoField<ImageInfoFieldType> imageInfoField =
		InfoField.builder(
		).infoFieldType(
			ImageInfoFieldType.INSTANCE
		).name(
			"image"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "image")
		).build();
	public final InfoField<TextInfoFieldType> nameInfoField = InfoField.builder(
	).infoFieldType(
		TextInfoFieldType.INSTANCE
	).name(
		"name"
	).labelInfoLocalizedValue(
		InfoLocalizedValue.localize(Player.class, "name")
	).build();

}
```
--
### View items
<img 
  src="./images/view-items-menu.png" 
  alt="View Items Menu" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;"/>
--
### View items now displays the list of players
<img 
  src="./images/view-items-success.png" 
  alt="View Items Menu" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;"/>
--
### Let's return now 21 players
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements InfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<Player> players = new ArrayList();

		players.add(new Player(1, "Player 1", _PLAYER1_IMAGE_URL));
		players.add(new Player(2, "Player 2", _PLAYER2_IMAGE_URL));
		
		// ...
		
		players.add(new Player(21, "Player 21", _PLAYER21_IMAGE_URL));

		return InfoPage.of(players);
	}
}
```
--
### View items
<img 
  src="./images/view-items-menu.png" 
  alt="View Items Menu" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### All 21 players are displayed regardless of the page selected
<img 
  src="./images/view-items-page1-wrong.png" 
  alt="View Items Page 1 Wrong Results" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
---
### What we will be covering (1): <mark>Pagination</mark>
- Create a **collection provider** ✔ 
- <mark>Add support for **pagination** to collection provider</mark>
- Display Collection Provider in Content Page with **Collection Display**
- Enable pagination and choose between different **pagination types**
--
### Return sublist of elements according to pagination object
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements InfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<Player> players = new ArrayList();

		players.add(new Player(1, "Player 1", _PLAYER1_IMAGE_URL));
		players.add(new Player(2, "Player 2", _PLAYER2_IMAGE_URL));

		// ...

		players.add(new Player(21, "Player 21", _PLAYER21_IMAGE_URL));

		Pagination pagination = collectionQuery.getPagination();

		return InfoPage.of(
			ListUtil.subList(
				players, pagination.getStart(), pagination.getEnd()));
	}
}
```
--
### Sublist but no pagination options
<img 
  src="./images/view-items-page1-20items-1page.png" 
  alt="View Items Page 1. 20 items. 1 Page." 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Include pagination and totalCount in InfoPage
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements InfoCollectionProvider<Player> {

	@Override
	public InfoPage<Player> getCollectionInfoPage(
		CollectionQuery collectionQuery) {

		List<Player> players = new ArrayList();

		players.add(new Player(1, "Player 1", _PLAYER1_IMAGE_URL));
		players.add(new Player(2, "Player 2", _PLAYER2_IMAGE_URL));

		// ...

		players.add(new Player(21, "Player 21", _PLAYER21_IMAGE_URL));

		Pagination pagination = collectionQuery.getPagination();

		return InfoPage.of(
			ListUtil.subList(
				players, pagination.getStart(), pagination.getEnd()),
			pagination, players.size());
	}
}
```
--
### Sublist with pagination options (1)
<img 
  src="./images/view-items-page1-correct.png" 
  alt="View Items Page 1. 20 items. 1 Page." 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Sublist with pagination options (2) 
<img 
  src="./images/view-items-page2.png" 
  alt="View Items Page 1. 20 items. 1 Page." 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering (1): <mark>Pagination</mark>
- Create a **collection provider** ✔ 
- Add support for **pagination** to collection provider ✔
- <mark>Display Collection Provider in Content Page with **Collection Display**</mark>
- Enable pagination and choose between different **pagination types**
--
### Create new Page
<img 
  src="./images/create-page-players.png" 
  alt="Create Page" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Add a Collection Display
<img 
src="./images/add-collection-display.png" 
  alt="Create Page" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Collection
<img 
src="./images/select-collection.png" 
  alt="Select Collection" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Collection Provider does not list Players
<img 
src="./images/select-collection-provider.png" 
  alt="Select Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Create PlayerInfoItemFormProvider
```java
@Component(immediate = true, service = InfoItemFormProvider.class)
public class PlayerInfoItemFormProvider
	implements InfoItemFormProvider<Player> {

	@Override
	public InfoForm getInfoForm() {
		return InfoForm.builder(
		).infoFieldSetEntry(
			InfoFieldSet.builder(
			).infoFieldSetEntry(
				PlayerInfoFields.nameInfoField
			).infoFieldSetEntry(
				PlayerInfoFields.imageInfoField
			).labelInfoLocalizedValue(
				InfoLocalizedValue.localize(PlayerInfoFields.class, "player")
			).build()
		).name(
			"Player"
		).build();
	}

}
```
--
### Select Collection Provider now lists Players
<img 
src="./images/select-collection-provider-players.png" 
  alt="Select Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Collection Display is now assigned to Players
<img 
  src="./images/collection-display-players.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Set Layout to 6 columns and add Card fragment
<img 
  src="./images/collection-display-6columns.png" 
  alt="Collection Display" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Map Heading to Name field
<img 
  src="./images/collection-display-6columns-name-mapped.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Map Image to Image field
<img 
  src="./images/collection-display-6columns-name-image-mapped.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Publish page
<img 
  src="./images/collection-display-6columns-name-image-mapped-view.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering (1): <mark>Pagination</mark>
- Create a **collection provider** ✔ 
- Add support for **pagination** to collection provider ✔
- Display Collection Provider in Content Page with **Collection Display** ✔
- <mark>Enable pagination and choose between different **pagination types**</mark>
--
### Numeric Pagination (Edit Mode)
<img 
  src="./images/collection-display-6columns-pagination-numeric.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Numeric Pagination (View Mode)
<img 
  src="./images/collection-display-6columns-pagination-numeric-page2-view.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Simple Pagination (Edit Mode)
<img 
  src="./images/collection-display-6columns-pagination-simple.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Simple Pagination (Preview Mode)
<img 
  src="./images/collection-display-6columns-pagination-simple-last-page-preview.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Pagination in Collection Display can be applied to any collection type
- Collection Provider
- Manual Collection
- Dynamic Collection
--
### What we will be covering (2): <mark>Filtering<mark>
* <mark>**Pre filter** the items returned by collection providers</mark>
* Use Collection Filter fragments to **allow end users to filter** collection items themselves
* Make an existing Collection Provider **compatible with a filter** (e.g: Keywords and Categories filters)
* Create a **new type of filter** and make a Collection Provider compatible with it
--
**Requirement**

Allow **Page Creator** to filter players based on their country.

**Solution**
- Add a country text field to ```Player```, ```PlayerInfoFields```, ```PlayerInfoItemFormProvider```, ```PlayerInfoItemFieldValuesProvider``` and ```PlayerCollectionProvider```
- Make ```PlayerCollectionProvider``` implement ```ConfigurableInfoCollectionProvider```
- Let the **Page Creator** configure the Collection Display Fragment to prefilter Players from the selected countries
--
### Make PlayerCollectionProvider implement ConfigurableInfoCollectionProvider
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player> {

    // ..
}
```
--
### Implement getConfigurationInfoForm with country field
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player> {

	@Override
	public InfoForm getConfigurationInfoForm() {
		return InfoForm.builder(
		).infoFieldSetEntry(
			PlayerInfoFields.countryInfoField
		).build();
	}

    // ...
    
}
```
--
### Filter Collection option is now available
<img 
  src="./images/collection-display-prefilter.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Filter players according to configuration values
```java
public class PlayerCollectionProvider implements ConfigurableInfoCollectionProvider<Player> {

	public InfoPage<Player> getCollectionInfoPage(CollectionQuery collectionQuery) {
        // ...
		List<Player> filteredPlayers = ListUtil.copy(players);

		Optional<Map<String, String[]>> configurationOptional =
			collectionQuery.getConfigurationOptional();

		if (configurationOptional.isPresent()) {
			Map<String, String[]> configurationMap = configurationOptional.get();

			String[] countries = configurationMap.get(
				PlayerInfoFields.countryInfoField.getName());

			filteredPlayers = filteredPlayers.stream().filter(
				player -> ArrayUtil.contains(countries, player.getCountry())
			).collect(Collectors.toList());
		}

		List<Player> pageFilteredPlayers = ListUtil.subList(
			filteredPlayers, pagination.getStart(), pagination.getEnd());

		return InfoPage.of(pageFilteredPlayers, pagination, filteredPlayers.size());
	}
}
```
--
### Filter players from Spain 
<img 
  src="./images/collection-display-prefilter-country-spain.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Only players from Spain are displayed
<img 
  src="./images/collection-display-prefilter-country-spain-edit-mode.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Filter players from USA
<img 
  src="./images/collection-display-prefilter-country-usa.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Only players from USA are displayed
<img 
  src="./images/collection-display-prefilter-country-usa-edit-mode-page1.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Filtered players are paginated
<img 
  src="./images/collection-display-prefilter-country-usa-edit-mode-page2.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Filtered players are paginated
<img 
  src="./images/collection-display-prefilter-country-usa-edit-mode-page3.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
**Requirement**

Allow **Page Creator** to select country for filtering from a drop down menu.

**Solution**
- Change type of ```countryInfoField``` to ```SelectInfoFieldType```
- Specify ```OPTIONS``` attribute with available Country values
--
### Drop down menu can be specified for listing available filter values
```java
public class PlayerInfoFields {

	public static final InfoField<SelectInfoFieldType> countryInfoField =
		InfoField.builder(
		).infoFieldType(
			SelectInfoFieldType.INSTANCE
		).name(
			"country"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "country")
		).attribute(
			SelectInfoFieldType.OPTIONS, _getCountryOptions()
		).build();

	private static List<SelectInfoFieldType.Option> _getCountryOptions() {
		List<SelectInfoFieldType.Option> options = new ArrayList();

		options.add(new SelectInfoFieldType.Option("Argentina", "Argentina"));
		options.add(new SelectInfoFieldType.Option("China", "China"));
		options.add(new SelectInfoFieldType.Option("Spain", "Spain"));
		options.add(new SelectInfoFieldType.Option("USA", "USA"));

		return options;
	}
}
```
--
### Available country values for filtering are now listed in a dropdown
<img 
  src="./images/collection-display-prefilter-country-single-select-china.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Players of the selected country are displayed
<img 
  src="./images/collection-display-prefilter-country-single-select-china-edit-view.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
**Requirement**

Allow **Page Creator** to select **multiple countries** for filtering from a drop down menu.

**Solution**
Specify ```MULTIPLE``` attribute to ```true```
--
### Multi selection can be configured for filter values
```java
public class PlayerInfoFields {

	public static final InfoField<SelectInfoFieldType> countryInfoField =
		InfoField.builder(
		).infoFieldType(
			SelectInfoFieldType.INSTANCE
		).name(
			"country"
		).labelInfoLocalizedValue(
			InfoLocalizedValue.localize(Player.class, "country")
		).attribute(
			SelectInfoFieldType.OPTIONS, _getCountryOptions()
		).attribute(
			SelectInfoFieldType.MULTIPLE, true
		).build();
    
    // ..
}
```
--
### Multiple countries can now be selected
<img 
  src="./images/collection-display-prefilter-country-multiple-select-china-argentina.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Players of the selected countries are displayed
<img 
  src="./images/collection-display-prefilter-country-multiple-select-china-argentina-edit-view.png" 
  alt="Collection Display Players" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering (2): <mark>Filtering<mark>
* **Pre filter** the items returned by collection providers ✔
* <mark>Use Collection Filter fragments to **allow end users to filter** collection items themselves</mark>
* Make an existing Collection Provider **compatible with a filter** (e.g: Keywords and Categories filters)
* Create a **new type of filter** and make a Collection Provider compatible with it

--
### Out of the Box Collection Filter fragments
- Keywords
- Categories
--

- **Requirement**

Display national teams and let end user filter them based on entered continent and languages selected on a drop down.

**Solution**
- Model National Team as a Web Content Structure, Languages as Categories and Continents as Keywords
- Create Web Contents representing National Teams and assign them Categories and Keywords
- Create manual Collection with available National Teams
- Use Collection Display, Collection Filter and Applied Filters fragments to create Page
--
### Model National Team as a Web Content Structure.
<img 
  src="./images/national-team-structure.png" 
  alt="National Team Web Content Structure" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Model Languages as Categories
<img 
  src="./images/vocabulary-languages.png" 
  alt="Language Categories" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Model Continents as Keywords (Tags)
<img 
  src="./images/tags-continents.png" 
  alt="Language Categories" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Create Web Contents representing National Teams and assign them Categories and Keywords
<img 
  src="./images/web-content-ch.png" 
  alt="National Team Web Content" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Create manual Collection with available National Teams
<img 
  src="./images/national-teams-manual-collection.png" 
  alt="National Teams manual Collection" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Use Collection Display to display National Teams
<img 
  src="./images/national-teams-collection-display.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Use Keywords Collection Filter to filter based on Continent
<img 
  src="./images/national-teams-keywords-continent-filter.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Use Categories Collection Filter to filter based on Language
<img 
  src="./images/national-teams-categories-language-filter.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Use Applied Filters fragment to display current Collection Filter values
<img 
  src="./images/national-teams-applied-filters.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select English, German or Spanish as Language
<img 
  src="./images/national-teams-languages-selected.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### National Teams with English, German or Spanish as Language are displayed
<img 
  src="./images/national-teams-languages-selected-displayed.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Enter Europe as Continent
<img 
  src="./images/national-teams-continent-entered.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
#### Bonus Requirement 
Allow filtering countries in which at least one language of two different groups is spoken.

#### Solution
Add a second Categories Collection Filter fragment
--
### Add a second Categories Collection Filter fragment
<img 
  src="./images/national-teams-categories-language-filter-2.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select (English, German or Spanish) and (French or Italian) as Language
<img 
  src="./images/national-teams-languages-selected-2.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### National Teams with (English, German or Spanish) and (French or Italian) as Language are displayed
<img 
  src="./images/national-teams-languages-selected-2-displayed.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering (2): <mark>Filtering<mark>
* **Pre filter** the items returned by collection providers ✔
* Use Collection Filter fragments to **allow end users to filter** collection items themselves ✔
* <mark>Make an existing Collection Provider **compatible with a filter** (e.g: Keywords and Categories filters)</mark>
* Create a **new type of filter** and make a Collection Provider compatible with it
--
### Requirement 
Let end user filter Players based on the Olympics where they participated

### Solution
Support Keywords filter on PlayerCollectionProvider and allow end user to enter Olympic cities as comma separated string to filter Players. 
--
### Create Olympics Model
```java
public class Olympics {

	public Olympics(int year, String city) {
		_year = year;
		_city = city;
	}

	public String getCity() {
		return _city;
	}

	public int getYear() {
		return _year;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder(6);

		sb.append(_city);
		sb.append(" ");
		sb.append(_year);

		return sb.toString();
	}

	private String _city;
	private int _year;

}
```
--
### Add array of Olympics to Player
```java
public class Player implements ClassedModel {

	public Player(
		long id, String name, String image, String country,
		Olympics[] olympics) {

		_id = id;
		_name = name;
		_image = image;
		_country = country;
		_olympics = olympics;
	}
    
	public Olympics[] getOlympics() {
		return _olympics;
	}

	private Olympics[] _olympics;

    // ..
}
```
--
### PlayerCollectionProvider implements FilteredInfoCollectionProvider and returns KeywordsInfoFilter as supported InfoFilter
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>,
			   FilteredInfoCollectionProvider<Player> {
	@Override
	public List<InfoFilter> getSupportedInfoFilters() {
		return Collections.singletonList(new KeywordsInfoFilter());
	}
    
    // ..
  }
```
--
### Add Collection Filter
<img 
  src="./images/add-collection-filter.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Players as target collection
<img 
  src="./images/add-collection-filter-target-collection.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Keywords as filter
<img 
  src="./images/add-collection-filter-type-keywords.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Enter Olympics as label
<img 
  src="./images/add-collection-filter-label-olympics.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Filter players based on the Olympics specified in Keywords Filter
```java
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>, 
               FilteredInfoCollectionProvider<Player> {
	public InfoPage<Player> getCollectionInfoPage(CollectionQuery collectionQuery) {
		// ..
		Optional<KeywordsInfoFilter> keywordsInfoFilterOptional =
			collectionQuery.getInfoFilterOptional(KeywordsInfoFilter.class);

		if (keywordsInfoFilterOptional.isPresent()) {
			KeywordsInfoFilter keywordsInfoFilter = keywordsInfoFilterOptional.get();
			String keywords = keywordsInfoFilter.getKeywords();
			if (Validator.isNotNull(keywords)) {
				String[] keywordsArray = keywords.split(", ");
				if (keywordsArray.length > 0) {
					Stream<Player> stream = filteredPlayers.stream();
					filteredPlayers = stream.filter(
						player -> { 
                            List<String> olympicCities = Stream.of(player.getOlympics())
                              .map(Olympics::getCity).collect(Collectors.toList());
							return olympicCities.containsAll(Arrays.asList(keywordsArray));
                        }
					).collect(Collectors.toList());
				}
			}
		}
		// ..
    }
}
```
--
### Players (prefiltered by page creator to Spain) can now be filtered by end user based on Olympics
<img 
  src="./images/players-spain.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Players (prefiltered by page creator to Spain) filtered by end user to Tokyo and Rio Olympics
<img 
  src="./images/players-spain-tokyo-rio.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Add Applied Filters fragment
<img 
  src="./images/add-applied-filters.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Configure target collection to Players and enable clear option
<img 
  src="./images/add-applied-filters-target-collection.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Applied filters fragment displays current values entered
<img 
  src="./images/players-applied-filters.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Requirement
Let end user filter Players based on the Olympics where they participated and
whether they won any gold, silver or bronze medals.

### Solution
Support Categories filter on PlayerCollectionProvider and allow end user to select Gold / Silver / Bronze in a drop down menu with multiple selection.

--
### Add int arrays to Player with years when each type of medal was won
```java
public class Player implements ClassedModel {

	public Player(
		long id, String name, String image, String country, Olympics[] olympics,
		int[] goldMedals, int[] silverMedals, int[] bronzeMedals) {
        // ..
		_goldMedals = goldMedals;
		_silverMedals = silverMedals;
		_bronzeMedals = bronzeMedals;
	}

	public int[] getBronzeMedals() {
		return _bronzeMedals;
	}
    // ..
	public int[] getSilverMedals() {
		return _silverMedals;
	}
    
	private int[] _bronzeMedals;
	private int[] _goldMedals;
	private int[] _silverMedals;
}
```
--
### Add CategoriesInfoFilter to the list of supported InfoFilters
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>,
			   FilteredInfoCollectionProvider<Player> {
 
    // ..

	@Override
	public List<InfoFilter> getSupportedInfoFilters() {
		List<InfoFilter> infoFilters = new ArrayList<>();

		infoFilters.add(new CategoriesInfoFilter());
		infoFilters.add(new KeywordsInfoFilter());

		return infoFilters;
	}
}
```
--
### Create Medals Vocabulary with Gold, Silver and Bronze as categories
<img 
  src="./images/vocabulary-medals.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Add a second Collection filter
<img 
  src="./images/add-collection-filter-2.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Players as target collection and Category as filter
<img 
  src="./images/add-collection-filter-2-category.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Medals as source
<img 
  src="./images/add-collection-filter-2-category-source.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Specify Medals as label
<img 
  src="./images/add-collection-filter-2-category-label.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Filter players based on the Medals specified in Categories Filter (1)
```java
public class PlayerCollectionProvider
        implements ConfigurableInfoCollectionProvider<Player>,
        FilteredInfoCollectionProvider<Player> {
  public InfoPage<Player> getCollectionInfoPage(CollectionQuery collectionQuery) {
    // ..
    Optional<CategoriesInfoFilter> categoriesInfoFilterOptional =
            collectionQuery.getInfoFilterOptional(CategoriesInfoFilter.class);

    if (categoriesInfoFilterOptional.isPresent()) {
      CategoriesInfoFilter categoriesInfoFilter = categoriesInfoFilterOptional.get();

      long[] categoryIds = ArrayUtil.append(categoriesInfoFilter.getCategoryIds());

      categoryIds = ArrayUtil.unique(categoryIds);

      LongStream longStream = Arrays.stream(categoryIds);

      List<String> categoryNames = longStream.mapToObj(
              categoryId -> _assetCategoryService.fetchCategory(categoryId)
      ).filter(Objects::nonNull).map(AssetCategory::getName).collect(Collectors.toList());
      // [continues]
    }
    // ..
  }
}
```
--
### Filter players based on the Medals specified in Categories Filter (2)
```java
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>, 
               FilteredInfoCollectionProvider<Player> {
	public InfoPage<Player> getCollectionInfoPage(CollectionQuery collectionQuery) { //..
		if (categoriesInfoFilterOptional.isPresent()) { // [continued]
			if (!categoryNames.isEmpty()) {
				Stream<Player> stream = filteredPlayers.stream();
				filteredPlayers = stream.filter(player -> {
						boolean matches = true;
						for (String categoryName : categoryNames) {
							if (categoryName.equals("Gold")) {
								int[] goldMedals = player.getGoldMedals();
								matches = matches && (goldMedals.length > 0);
							}
                            // ..
							if (categoryName.equals("Bronze")) {
								int[] goldMedals = player.getBronzeMedals();
								matches = matches && (goldMedals.length > 0);
							}
						}
						return matches;
					}
				).collect(Collectors.toList());
			}
		} // ..
    }
}
```
--
### Players (prefiltered by page creator to Spain) can now be filtered by end user based on Olympics and Medals
<img 
  src="./images/players-spain-tokyo-silver-bronze-1.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Players (prefiltered by page creator to Spain) filtered by end user to Tokyo, who have won Bronze and Silver medals
<img 
  src="./images/players-spain-tokyo-silver-bronze-2.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering (2): <mark>Filtering<mark>
* **Pre filter** the items returned by collection providers ✔
* Use Collection Filter fragments to **allow end users to filter** collection items themselves ✔
* Make an existing Collection Provider **compatible with a filter** (e.g: Keywords and Categories filters) ✔
* <mark>Create a **new type of filter** and make a Collection Provider compatible with it</mark>
--
### Requirement 
Filter Players based on a range of total medals won

### Solution
Create new Filter that allows setting a minimum and maximum value for number of medals and filter players returned by  PlayerCollectionProvider based on values selected  
--
- Create class implementing InfoFilter
- Create class implementing InfoFilterProvider
- Create UI for filter using technology of your liking
- Connect UI with backend by calling `getCollectionFilterValue` and `setCollectionFilterValue`
- Create NumericRangeFragmentCollectionFilter implementing FragmentCollectionFilter 
- Add new filter to list of supported filters in CollectionProvider 
- Return items that match filter's values in CollectionProvider
--
### Create NumericRangeInfoFilter class implementing InfoFilter
```java
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
```
--
### Create NumericRangeInfoFilterProvider
```java
@Component(immediate = true, service = InfoFilterProvider.class)
public class NumericRangeInfoFilterProvider 
        implements InfoFilterProvider<NumericRangeInfoFilter> {
	@Override
	public NumericRangeInfoFilter create(Map<String, String[]> values) {
		NumericRangeInfoFilter numericRangeInfoFilter = new NumericRangeInfoFilter();
		for (Map.Entry<String, String[]> entry : values.entrySet()) {
			if (StringUtil.startsWith(
					entry.getKey(), NumericRangeInfoFilter.FILTER_TYPE_NAME + "_")) {
				String[] value = entry.getValue();
				if ((value != null) && (value.length > 0)) {
					String[] valueParts = value[0].split("-");
					if (valueParts.length > 0) {
						numericRangeInfoFilter.setMin(
                        	GetterUtil.getInteger(valueParts[0], -1));
					} else {
                        numericRangeInfoFilter.setMin(-1);
                    }
                    // ..
				}
				return numericRangeInfoFilter;
			}
		}
		return null;
	}
}
```
--
### Create UI for filter using technology of your liking: page.jsp (1)
```html
<%
FragmentRendererContext fragmentRendererContext = 
    (FragmentRendererContext)request.getAttribute(FragmentRendererContext.class.getName());
FragmentEntryLink fragmentEntryLink = fragmentRendererContext.getFragmentEntryLink();
String fragmentEntryLinkNamespace = 
    fragmentEntryLink.getNamespace() + fragmentEntryLink.getFragmentEntryLinkId();
%>

<form id="<%= fragmentEntryLinkNamespace %>form">
	<label>
		<span><%= LanguageUtil.get(request, "number-of-medals") %></span>
	</label>
	<br />
	<label>
		<span><%= LanguageUtil.get(request, "minimum") %></span>
		<input id="min" max="28" min="0" name="min" type="number" />
	</label>
	<label>
		<span><%= LanguageUtil.get(request, "maximum") %></span>
		<input id="max" max="28" min="0" name="max" type="number" />
	</label>
</form>
```
--
### Create UI for filter using technology of your liking: page.jsp (2)
```html
<liferay-frontend:component
	context='<%=
		HashMapBuilder.<String, Object>put(
			"fragmentEntryLinkId", fragmentEntryLink.getFragmentEntryLinkId()
		).put(
			"fragmentEntryLinkNamespace", fragmentEntryLinkNamespace
		).build()
	%>'
	module="NumericRangeFilter"
/>
```
--
### Fill UI with value from URL value calling `getCollectionFilterValue`
```js
import {getCollectionFilterValue, setCollectionFilterValue,} 
  from '@liferay/fragment-renderer-collection-filter-impl';
export default function NumericRangeFilter(
    {fragmentEntryLinkId, fragmentEntryLinkNamespace,}) {
	const form = document.getElementById(`${fragmentEntryLinkNamespace}form`);
	const minInput = form && form.elements['min'];
	const maxInput = form && form.elements['max'];
	if (!form || (!minInput && !maxInput)) {return;}
	const urlValue = getCollectionFilterValue('numericRange', fragmentEntryLinkId);
	if (urlValue !== null) {
		const parts = urlValue.split('-');
		minInput.value = isNaN(parts[0]) ? '' : parts[0];
		maxInput.value = isNaN(parts[1]) ? '' : parts[1];
	}
    // ..
}
```
--
### Get value from UI and set it on URL by calling `setCollectionFilterValue`
```js
import {getCollectionFilterValue, setCollectionFilterValue,} 
  from '@liferay/fragment-renderer-collection-filter-impl';
export default function NumericRangeFilter(
    {fragmentEntryLinkId, fragmentEntryLinkNamespace,}) {
	const form = document.getElementById(`${fragmentEntryLinkNamespace}form`);
	const minInput = form && form.elements['min'];
	const maxInput = form && form.elements['max'];
    // ..
	const handleChange = () => {
		setCollectionFilterValue(
            'numericRange', fragmentEntryLinkId, `${minInput.value}-${maxInput.value}`);};
	form.addEventListener('change', handleChange);
	return {
		dispose() {form.removeEventListener('change', handleChange);},
	};
}
```
--
#### NumericRangeFragmentCollectionFilter
```java
@Component
public class NumericRangeFragmentCollectionFilter implements FragmentCollectionFilter {
	@Override
	public String getFilterKey() {
		return "numericRange";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, "numeric-range");
	}

	@Override
	public void render(FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		try {
			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher("/page.jsp");

			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {}
	}

	@Reference(target = "(osgi.web.symbolicname=com.liferay.player.web)")
	private ServletContext _servletContext;
}
```
--
### Add NumericRangeInfoFilter to the list of supported InfoFilters
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>,
			   FilteredInfoCollectionProvider<Player> {
 
    // ..

	@Override
	public List<InfoFilter> getSupportedInfoFilters() {
		List<InfoFilter> infoFilters = new ArrayList<>();

		infoFilters.add(new CategoriesInfoFilter());
		infoFilters.add(new KeywordsInfoFilter());
		infoFilters.add(new NumericRangeInfoFilter());

		return infoFilters;
	}
}
```
--
### Add Collection Filter
<img 
  src="./images/add-collection-filter-numeric-range.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Players as target collection
<img 
  src="./images/add-collection-filter-numeric-range-target-collection.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select Numeric Range as filter
<img 
  src="./images/add-collection-filter-type-numeric-range.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Return items that match filter's values in CollectionProvider
```java
@Component(immediate = true, service = InfoCollectionProvider.class)
public class PlayerCollectionProvider
	implements ConfigurableInfoCollectionProvider<Player>,
			   FilteredInfoCollectionProvider<Player> {
	@Override
	public InfoPage<Player> getCollectionInfoPage(CollectionQuery collectionQuery) {// ..
		Optional<NumericRangeInfoFilter> optional =
			collectionQuery.getInfoFilterOptional(NumericRangeInfoFilter.class);
		if (optional.isPresent()) {
			NumericRangeInfoFilter numericRangeInfoFilter = optional.get();
			int min = numericRangeInfoFilter.getMin();
			int max = numericRangeInfoFilter.getMax();
			if ((min != -1) || (max != -1)) {
				Stream<Player> stream = filteredPlayers.stream();
				filteredPlayers = stream.filter(player -> {
                    boolean matches = true;
                    int totalMedals = player.getGoldMedals().length +
                        player.getSilverMedals().length + player.getBronzeMedals().length;
                    if (min != -1) {matches = matches && (totalMedals >= min);}
                    if (max != -1) {matches = matches && (totalMedals <= max);}
                    return matches;
                  }).collect(Collectors.toList());
			}
		}// ..
	}
}
```
--
### Players (prefiltered by page creator to Spain) filtered by end user to Tokyo, who have won Bronze and Silver medals, and at least 3 medals and at most 4 medals 
<img 
  src="./images/players-spain-tokyo-silver-bronze-min-3-max-4.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### What we will be covering (3): <mark>Related Items</mark>
* <mark>Display **items related** to one specific item using Related Items Collection Providers</mark>
--
### Requirement 
Display the logos of the Olympics where each player participated

### Solution
Create a Related Items Collection Provider that given a Player returns list of Olympics he participated 
--
### Make Olympics mappable (analogous to Player)
- Create OlympicsInfoFields class
- Create OlympicsInfoItemFormProvider class
- Create OlympicsInfoItemFieldValuesProvider class
--
#### OlympicsWherePlayerParticipatedCollectionProvider
```java
@Component(immediate = true, service = RelatedInfoItemCollectionProvider.class)
public class OlympicsWherePlayerParticipatedCollectionProvider
	implements RelatedInfoItemCollectionProvider<Player, Olympics> {
	
	@Override
	public InfoPage<Olympics> getCollectionInfoPage(CollectionQuery collectionQuery) {
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
		return LanguageUtil.get(locale, "olympics-where-a-player-participated");
	}
}
```
--
### Add Collection Display under existing Collection Item
<img 
  src="./images/related-collection-add-collection-display.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select collection (1)
<img 
  src="./images/related-collection-collection-display-select-collection.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select collection (2)
<img 
  src="./images/related-collection-collection-display-select-collection-2.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Select 3 Columns layout
<img 
  src="./images/related-collection-collection-display-layout-3-columns.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Add image and set padding
<img 
  src="./images/related-collection-collection-display-image-padding-2.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Map image to Olympics image field
<img 
  src="./images/related-collection-collection-display-image-mapping.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Logos of Olympics in which players participated are now displayed
<img 
  src="./images/related-collection-olympics-view.png" 
  alt="Player Collection Provider" 
  style="max-height:470px;max-width:800px;height:auto;width:auto;" />
--
#### Related Items Collection Providers can be used:
- Within a collection item of a Collection Display fragment
- Within a Display Page Template
--
### Recap:
* **Pagination** ✔
* **Filtering** ✔
* **Related Items** ✔
--
### Thank you!
<img 
  src="./images/echo-team-members.png" 
  alt="Thank you!" 
  style="margin-left:auto;margin-right:auto;display:block;max-height:470px;max-width:800px;height:auto;width:auto;" />
--
### Image sources
- https://olympics.com/
- https://www.themoviedb.org/movie/114887-the-dream-team
- https://www.fiba.basketball/eurobasket/2022/news/fiba-eurobasket-dream-teams-spain
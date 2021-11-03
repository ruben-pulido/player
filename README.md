# Liferay Player Collection Provider Demo

This is the accompanying code for the Liferay dev24 session [Make Collections shine with the Collection Display fragment](https://liferay.dev/twentyfour#Make%20Collections%20shine%20with%20the%20Collection%20Display%20fragment).

> Liferay 7.3 introduced functionality to display Collections on Content Pages through Collection Display Fragments.
>
> Liferay 7.4 has brought along multiple improvements around displaying Collections in Content Pages.
>
> In this session you will learn how easy it is now not only to display your collection items using the Collection Display Fragment but also how to:
> - Enable pagination and choose between different pagination types
> - Pre filter the items returned by collection providers
> - Use Collection Filter fragments to allow end users to filter collection items themselves
> - Display items related to one specific item using Related Items Collection Providers
>
> This session is targeted at developers using Liferay who want to be knowledgeable about displaying collections in Liferay.

The slides from the session can be found [here](https://ruben-pulido.github.io/player/index.html)

To deploy it, specify the location of your liferay-portal source code and the bundles directory in gradle.properties:

liferay.workspace.home.dir=[location of bundles directory]

liferay.source.home.dir=[location of the source code]

Once deployed you will be able to select the collection provider for the collection display fragment.





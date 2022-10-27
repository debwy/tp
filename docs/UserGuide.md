---
layout: page
title: User Guide
---

_NUSEatWhere is a **Command Line (CLI) application** which helps you search for the available 
food options in NUS and thus make an informed decision on where to eat._

## Table of Contents
* Introduction
* Table of Contents
* [Quick Start](#quick-start)
* [Features](#features)
  * Help
  * List
  * Find / FindTag / FindLocation / FindCuisine
  * Tag / Untag
  * Add / Delete
* [FAQ](#faq)
* [Command Summary](#command-summary)


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Install **Java 11** from [here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html).

1. Download the latest `NUSEatWhere.jar` from [here](https://github.com/AY2223S1-CS2103T-W11-1/tp/releases).

1. Move the file to your intended **home folder** for the NUSEatWhere application.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. <br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`help`** : Lists all commands.

   * **`list`**`: Lists all eateries.

1. Refer to [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add -n NAME`, `NAME` is a parameter which can be used as `add -n Pasta Express`.<br><br>

* Items in square brackets are optional.<br>
  e.g `-n NAME [-p PHONE]` can be used as `-n Pasta Shop -p 87654321` or as `-n Pasta Shop`.<br><br>

* Items with `…`​ after them can be used one or more times.<br>
  e.g. `-t TAGNAME…​` can be used as `-t Foodcourt`, `-t Tea -t Coffee` etc.  <br>
Note:  ` ` (i.e. 0 times) is only allowed if there are square brackets, e.g. `[-t TAGNAME]`<br><br>

* Prefixed parameters _(e.g. -n, -p, -t, etc.)_ can be in any order.<br>
  e.g. if the command specifies `-n NAME -t TAG`, `-t TAG -n NAME` is also acceptable.<br><br>

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `-n nameA -n nameB`, only `-n nameB` will be taken.<br><br>

* Extraneous parameters for commands that do not take in parameters (such as `help`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

<br>

</div>

### Help command : `help`

_Lists out all the available commands & their functions as a pop-out window. <br>
Also contains a link to this User Guide._

**Format:** `help`

<br>

### Listing all eateries : `list`

_Lists out all eateries in NUSEatWhere database. <br>
This can be used after a `find` command to return the list to its default state (all eateries displayed)._

**Format:** `list [-h]`

**Arguments:**<br>
`-h`: displays help message (specific to list)

<br>

### Finding eateries by name : `find`

_Search for eateries with names that match the keywords._

**Format:** `find NAME…​ [-r NUMBER] [-h]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can leave NAME empty if you are using the wildcard (-r) feature
</div>

**Arguments:** </br>
`NAME` : returns eateries that match the keyword(s)<br>
`NUMBER`: randomly generates indicated number of eateries (more than 0)<br>
`-h`: displays help message (specific to find)
<br>

Note:
* The search is case-insensitive. e.g `koi` will match `KOI`
* Eateries matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `find coffee cafe` will return eateries with either `coffee` or `cafe` in their name.

Example: `find pasta -r 3`

Below is an example of what the list would look like when using the wildcard `-r` command.
The command used is shown on the command line. <br>
Note how only 2 random eateries with "mala" in their names are shown. 
![Ui](images/user-guide/UgFindWildcard.png)


<br>

### Finding eateries by tag : `findTag`

_Search for eateries that match the specified tag(s)._

**Format:** `find TAGNAME…​ [-h]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can search for any number of tags by typing them all after findTag
</div>

**Arguments:** </br>
`TAGNAME`: returns eateries that match the keyword(s)<br>
`-h`: displays help message (specific to findTag)
<br>

Note:
* The search is case-insensitive. e.g `foodcourt` will match `Foodcourt`
* Eateries matching at least one tag will be returned (i.e. `OR` search).
  e.g. `findTag foodcourt cafe` will return eateries with either tag.

Example: `findTag restaurant`

<br>

### Finding eateries by location : `findLocation`

_Search for eateries that match the specified location(s)._

**Format:** `findLocation LOCATIONNAME…​ [-h]`

**Arguments:** <br>
`LOCATIONNAME`: returns eateries that match the keyword(s)<br>
`-h`: displays help message (specific to findLocation)<br>

Note:
* The search is case-insensitive. e.g `arts` will match `Arts`
* Eateries matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `findLocation engineering science` will eateries stalls at either location.

Example: `findLocation utown`

<br>

### Finding eateries by cuisine : `findCuisine`

_Search for eateries that match the specified cuisine(s)._

**Format:** `findCuisine CUISINENAME…​ [-h]`

**Arguments:** </br>
`CUISINENAME`: returns eateries that match the keyword(s)<br>
`-h`: displays help message (specific to findCuisine)
<br>

Note:
* The search is case-insensitive. e.g `korean` will match `Korean`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `findCuisine Western Japanese` will return eateries that sell either cuisine.

Example: `findCuisine chinese`

<br>

### Add tag to eatery : `tag`

_Create custom tag(s) for an eatery to facilitate searching._

**Format:** `tag ID -t TAGNAME…​ [-h]`

**Arguments:**<br>
`ID`: ID of eatery to place tag on<br>
`TAGNAME`: name of tag to assign to eatery<br>
`-h`: displays help message (specific to tag)<br><br>
Example: `tag 1 -t coffee -t tea`

<br>

### Remove tag from eatery : `untag`

_Remove custom tag(s) from eatery._

**Format:** `untag ID -t TAGNAME…​ [-h]`

**Arguments:**<br>
`ID`: ID of eatery to remove tag from<br>
`TAGNAME`: name of tag to remove from eatery<br>
`-h`: displays help message (specific to untag)<br><br>
Example: `untag 1 -t coffee -t tea`

Below is a comparison between when the store at ID 50 is `tag[ged]` _(left)_ then `untag[ged]` _(right)_.
The commands used are shown on the command line. <br>
Note how the blue "cafe" tag on store 50 disappears after the untag command.
![Ui](images/user-guide/UgTagUntagComparison.png)
<br><br>

### Add eatery: `add`

_Adds a new eatery to NUSEatWhere's database. Eatery will be added to the end of the 
current list <br>
(i.e. if the current list pre-addition has 5 eateries, the newly added eatery will be of index 6)._

**Format:** `add -n NAME -l LOCATION -c CUSINE [-p PHONE] [-t TAG]…​ [-h]`

**Arguments:**<br>
`NAME`: name of the eatery to be added<br>
`LOCATION`: location of the eatery<br>
`CUSINE`: cuisine type of the eatery<br>
`PHONE`: phone number of the eatery <br>
`TAG`: extra tags to add to the eatery<br>
`-h`: displays help message (specific to add)<br><br>
Example: `add -n KOI -l Central Square -c Drinks`

<br>

### Delete eatery : `delete`

_Deletes an eatery from NUSEatWhere's database_

**Format:** `delete ID [-h]`

**Arguments:**<br>
`ID`: ID of eatery to remove from NUSEatWhere<br>
`-h`: displays help message (specific to delete)<br><br>
Example: `delete 3`

Below is a comparison between when the store at ID 70 is `add[ed]` _(left)_ then `delet[ed]` _(right)_.
The commands used are shown on the command line. <br>
Note how the eatery at index 70 disappears after the delete command.
![Ui](images/user-guide/UgAddDeleteComparison.png)
<br><br>

### Favourite/Unfavourite `[coming in later versions]`

_... Details coming soon ..._

<br>

### Manage ratings & personal comments `[coming in later versions]`

_... Details coming soon ..._

<br>

### Edit tag `[coming in later versions]`

_... Details coming soon ..._

<br>

### Delete tag `[coming in later versions]`

_... Details coming soon ..._

<br>

### List all tags `[coming in later versions]`

_... Details coming soon ..._

<br>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains 
the data of your previous NUSEatWhere home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action           | Format, Examples                                               |
|:-----------------|:---------------------------------------------------------------|
| **Help**         | `help`                                                         |
| **List**         | `list [-h]`                                                    |
| **Find**         | `find NAME…​ [-r NUMBER] [-h]`                                 |
| **FindTag**      | `findTag TAGNAME…​ [-h]`                                       |
| **FindLocation** | `findLocation LOCATIONNAME…​ [-h]`                             |
| **FindCuisine**  | `findCuisine CUISINENAME…​ [-h]`                               |
| **Tag**          | `tag ID -t TAGNAME…​ [-h]`                                     |
| **Untag**        | `untag ID -t TAGNAME…​ [-h]`                                   |
| **Add**          | `add -n NAME -l LOCATION -c CUSINE [-p PHONE] [-t TAG…​] [-h]` |
| **Delete**       | `delete ID [-h]`                                               |

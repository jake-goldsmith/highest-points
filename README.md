# High points in an area map generator

Script for generating a map of the highest points in each area given a set of polygons and spot heights over the same region. 
Tested on the Ordnance Survey OpenData for the counties of Great Britain.

## Example output:

![Map of the south of Great Britain showing the highest point in each county border](https://github.com/jake-goldsmith/highest-points/blob/mainline/output.png "Example output")

Contains OS data © Crown copyright and database right 2022. This information is licensed under the [Open Government License v3.0](https://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/).

## Tools used:

* [GeoTools - Java Geospatial library](https://www.geotools.org/)

## References:

* [GeoTools tutorials](https://docs.geotools.org/stable/userguide/tutorial/)
* [GeoTools javadocs](https://docs.geotools.org/stable/javadocs/)
* [Ordnance Survey Open Data Products](https://www.ordnancesurvey.co.uk/business-government/products?Licence%20or%20agreement=0%2F154%2F168%2F171&Product%20type=0%2F154%2F173%2F175)
* [Ian's random blog - GeoTools Contouring 24/12/2020](https://blog.ianturton.com/geotools/2020/12/24/contours.html)
* Results validated against: [Walking Englishman - County tops of England](https://www.walkingenglishman.com/lists/countytopsengland/master.html)

## Known Issues:

* Some of the highest points in counties are missed since we are using the spot heights provided which don't cover all locations.
    * Next steps: either use the contour data or grid coverage of heights to find the missing high points.
* Carnarvonshire has multiple high points on each of its islands.
    * Next steps: handle disjoint counties correctly using a unique county identifier to record their max height point.
* Some labels are not displayed on the map if they are too close to other high points. Generally it is possible to zoom in, and they will become visible.

## How to set up the Ordnance Survey Dataset for Great Britain?

1. Download county boundaries:
    * https://osdatahub.os.uk/downloads/open/BoundaryLine - Download the ESRI® Shapefile option.
    * Extract the zip file and save the directory named `Supplementary_Historical` in the `data` directory. It contains a shape file containing the boundaries of the counties.
2. Download height data:
    * https://osdatahub.os.uk/downloads/open/Terrain50 - Download the ESRI® Shapefile (contours) option.
    * Extract the zip file and save the directory named `terr_50_cesh_gb` in the `data` directory. It contains shape files containing the spot heights and contours recorded by Ordnance Survey.
    * Extract the nested zip directories. On windows you can run the following bat script from the `terr_50_cesh_gb` directory, and it will recursively use 7-Zip to extract the nested directories.

```bat
FOR /D /r %%F in ("*") DO (
    pushd %CD%
    cd %%F
        FOR %%X in (*.rar *.zip) DO (
            "C:\Program Files\7-zip\7z.exe" x "%%X" -o*
		   del "%%X"
        )
    popd
)
```

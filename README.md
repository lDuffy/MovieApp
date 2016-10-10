## Synopsis

This is a test application which pulls information about popular movies from the online movie database: https://www.themoviedb.org/

The application uses MVP architecture and dependency injection throughout to separate business/view logic and make for easier testing.

Routing is organized using extendable Launcher class.

When the application starts it checks for a global configuration file. If it is not found it fetches the file and the genre list before making the movie list request.

It uses lazy loading on the paginated feed which loads more items when the user scrolls to the bottom of the list.

The list shows the movie image, release date and genre.

It also has a simple sorting feature to sort the downloaded list by popularity.

When th user click on a list item, the corresponding list object  is passed to a detail fragment for further viewing.

## Libraries

* Retrofit 2 : networking.
* RxJava : reactive programming.
* Mockito/Junit : testing.
* Picasso : image loading, caching.
* ButterKnife : view injection.
* Dagger2 : dependency injection.
* Gson : json serialization.
* Infinite Scroll : feed lazy loading.


## Installation

Clone project. Open in Android Studio. build.

## API Reference

Configuration api : Pulls global configuration file which contains relevant image sizes, image url path etc.
Genres api: Pulls list of genres which is needed for displaying the genre of a movie.
Movies api: pulls a paginated list of popular movies.

## Tests

The application uses Mockito and Junit4 for unit testing

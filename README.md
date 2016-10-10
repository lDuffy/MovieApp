## Synopsis

This is a test application which pulls information on popular movies from the online movie database https://www.themoviedb.org/

The application uses MVP architecture and dependency injection throughout to separate business/view logic and make for easier testing.

Routing is organized using extendable Launcher class.

When the application starts i check for a global configuration file. If it is not found I fetch the file and the genre list before making the movie list request.

I implemented lazy loading of the paginated list which loads more items when the user scrolls to the bottom of the list.

The list shows the movie image, release date and genre.

I also implemented a simple sorting feature to sort the downloaded list by popularity.

When th user click on a list item I pass the corresponding list object to a detail fragment for further viewing.

## Libraries

Retrofit 2 : networking.
RxJava : reactive programming.
Mockito/Junit : testing.
Picasso : image loading, caching.
ButterKnife : view injection.
Dagger2 : dependency injection.
Gson : json serialization.
Infinite Scroll : feed lazy loading.


## Installation

Clone project. Open in Android Studio. build.

## API Reference

Configuration api : Pulls global configuration file which contains relevant image sizes, image url path etc.
Genres api: Pulls list of genres which is needed for displaying the genre of a movie.
Movies api: pulls a paginated list of popular movies.

## Tests

The application uses Mockito and Junit4 for unit testing

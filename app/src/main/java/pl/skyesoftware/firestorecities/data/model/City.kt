package pl.skyesoftware.firestorecities.data.model

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ServerTimestamp
import pl.skyesoftware.firestorecities.viper.detail.CityDetailsObject

/**
 * Created by norbertbanaszek on 17.10.2017.
 */

class City(var id: String,
           var name: String,
           var state: String,
           var country: String,
           var imageUrl: String,
           var population: Long,
           var timestamp: Long) : ModelType {

    override fun getModelType(): Int {
        return ModelType.Types.CITY
    }

    constructor() : this("", "", "", "", "", 0L, 0L)

    companion object {
        fun getMockedCitiesList() : MutableList<City> {
            val citiesList = mutableListOf<City>()
            citiesList.add(City(
                    id = "NewYork_" + System.currentTimeMillis(),
                    name = "New York",
                    state = "New York",
                    country = "USA",
                    imageUrl = "https://images.pexels.com/photos/421927/pexels-photo-421927.jpeg?w=940&h=650&dpr=2&auto=compress&cs=tinysrgb",
                    population = 8537673,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "LosAngeles_" + System.currentTimeMillis(),
                    name = "Los Angeles",
                    state = "California",
                    country = "USA",
                    imageUrl = "https://images.pexels.com/photos/462219/pexels-photo-462219.jpeg?w=940&h=650&dpr=2&auto=compress&cs=tinysrgb",
                    population = 3976322,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "Chicago_" + System.currentTimeMillis(),
                    name = "Chicago",
                    state = "Illinois",
                    country = "USA",
                    imageUrl = "https://images.pexels.com/photos/167200/pexels-photo-167200.jpeg?w=940&h=650&dpr=2&auto=compress&cs=tinysrgb",
                    population = 2704958,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "Houston_" + System.currentTimeMillis(),
                    name = "Houston",
                    state = "Texas",
                    country = "USA",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/44/Panoramic_Houston_skyline.jpg",
                    population = 2303482,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "Phoenix_" + System.currentTimeMillis(),
                    name = "Phoenix",
                    state = "Arizona",
                    country = "USA",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Downtown_Phoenix_Aerial_Looking_Northeast.jpg/220px-Downtown_Phoenix_Aerial_Looking_Northeast.jpg",
                    population = 1615017,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "Philadelphia_" + System.currentTimeMillis(),
                    name = "Philadelphia",
                    state = "Pennsylvania",
                    country = "USA",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Philadelphia_from_South_Street_Bridge_July_2016_panorama_1.jpg/1600px-Philadelphia_from_South_Street_Bridge_July_2016_panorama_1.jpg",
                    population = 1567872,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "SanAntonio_" + System.currentTimeMillis(),
                    name = "San Antonio",
                    state = "Texas",
                    country = "USA",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/48/Downtown-san-antonio.jpeg",
                    population = 1492510,
                    timestamp = System.currentTimeMillis()))
            citiesList.add(City(
                    id = "SanDiego_" + System.currentTimeMillis(),
                    name = "San Diego",
                    state = "California",
                    country = "USA",
                    imageUrl = "https://www.sandiego.gov/sites/default/files/styles/hero/public/hero_20160525_131743.jpg?itok=Ia00SALR",
                    population = 1406630,
                    timestamp = System.currentTimeMillis()))
            return citiesList
        }

        fun createCityDetailsObjectFromCityForPosition(city: City, position: Int) : CityDetailsObject {
            return CityDetailsObject(
                    name = city.name,
                    nameTransition = "nameTransition_" + position,
                    state = city.state,
                    stateTransition = "stateTransition_" + position,
                    country = city.country,
                    countryTransition = "countryTransition_" + position,
                    population = city.population,
                    populationTransition = "populationTransition_" + position,
                    imageUrl = city.imageUrl,
                    imageTransition = "imageTransition_" + position
            )
        }
    }

}
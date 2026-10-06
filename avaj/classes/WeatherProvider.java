package avaj.classes;

public class	WeatherProvider
{
	enum WeatherType
	{
		SUN,
		RAIN,
		FOG,
		SNOW
	}


	private static WeatherProvider	instance = null;

	private WeatherProvider() {}

	public static WeatherProvider	getInstance()
	{
		if (instance == null)
			instance = new WeatherProvider();

		return (instance);
	}


    WeatherType getCurrentWeather(Coordinate coords)
    {
        
    }
}
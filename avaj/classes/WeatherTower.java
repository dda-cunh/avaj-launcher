package avaj.classes;

import java.time.Instant;
import avaj.classes.WeatherProvider.WeatherType;

public class	WeatherTower extends Tower
{


	WeatherType	getWeather(Coordinate coords)
	{
		long epochSeconds = Instant.now().getEpochSecond();

		if (epochSeconds % 2 == 0)
		{
			
		}

		return (WeatherType.SUN);
	}

}

package src.classes;

import src.classes.AFlyable;

public class	AircraftFactory
{
	private static AircraftFactory	instance = null;

	private AircraftFactory() {}

	public static AircraftFactory	getInstance()
	{
		if (instance == null)
			instance = new AircraftFactory();

		return (instance);
	}

	public AFlyable	newAircraft(String type, String name, Coordinate coords)
	{
		switch (type.toLowerCase())
		{
			
		}
	}
}

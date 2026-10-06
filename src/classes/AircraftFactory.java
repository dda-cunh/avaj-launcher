package src.classes;

import src.classes.AFlyable.AircraftType;

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


	public AFlyable	newAircraft(String line)
	{
		String[]	parts = line.split(" ");
		if (parts.length != 5)
			return (null);

		AircraftType	type = AircraftType.fromString(parts[0]);
		String			name = parts[1];
		int				longitude = Integer.parseInt(parts[2]);
		int				latitude = Integer.parseInt(parts[3]);
		int				height = Integer.parseInt(parts[4]);

		return (newAircraft(type, name, new Coordinate(longitude, latitude, height)));
	}

	private AFlyable	newAircraft(AircraftType type, String name, Coordinate coords)
	{
		switch (type)
		{
			case HELICOPTER:
				return (new Helicopter(name, coords));
			case JETPLANE:
				return (new JetPlane(name, coords));
			case BALLOON:
				return (new Balloon(name, coords));
			default:
				return (null);
		}
	}
}

package src.classes;

import src.classes.AircraftFactory.AircraftType;

public class Helicopter extends AFlyable
{
	public Helicopter(String name, Coordinate coords)
	{
		super(AircraftType.HELICOPTER,
				name,
				coords,
				new Coordinate(0, 10, 2),
				new Coordinate(0, 5, 0),
				new Coordinate(0, 1, 0),
				new Coordinate(0, 0, -12)
			);
	}
}

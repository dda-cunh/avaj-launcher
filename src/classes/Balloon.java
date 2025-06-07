package src.classes;

import src.classes.AircraftFactory.AircraftType;

public class Balloon extends AFlyable
{
	public Balloon(String name, Coordinate coords)
	{
		super(AircraftType.BALLOON,
				name,
				coords,
				new Coordinate(0, 2, 4),
				new Coordinate(0, 0, -5),
				new Coordinate(0, 0, -3),
				new Coordinate(0, 0, -15)
			);
	}
}

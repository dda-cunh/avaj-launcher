package src.classes;

import src.classes.AircraftFactory.AircraftType;

public class JetPlane extends AFlyable
{
	public JetPlane(String name, Coordinate coords)
	{
		super(AircraftType.JETPLANE,
				name,
				coords,
				new Coordinate(10, 0, 2),
				new Coordinate(5, 0, 0),
				new Coordinate(1, 0, 0),
				new Coordinate(0, 0, -7)
			);
	}
}

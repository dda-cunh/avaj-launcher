package avaj.classes;

import avaj.classes.WeatherProvider.WeatherType;

public abstract class	AFlyable
{
	enum AircraftType
	{
		HELICOPTER,
		JETPLANE,
		BALLOON;

		public static AircraftType fromString(String value) {
			return AircraftType.valueOf(value.toUpperCase());
		}
	}

	private final Coordinate	SUN_EFFECT;
	private final Coordinate	RAIN_EFFECT;
	private final Coordinate	FOG_EFFECT;
	private final Coordinate	SNOW_EFFECT;

	private AircraftType		_type;
    private Coordinate			_coords;
	private String				_name;
	private String				_uuid;

	protected AFlyable(AircraftType type, String name, Coordinate coords,
						Coordinate sunEffect, Coordinate rainEffect,
						Coordinate fogEffect, Coordinate snowEffect)
	{
		this._name = name;
		this._coords = coords;
		this._type = type;
		this._uuid = inc.Utils.genUUIDStr();
		this.SUN_EFFECT = sunEffect;
		this.RAIN_EFFECT = rainEffect;
		this.FOG_EFFECT = fogEffect;
		this.SNOW_EFFECT = snowEffect;
	}

	public void	registerTower(Tower weatherTower)
	{
		weatherTower.register(this);
	}

	public AircraftType getType()
	{
		return (_type);
	}

	public String getName()
	{
		return (_name);
	}

	public Coordinate getCoords()
	{
		return (_coords);
	}

	public String getUUID()
	{
		return (_uuid);
	}

	public void updateConditions(WeatherType weather)
	{
		switch (weather)
		{
			case SUN:
				updateCoords(SUN_EFFECT);
				break;
			case RAIN:
				updateCoords(RAIN_EFFECT);
				break;
			case FOG:
				updateCoords(FOG_EFFECT);
				break;
			case SNOW:
				updateCoords(SNOW_EFFECT);
				break;
		}
	}

	public void updateCoords(Coordinate diffVec)
	{
		_coords.updateX(diffVec.getX());
		_coords.updateY(diffVec.getY());
		_coords.updateZ(diffVec.getZ());
	}
}

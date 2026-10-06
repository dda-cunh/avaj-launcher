package avaj.classes;

import java.util.Map;

import avaj.classes.WeatherTower.WeatherType;

public abstract class Tower
{
	Map<String, AFlyable> _observers;

	void register(AFlyable flyable)
	{
		if (flyable == null)
			return ;
		if (_observers.containsKey(flyable.getUUID()))
			return ;

		_observers.put(flyable.getUUID(), flyable);
	}

	void unregister(AFlyable flyable)
	{
		if (flyable == null)
			return ;
		if (!_observers.containsKey(flyable.getUUID()))
			return ;

		_observers.remove(flyable.getUUID());
	}

	void conditionChanged(WeatherType weather)
	{
		if (weather == null)
			return ;

		for (Map.Entry<String, AFlyable> entry : _observers.entrySet())
		{
			AFlyable flyable = entry.getValue();
			if (flyable != null)
				flyable.updateConditions(weather);
		}
	}
}

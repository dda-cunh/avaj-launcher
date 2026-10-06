package avaj.classes;

//Latitude x
//Longitude y
//Height z

public final class	Coordinate
{
	private int	_x;
	private int	_y;
	private int	_z;

	public	Coordinate(int x, int y, int z)
	{
		this._x = x;
		this._y = y;
		this._z = z;
	}

	public boolean isValid()
	{
		return (_x >= 0 && _y >= 0 && _z >= 0 && _z <= 100);
	}

	public int	getX()
	{
		return _x;
	}

	public int	getY()
	{
		return _y;
	}

	public int	getZ()
	{
		return _z;
	}

	public void	updateX(int diff)
	{
		_x += diff;

		if (_x < 0)
			_x = 0;
	}

	public void	updateY(int diff)
	{
		_y += diff;

		if (_y < 0)
			_y = 0;
	}

	public void	updateZ(int diff)
	{
		_z += diff;

		if (_z < 0)
			_z = 0;
		else if (_z > 100)
			_z = 100;
	}
}

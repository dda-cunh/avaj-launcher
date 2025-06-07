package inc;

import java.io.Serializable;

public abstract class ADBType implements Serializable
{
	private static final long	serialVersionUID = 1L;

	protected ADBType()
	{}

	public abstract int hashCode();	
}

package inc;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.UUID;

public class Utils
{
	public static final OutputStream	STD_OUT = System.out;
	public static final OutputStream	STD_ERR = System.err;
	public static final InputStream		STD_IN = System.in;
	public static final String			BREAKLINE = System.lineSeparator();

	public static final String	genUUIDStr()
	{
		return (UUID.randomUUID().toString());
	}

	public static final InputStream	openFileRead(String path)
	{
		try
		{
			FileInputStream fileInputStream = new FileInputStream(path);
			return (fileInputStream);
		}
		catch (IOException ex)
		{
			return (null);
		}
	}

	public static final OutputStream	openFileWriteAppend(String path)
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(path, true);
			return (fileOutputStream);
		}
		catch (IOException ex)
		{
			return (null);
		}
	}

	public static final OutputStream	openFileWriteCreate(String path)
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(path, false);
			return (fileOutputStream);
		}
		catch (IOException ex)
		{
			return (null);
		}
	}

	/**
	*	Takes a variadic amount of objects and tries to print them to a given stream
	*	<p>
	*	Obejcts should have ToString() defined.
	*
	* @param	outStream OutputStream to write into
	* @param	output Variadic of type Object
	* @return	void
	* @see		System
	*/
	public static final void	putOut(OutputStream outStream, Object... output)
	{
		try
		{
			for	(Object out : output)
				outStream.write(out.toString().getBytes());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return ;
	}

	/**
	*	Reads next bytes from a given stream until eof or breakline
	*	<p>
	*	If second param is true it will stop reading at whitespaces.
	*
	* @param	inStream <code>InputStream</code> the stream to read from
	* @param	lineOnly <code>Boolean</code> A boolean denoting if reading
	*						should stop only at breakline
	* @return	<code>String</code> The read bytes converted into a string
	* @see		InputStream
	*/
	private static final String	getNextInput(InputStream inStream, Boolean lineOnly) throws IOException
	{
		StringBuilder	input;
		Character		c; 
		Boolean			lastWasWSpace;
		boolean			gotInput;
		int				read;

		input = new StringBuilder();
		lastWasWSpace = true;
		gotInput = false;
		try
		{
			while (!gotInput)
			{
				while ((read = inStream.read()) != -1)
				{
					c = (char) read;
					if (c == '\n')
						break ;
					if (c == '\r')
					{
						int next;
						if ((next = inStream.read()) != -1)
						{
							if (next == '\n')
								break ;
							else
							{
								input.append('\r').append((char) next);
								continue ;
							}
						}
					}
					if (!lineOnly && Character.isWhitespace(c))
					{
						if (!lastWasWSpace)
							break ;
						else
						{
							lastWasWSpace = true;
							continue ;
						}
					}
					lastWasWSpace = false;
					input.append(c);
				}
				if (input.length() > 0)
					gotInput = true;
			}
			return (input.toString());
		}
		catch (Exception ex)
		{
			throw new IOException(ex.getStackTrace().toString());
		}
	}

	/**
	*	Reads the next line of an Inputstream
	*	<p>
	*
	* @param	inStream <code>InputStream</code>	The stream to read from
	* @return	<code>String</code> The read line converted into a string
	* @see		InputStream
	*/
	public static final String	getNextLine(InputStream inStream)
	{
		try
		{
			return (getNextInput(inStream, true));
		}
		catch (Exception ex)
		{
			return (null);
		}
	}

	/**
	*	Attempts to parse a given primitive numeric data type from an InputStream
	*
	* @param	inStream	the InputStream
	* @param	type		the target type for parsing	
	* @param	prompt		A prompt to be given to the user
	* @return	int
	*/
	public static final <NumericType> NumericType	getNumeric(InputStream inStream,
	Class<NumericType> type, String prompt)
	{
		NumericType	n;
		boolean		parsed;
		
		n = null;
		parsed = false;
		while (!parsed)
		{
			putOut(System.out, prompt);
			try
			{
				String	input = getNextInput(inStream, false);
				if (type.equals(Double.class))
					n = type.cast(Double.valueOf(Double.parseDouble(input)));
				else if (type.equals(Float.class))
					n = type.cast(Float.valueOf(Float.parseFloat(input)));
				else if (type.equals(Integer.class))
					n = type.cast(Integer.valueOf(Integer.parseInt(input)));
				else if (type.equals(Short.class))
					n = type.cast(Short.valueOf(Short.parseShort(input)));
				else if (type.equals(Byte.class))
					n = type.cast(Byte.valueOf(Byte.parseByte(input)));
				else
				{
					putOut(System.err,"Not a primitive numeric type");
					return (null);
				}
				parsed = true;
			}
			catch (Exception ex)
			{
				putOut(System.err, ex.getMessage(), BREAKLINE);
			}
		}
		return (n);
	}
}

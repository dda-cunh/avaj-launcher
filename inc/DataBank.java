package inc;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import java.nio.file.Paths;

import java.util.LinkedList;
import java.util.HashMap;

class DuplicateEntryException extends Exception
{
	private static final long	serialVersionUID = 1L;

	public	DuplicateEntryException(String message)
	{
		super(message);
	}
}

class EntryNotFoundException extends Exception
{
	private static final long	serialVersionUID = 1L;

    public	EntryNotFoundException(String message)
	{
		super(message);
	}
}

public class DataBank<T extends ADBType>
{
	private HashMap<Integer, T>	data;
	private String				dataFileName;
	private String				dataFilePath;

	public LinkedList<T>	getData()
	{
		LinkedList<T>	lList;

		lList = new LinkedList<>(data.values());
		return (lList);
	}

	public DataBank(String name)
	{
		data = new HashMap<>();
		dataFileName = name;
		dataFilePath =  Paths.get(System.getProperty("user.home"),
							"/." + dataFileName + ".dat").toString();
		deserializeData();
		return ;
	}

	public DataBank(String name, String dirPath)
	{
		data = new HashMap<>();
		dataFileName = name;
		dataFilePath = Paths.get(dirPath, "/." + dataFileName + ".dat").toString();
		deserializeData();
		return ;
	}

	@SuppressWarnings("unchecked")
	public void deserializeData()
	{
		File file;

		file = new File(dataFilePath);
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
				return ;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
		{
			data = (HashMap<Integer, T>) ois.readObject();
		}
		catch (IOException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public void serializeData()
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath)))
		{
			oos.writeObject(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public T	get(int hash)
	{
		return (this.data.get(hash));
	}

	public void	add(T newEntry) throws DuplicateEntryException
	{
		Integer	newEntryHash;

		newEntryHash = newEntry.hashCode();
		if (this.data.containsKey(newEntryHash))
			throw new DuplicateEntryException("Entry already exists");
		else
		{
			this.data.put(newEntryHash, newEntry);
			serializeData();
		}
		return ;
	}

	public void	remove(int hash) throws EntryNotFoundException
	{
		if (this.data.containsKey(hash))
		{
			this.data.remove(hash);
			serializeData();
		}
		else
			throw new EntryNotFoundException("Entry not found");
		return;
	}

	public Integer	count()
	{
		return (this.data.size());
	}

	public String getDirectoryPath()
	{
		return (Paths.get(dataFilePath).getParent().toString());
	}
}

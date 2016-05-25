package org.p565.team6.beans;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.p565.team6.model.RegisterModel;
import com.mongodb.gridfs.GridFSDBFile;

public interface GridFSOperationDao {

	public boolean insertOneFile(InputStream inputStream,String identifier)throws IOException;
	public GridFSDBFile searchOneFile(String identifier);
	public List<GridFSDBFile> searchFiles(String identifier);
	
	//suraj's
	
	public boolean updateOneFile(InputStream inputStream,String identifier)throws IOException;

	
}

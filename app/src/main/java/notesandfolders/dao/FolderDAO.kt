package notesandfolders.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import notesandfolders.entities.Folder
import java.util.UUID

@Dao
interface FolderDAO{
    @Insert
    fun insert(vararg folder: Folder)

    @Query("SELECT * FROM folder WHERE id = :id")
    fun get(id: UUID): LiveData<Folder>

    @Query("SELECT * FROM folder WHERE parent_folder_id = :parentFolderId")
    fun getChildFolders(parentFolderId: UUID): LiveData<List<Folder>>
}
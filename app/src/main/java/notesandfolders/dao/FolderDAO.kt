package notesandfolders.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import notesandfolders.entities.Folder
import java.util.Date
import java.util.UUID

@Dao
interface FolderDAO{
    @Insert
    fun insert(vararg folder: Folder)

    @Query("SELECT * FROM folder WHERE id = :id")
    fun get(id: UUID): Folder

    @Query("SELECT * FROM folder WHERE parent_folder_id = :parentFolderId")
    fun getChildFolders(parentFolderId: UUID): List<Folder>

    @Delete
    fun delete(folder: Folder)

    @Query("UPDATE folder SET last_updated = :lastUpdated WHERE parent_folder_id = :parentFolderId")
    fun updateLastUpdated(parentFolderId: UUID, lastUpdated: Date)
}
package notesandfolders.dao

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
    fun get(id: UUID): Folder
}
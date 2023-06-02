package notesandfolders.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import notesandfolders.entities.Note
import java.util.UUID

@Dao
interface NoteDAO{
    @Insert
    fun insert(vararg note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    fun get(id: UUID): Note

    @Query("SELECT * FROM note WHERE parent_folder_id = :parentFolderId")
    fun getChildNotes(parentFolderId: UUID): List<Note>
}
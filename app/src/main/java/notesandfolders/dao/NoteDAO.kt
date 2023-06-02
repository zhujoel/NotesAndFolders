package notesandfolders.dao

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
}
package notesandfolders.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Folder(
    @PrimaryKey val id: UUID,
    @ColumnInfo(name = "parent_folder_id") val parentFolderId: UUID,
    @ColumnInfo(name = "title") val  title: String
)
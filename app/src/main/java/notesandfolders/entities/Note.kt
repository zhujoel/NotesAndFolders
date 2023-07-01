package notesandfolders.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.Date
import java.util.UUID

@Entity(foreignKeys = [ForeignKey(entity = Folder::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("parent_folder_id"),
    onDelete = ForeignKey.CASCADE)
])
data class Note(
    @PrimaryKey val id: UUID,
    @ColumnInfo(name = "parent_folder_id") val parentFolderId: UUID,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "created_at") val  createdAt: Date
)
package notesandfolders.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import notesandfolders.dao.FolderDAO
import notesandfolders.dao.NoteDAO
import java.util.Date
import java.util.UUID

@Database(entities= [Folder::class, Note::class], version = 1, exportSchema = false)
@TypeConverters(DbTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun folderDao(): FolderDAO
    abstract fun noteDAO(): NoteDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase{
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE =  Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "notes-and-folders-db")
                        .addCallback(object: Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase){
                                super.onCreate(db)
                                Thread {
                                    getDatabase(context).folderDao().insert(Folder(UUID(0L, 0L), UUID(0L, 0L), "root", Date(), Date()))
                                }.start()
                            }
                        })
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}
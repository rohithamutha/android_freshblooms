package com.example.freshblooms

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import java.io.*

object FileUtil {

    @Throws(IOException::class)
    fun from(context: Context, uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
            ?: throw IOException("Unable to open input stream from URI")
        val fileName = getFileName(context, uri)
        val tempFile = File(context.cacheDir, fileName)

        // Write input stream data to the file
        FileOutputStream(tempFile).use { outputStream ->
            inputStream.copyTo(outputStream)
        }

        return tempFile
    }

    private fun getFileName(context: Context, uri: Uri): String {
        var name = "temp_file"
        val returnCursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
        returnCursor?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            if (cursor.moveToFirst() && nameIndex != -1) {
                name = cursor.getString(nameIndex)
            }
        }
        return name
    }
}

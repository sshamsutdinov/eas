package ru.eas.domain.storage

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileStorageService(
    private val fileStorage: GridFsOperations
) {

    companion object : Logging

    fun store(file: MultipartFile): StoreResult {
        val objectId = fileStorage.store(file.inputStream, file.name, file.contentType)

        logger.info("File(id=$objectId name=${file.name}, contentType=${file.contentType}) is stored successfully")

        return StoreResult.Success(objectId.toString())
    }

    fun download(fileId: String): DownloadResult {
        val fileMeta = fileStorage.findOne(Query(Criteria.where("_id").`is`(fileId)))

        if (fileMeta == null) {
            logger.error("Cannot find file by id = $fileId")
            return DownloadResult.Fail(message = "Cannot find file by id = $fileId")
        }

        val file = fileStorage.getResource(fileMeta)

        return DownloadResult.Success(
            File(
                fileName = file.filename,
                contentType = file.contentType,
                stream = file.inputStream
            )
        )
    }

    sealed class StoreResult {
        data class Success(val fileId: String) : StoreResult()
        data class Fail(val message: String) : StoreResult()
    }

    sealed class DownloadResult {
        data class Success(val file: File) : DownloadResult()
        data class Fail(val message: String) : DownloadResult()
    }
}


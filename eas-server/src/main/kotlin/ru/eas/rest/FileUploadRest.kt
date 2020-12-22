package ru.eas.rest

import org.springframework.http.MediaType
import org.springframework.util.FileCopyUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.eas.domain.storage.File
import ru.eas.domain.storage.FileStorageService
import ru.eas.domain.storage.FileStorageService.DownloadResult
import ru.eas.domain.storage.FileStorageService.StoreResult
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("${Api.GENERAL}/file")
class FileUploadRest(
    private val fileStorageService: FileStorageService
) {

    @PutMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(@RequestPart("file") file: MultipartFile): Response {
        return when (val result = fileStorageService.store(file)) {
            is StoreResult.Success -> Response.Success(result)
            is StoreResult.Fail -> Response.Fail(result.message)
        }
    }

    @GetMapping("/{id}")
    fun downloadFile(@PathVariable("id") fileId: String, response: HttpServletResponse): Response {
        return when (val result = fileStorageService.download(fileId)) {
            is DownloadResult.Success -> {
                response.contentType = result.file.contentType
                response.addHeader("fileName", result.file.fileName)
                FileCopyUtils.copy(result.file.stream, response.outputStream)

                return Response.Success<File>()
            }
            is DownloadResult.Fail -> Response.Fail(result.message)
        }
    }
}
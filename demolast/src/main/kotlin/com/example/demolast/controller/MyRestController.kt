package com.example.demolast.controller
import com.example.demolast.dao.ProductRepository
import com.example.demolast.data.Product
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths

@RestController
class MyRestController {
    private var productRepository: ProductRepository? = null

    fun MyRestController(p: ProductRepository) {
        productRepository = p
    }
    @GetMapping(path = ["/photoProduct/{id}"], produces = [MediaType.IMAGE_PNG_VALUE])

    open fun getPhoto(@PathVariable("id") id: Long?): ByteArray?  {
        val p: Product = productRepository!!.findById(id!!).get()
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/ecom/products/" + p.photoName))
    }

    @PostMapping(path = ["/uploadPhoto/{id}"])
    fun uploadPhoto(file: MultipartFile, @PathVariable id: Long?) {
        val p = productRepository!!.findById(id!!).get()
        p.photoName=(file.originalFilename)
        Files.write(Paths.get(System.getProperty("user.home") + "/ecom/products/" + p.photoName), file.bytes)
        productRepository!!.save(p)
    }
}
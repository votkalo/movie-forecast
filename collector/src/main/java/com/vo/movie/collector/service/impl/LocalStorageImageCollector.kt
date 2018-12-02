package com.vo.movie.collector.service.impl

import com.vo.movie.collector.configuration.StorageProperties
import com.vo.movie.collector.dto.Image
import com.vo.movie.collector.service.ImageCollector
import com.vo.movie.collector.util.createPathIfNotExist
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import java.util.*
import javax.imageio.ImageIO

@Service
class LocalStorageImageCollector(storageProperties: StorageProperties) : ImageCollector {

    private val image: StorageProperties.File = storageProperties.image!!

    override fun collectImage(url: String): Image? {
        if (url.isEmpty()) return null
        image.createPathIfNotExist()
        val bufferedImage: BufferedImage = ImageIO.read(URL(url))
        val uuid: UUID = UUID.randomUUID()
        ImageIO.write(bufferedImage, image.extension, File("${image.path}/$uuid.${image.extension}"))
        return Image(url, uuid.toString())
    }

}
package ikhwankoto.com.explore_image_loader.Coil

import coil.map.Mapper

class SchoolMapper : Mapper<School, String> {
    override fun map(data: School) = data.imageUrl
}
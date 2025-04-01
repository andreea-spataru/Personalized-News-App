package com.example.personalizednews.data

import com.example.stiripersonalizate.data.News

// Din Entity în model
fun NewsEntity.toNews(): News {
    return News(title = title, content = content)
}

// Din model în Entity
fun News.toEntity(): NewsEntity {
    return NewsEntity(title = title, content = content)
}

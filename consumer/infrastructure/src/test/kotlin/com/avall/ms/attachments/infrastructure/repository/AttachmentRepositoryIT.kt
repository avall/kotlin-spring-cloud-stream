package com.avall.ms.attachments.infrastructure.repository

//import com.avall.ms.attachments.arch.kotlin.loggerFor
//import com.avall.ms.attachments.infrastructure.PostgreSqlContainer
//import com.avall.ms.attachments.infrastructure.TestApplication
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInstance
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.test.context.ContextConfiguration
//import org.testcontainers.junit.jupiter.Testcontainers
//import strikt.api.expectThat
//import strikt.assertions.isEqualTo
//import java.util.*


// see: https://github.com/spring-projects-experimental/spring-boot-r2dbc/issues/68
// see: https://www.wwt.com/article/using-testcontainers-for-unit-tests-with-spring-and-kotlin
// see: https://github.com/hantsy/spring-reactive-sample/blob/master/boot-data-r2dbc-postgresql/src/test/java/com/example/demo/PostRepositoryTest.java
// see: https://rieckpil.de/testing-spring-boot-applications-with-kotlin-and-testcontainers/
//@DataJpaTest(properties = [
//    "logging.level.org.springframework.r2dbc=debug",
//    "logging.level.com.avall.ms.attachments.infrastructure.adapters.database=debug"
//])
//@Testcontainers
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
//@ContextConfiguration(classes = [TestApplication::class, AttachmentRepository::class])
class AttachmentRepositoryIT /*: PostgreSqlContainer()*/ {
//    private val log = loggerFor(javaClass)

//    @Autowired
//    lateinit var attachmentRepository: AttachmentRepository
//
//    @Test
//    fun `Given a new Attachment When the Attachment stored in database Then you can retrieve using findById`() {
//        // Given
//        var attachment = buildAttachment()
//
//        // When
//        attachment = this.attachmentRepository.save(attachment)!!
//
//        // Then
//        expectThat(this.attachmentRepository.findById(attachment.id.toString()).get())
//        {
//            get { id } isEqualTo attachment.id
//            get { objectId } isEqualTo attachment.objectId
//            get { parentObjectName } isEqualTo attachment.parentObjectName
//            get { fileName } isEqualTo attachment.fileName
//            get { contentType } isEqualTo attachment.contentType
//            get { path } isEqualTo attachment.path
//            get { type } isEqualTo attachment.type
//            get { docType } isEqualTo attachment.docType
//        }
//    }
//
//    @Test
//    fun `Given a new Attachment When the Attachment stored in database Then you can retrieve using findByParentId`() {
//        // Given
//        var attachment = buildAttachment()
//
//        // When
//        attachment = this.attachmentRepository.save(attachment)!!
//
//        // Then
//        expectThat(this.attachmentRepository.findByParentId(attachment.objectId).size).isEqualTo(1)
//    }
//
//    @Test
//    fun `Given a saved Attachment When the Attachment is deleted in database Then is not found`() {
//        // Given
//        var attachment:AttachmentEntity = buildAttachment()
//        attachment = this.attachmentRepository.save(attachment)
//
//        // When
//        this.attachmentRepository.delete(attachment)
//
//        // Then
//        expectThat(this.attachmentRepository.findById(attachment.id.toString()).isEmpty).isEqualTo(true)
//    }
//
//    private fun buildAttachment(
//        id: String = UUID.randomUUID().toString(),
//        objectId : String = UUID.randomUUID().toString(),
//        contentType : String = "contentType",
//        parentObjectName : String = "parentObjectName",
//        path : String = "path",
//        fileName : String = "fileName",
//        type : String = "type",
//        docType : String = "docType",
//        description : String = "description",
//        country : String = "country",
//        language : String = "language",
//        isPrivate : Boolean = true,
//        isDocument : Boolean = true
//    ): AttachmentEntity {
//        return AttachmentEntity(
//            id = id,
//            contentType = contentType,
//            objectId = objectId,
//            parentObjectName = parentObjectName,
//            fileName =  fileName,
//            path = path,
//            type = type,
//            docType = docType,
//            description =  description,
//            country = country,
//            language = language,
//            isDocument = isDocument,
//            isPrivate = isPrivate
//        )
//    }
}
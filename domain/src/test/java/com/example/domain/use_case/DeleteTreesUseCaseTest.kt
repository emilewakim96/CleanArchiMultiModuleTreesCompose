package com.example.domain.use_case

import com.example.data.data_source.repository.FakeTreesRepository
import com.example.domain.entities.TreeEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteTreesUseCaseTest {

    private lateinit var deleteTrees: DeleteTreesUseCase
    private lateinit var fakeTreesRepository: FakeTreesRepository
    private val trees = mutableListOf<TreeEntity>()

    @Before
    fun setUp() {
        fakeTreesRepository = FakeTreesRepository()
        deleteTrees = DeleteTreesUseCase(fakeTreesRepository)

        trees.addAll(
            listOf(
                TreeEntity(
                    id = "1", adresse = "Bois Colombes", circonferenceencm = 3,
                    espece = "palm", hauteurenm = 20
                ),
                TreeEntity(
                    id = "2", adresse = "Paris", circonferenceencm = 5,
                    espece = "noisettes", hauteurenm = 10
                ),
                TreeEntity(
                    id = "3", adresse = "Saint ouen", circonferenceencm = 13,
                    espece = "hispanica", hauteurenm = 90
                ),
                TreeEntity(
                    id = "4", adresse = "Troca", circonferenceencm = 2,
                    espece = "betulus", hauteurenm = 40
                ),
                TreeEntity(
                    id = "5", adresse = "Opera", circonferenceencm = 7,
                    espece = "banane", hauteurenm = 5
                )
            )
        )

        runBlocking {
            trees.forEach { fakeTreesRepository.saveTree(it) }
        }
    }

    @Test
    fun `delete trees deletes tree from Room`(): Unit = runBlocking {
        Truth.assertThat(fakeTreesRepository.getTreesList()?.size).isEqualTo(5)
        // delete first tree in list from room
        deleteTrees.invoke(trees.first())
        val newList = fakeTreesRepository.getTreesList()
        Truth.assertThat(newList?.size).isEqualTo(4)
        Truth.assertThat(newList?.first()?.id).isEqualTo("2")
    }
}
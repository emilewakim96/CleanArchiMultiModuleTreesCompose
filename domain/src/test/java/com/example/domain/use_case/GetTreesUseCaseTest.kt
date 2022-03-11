package com.example.domain.use_case

import com.example.data.data_source.repository.FakeTreesRepository
import com.example.domain.entities.TreeEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTreesUseCaseTest {

    private lateinit var getTrees: GetTreesUseCase
    private lateinit var fakeTreesRepository: FakeTreesRepository

    @Before
    fun setUp() {
        fakeTreesRepository = FakeTreesRepository()
        getTrees = GetTreesUseCase(fakeTreesRepository)

        val trees = mutableListOf<TreeEntity>()
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

        trees.shuffle()
        runBlocking {
            trees.forEach { fakeTreesRepository.saveTree(it) }
        }
    }

    @Test
    fun `get trees return correct trees`(): Unit = runBlocking {
        val response = getTrees.invoke()
        val trees = response.data
        Truth.assertThat(trees?.size).isEqualTo(5)
    }
}
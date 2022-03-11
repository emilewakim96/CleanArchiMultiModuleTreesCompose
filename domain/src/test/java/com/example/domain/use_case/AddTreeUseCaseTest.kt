package com.example.domain.use_case

import com.example.data.data_source.remote.responses.Tree
import com.example.data.data_source.repository.FakeTreesRepository
import com.example.domain.mappers.mapToEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddTreeUseCaseTest {

    private lateinit var addTrees: AddTreeUseCase
    private lateinit var fakeTreesRepository: FakeTreesRepository
    private val trees = mutableListOf<Tree>()

    @Before
    fun setUp() {
        fakeTreesRepository = FakeTreesRepository()
        addTrees = AddTreeUseCase(fakeTreesRepository)

        trees.addAll(
            listOf(
                Tree(
                    id = "1", adresse = "Bois Colombes", circonferenceencm = 3,
                    espece = "palm", hauteurenm = 20
                ),
                Tree(
                    id = "2", adresse = "Paris", circonferenceencm = 5,
                    espece = "noisettes", hauteurenm = 10
                ),
                Tree(
                    id = "3", adresse = "Saint ouen", circonferenceencm = 13,
                    espece = "hispanica", hauteurenm = 90
                ),
                Tree(
                    id = "4", adresse = "Troca", circonferenceencm = 2,
                    espece = "betulus", hauteurenm = 40
                ),
                Tree(
                    id = "5", adresse = "Opera", circonferenceencm = 7,
                    espece = "banane", hauteurenm = 5
                )
            )
        )

        trees.shuffle()
    }

    @Test
    fun `add tree adds tree in Room`(): Unit = runBlocking {
        trees.forEach {
            addTrees.invoke(it.mapToEntity())
        }
        Truth.assertThat(fakeTreesRepository.getTreesListFromDB()?.size).isEqualTo(5)
    }
}